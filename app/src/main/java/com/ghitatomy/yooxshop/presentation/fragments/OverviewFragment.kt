package com.ghitatomy.yooxshop.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ghitatomy.yooxshop.R
import com.ghitatomy.yooxshop.databinding.FragmentOverviewBinding
import com.ghitatomy.yooxshop.domain.ItemSortType
import com.ghitatomy.yooxshop.domain.response.DomainResponse
import com.ghitatomy.yooxshop.presentation.adapters.ProductListAdapter
import com.ghitatomy.yooxshop.presentation.viewmodels.OverviewViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OverviewFragment : Fragment() {
    @Inject
    lateinit var productListAdapter: ProductListAdapter

    private val viewModel : OverviewViewModel by viewModels()
    private lateinit var fragmentOverviewBinding: FragmentOverviewBinding
    private var isLoading: Boolean = false
    private var isScrolling: Boolean = false
    private var page: Long = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentOverviewBinding = FragmentOverviewBinding.bind(view)
        initDropdown()
        initRecyclerView()
        getDefaultProducts()
        viewLifecycleOwnerLiveData.value?.let { owner ->
            viewModel.itemsHeadlines.observe(owner) { response ->
                when (response) {
                    is DomainResponse.Success -> {
                        hideProgressBar()
                        val list = response.model
                        viewModel.run {
                            if (previousSortType == sortType && previousSortType == ItemSortType.RECOMMENDED) {
                                productListAdapter.addProductsList(list)
                                hideProgressScroll()
                            } else {
                                productListAdapter.setList(list)
                            }
                        }
                        viewModel.previousSortType = viewModel.sortType
                    }
                    is DomainResponse.Error -> {
                        hideProgressBar()
                        hideProgressScroll()
                        response.model.message?.let {
                            Toast.makeText(activity, "An error occurred: $it", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

    private fun initDropdown() {
        fragmentOverviewBinding.dropdownMenu.run {
            setItems(
                ItemSortType.RECOMMENDED.sortType,
                ItemSortType.LATEST_ARRIVALS.sortType,
                ItemSortType.LOW_PRICE.sortType,
                ItemSortType.HIGH_PRICE.sortType)
            this.setOnItemSelectedListener { _, position, _, _ ->
                val itemSortType = when(position) {
                    0 -> ItemSortType.RECOMMENDED
                    1 -> ItemSortType.LATEST_ARRIVALS
                    2 -> ItemSortType.LOW_PRICE
                    3 -> ItemSortType.HIGH_PRICE
                    else -> ItemSortType.RECOMMENDED
                }
                viewModel.run {
                    sortType = itemSortType
                    if (sortType != previousSortType) {
                        viewModel.sortType.page = 1
                    }
                    this.getItems()
                }
            }
        }
    }

    private fun initRecyclerView() {
        fragmentOverviewBinding.itemsRecycleView.apply {
            productListAdapter.setOnItemClickListener {
                val bundle = Bundle().apply { putSerializable("selected_product", it) }
                findNavController().navigate(R.id.action_overviewFragment_to_productInfoFragment, bundle)
            }
            adapter = productListAdapter
            layoutManager = GridLayoutManager(activity, 2)
            addOnScrollListener(this@OverviewFragment.onScrollListener)
        }
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if(viewModel.sortType == ItemSortType.RECOMMENDED) {
                val layoutManager = fragmentOverviewBinding.itemsRecycleView.layoutManager as GridLayoutManager
                val sizeOfCurrentList = layoutManager.itemCount
                val visibleItems = layoutManager.childCount
                val topPosition = layoutManager.findFirstVisibleItemPosition()

                val hasReachedToEnd = topPosition + visibleItems >= sizeOfCurrentList
                val shouldPaginate = !isLoading && hasReachedToEnd && isScrolling
                if (shouldPaginate) {
                    page++
                    if (page > 3) {
                        page = 1
                    }
                    viewModel.sortType.page = page
                    showProgressScroll()
                    viewModel.getItems()
                    isScrolling = false
                }
            } else {
                isScrolling = false
            }
        }
    }

    private fun getDefaultProducts() {
        showProgressBar()
        viewModel.getItems()
    }

    private fun showProgressBar() {
        isLoading = true
        fragmentOverviewBinding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        isLoading = false
        fragmentOverviewBinding.progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressScroll(){
        fragmentOverviewBinding.progressBarScroll.visibility = View.VISIBLE
    }

    private fun hideProgressScroll(){
        fragmentOverviewBinding.progressBarScroll.visibility = View.INVISIBLE
    }
}