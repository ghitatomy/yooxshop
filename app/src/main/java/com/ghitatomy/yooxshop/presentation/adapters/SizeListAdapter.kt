package com.ghitatomy.yooxshop.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.ghitatomy.yooxshop.R
import com.ghitatomy.yooxshop.databinding.SizeListItemBinding

class SizeListAdapter (
    private val sizeList: MutableList<String>,
    private val callbackListener: ClickListener
) : RecyclerView.Adapter<SizeListAdapter.SizeListListViewHolder>() {

    lateinit var recyclerView: RecyclerView
    var selectedButtonPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeListListViewHolder {
        val binding = SizeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SizeListListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SizeListListViewHolder, position: Int) {
        holder.setup(sizeList[position], position)
    }

    override fun onAttachedToRecyclerView(recView: RecyclerView) {
        super.onAttachedToRecyclerView(recView)
        recyclerView = recView
    }

    override fun getItemCount(): Int = sizeList.size

    fun addSizeList(sizeCodeListItems: List<String>) {
        val startPosition = itemCount
        sizeList.addAll(sizeCodeListItems)
        notifyItemRangeInserted(startPosition, sizeCodeListItems.size)
    }

    inner class SizeListListViewHolder(private val binding: SizeListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setup(sizeName: String, position: Int) {
            binding.sizeButton.run {
                text = sizeName
                this.setOnClickListener {
                    if (selectedButtonPosition != position) {
                        removeStrokeFromUnselectedButton()
                        addStrokeForSelectedButton(it)
                        selectedButtonPosition = position
                        callbackListener.onSizeButtonClick()
                    }
                }
                if (position == 0) { this.performClick() }
            }
        }

        private fun MaterialButton.removeStrokeFromUnselectedButton() {
            val view = recyclerView.findViewHolderForAdapterPosition(selectedButtonPosition)?.itemView
            val button = view?.findViewById<MaterialButton>(R.id.size_button)
            button?.strokeColor = ContextCompat.getColorStateList(context, android.R.color.transparent)
        }
        private fun MaterialButton.addStrokeForSelectedButton(it: View?) {
            (it as MaterialButton).strokeColor = ContextCompat.getColorStateList(context, R.color.black)
        }
    }

    interface ClickListener { fun onSizeButtonClick() }
}