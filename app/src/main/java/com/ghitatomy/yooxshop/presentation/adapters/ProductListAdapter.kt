package com.ghitatomy.yooxshop.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ghitatomy.yooxshop.databinding.ProductListItemBinding
import com.ghitatomy.yooxshop.presentation.model.ProductUIModel

class ProductListAdapter(
    private val productsList: MutableList<ProductUIModel>,
) : RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {
    lateinit var recyclerView: RecyclerView

    private var onItemClickListener : ((ProductUIModel) -> Unit)? = null

    fun setOnItemClickListener(listener: (ProductUIModel) -> Unit) {
        onItemClickListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding =
            ProductListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productsList[position])
    }

    override fun onAttachedToRecyclerView(recView: RecyclerView) {
        super.onAttachedToRecyclerView(recView)
        recyclerView = recView
    }

    override fun getItemCount(): Int = productsList.size

    fun addProductsList(products: List<ProductUIModel>) {
        val startPosition = itemCount
        productsList.addAll(products)
        notifyItemRangeInserted(startPosition, products.size)
    }

    fun setList(list: List<ProductUIModel>) {
        val size = productsList.size
        productsList.clear()
        notifyItemRangeRemoved(0, size)
        productsList.addAll(list)
        notifyItemRangeInserted(0, list.size)
    }

    inner class ProductViewHolder(private val binding: ProductListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductUIModel) {
            product.imageUrl.let {
                Glide.with(binding.productImageView.context)
                    .load(it)
                    .into(binding.productImageView)
            }
            binding.brandNameTextView.text = product.brandName
            binding.categoryTextView.text = product.category
            binding.priceTextView.text = product.price
            binding.root.setOnClickListener {
                onItemClickListener?.let { it(product) }
            }
        }
    }

}