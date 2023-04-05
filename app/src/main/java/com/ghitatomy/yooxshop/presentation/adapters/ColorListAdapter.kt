package com.ghitatomy.yooxshop.presentation.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.ghitatomy.yooxshop.R
import com.ghitatomy.yooxshop.databinding.ColorListItemBinding

class ColorListAdapter (
    private val colorsList: MutableList<String>,
    private val clickListener: ClickListener
) : RecyclerView.Adapter<ColorListAdapter.ColorListViewHolder>() {

    lateinit var recyclerView: RecyclerView
    var selectedButtonPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorListViewHolder {
        val binding =
            ColorListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ColorListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ColorListViewHolder, position: Int) {
        holder.setup(colorsList[position], position)
    }

    override fun onAttachedToRecyclerView(recView: RecyclerView) {
        super.onAttachedToRecyclerView(recView)
        recyclerView = recView
    }

    override fun getItemCount(): Int = colorsList.size

    fun addColorList(colors: List<String>) {
        val startPosition = itemCount
        colorsList.addAll(colors)
        notifyItemRangeInserted(startPosition, colors.size)
    }

    inner class ColorListViewHolder(private val binding: ColorListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setup(color: String, position: Int) {
            binding.colorButton.run {
                setBackgroundColor(Color.parseColor("#$color"))
                this.setOnClickListener {
                    if (selectedButtonPosition != position) {
                        removeIndicatorForUnselectedColor()
                        addIndicatorForSelectedColor()
                    }
                    selectedButtonPosition = position
                    clickListener.onColorButtonClick(color)
                }
                if (position == 0) { performClick() }
            }
        }

        private fun removeIndicatorForUnselectedColor() {
            val view = recyclerView.findViewHolderForAdapterPosition(selectedButtonPosition)?.itemView
            val indicatorImage =
                view?.findViewById<ShapeableImageView>(R.id.selected_color_indicator_image_view)
            indicatorImage?.visibility = View.INVISIBLE
        }

        private fun addIndicatorForSelectedColor() {
            binding.selectedColorIndicatorImageView.visibility = View.VISIBLE
        }
    }

    interface ClickListener { fun onColorButtonClick(color: String) }
}