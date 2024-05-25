package com.example.caount2.foodlogging.fooditem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.caount2.R

class FoodItemAdapter(
    private val foodItemList: List<FoodItemCell>,
    private val onItemClick: (FoodItemCell) -> Unit
) :
    RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder>() {

    class FoodItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemNameTextView: TextView = itemView.findViewById(R.id.textViewItemName)

        fun bind(foodItem: FoodItemCell, clickListener: (FoodItemCell) -> Unit) {
            itemNameTextView.text = foodItem.name
            itemView.setOnClickListener { clickListener(foodItem) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return FoodItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FoodItemViewHolder, position: Int) {
        val currentItem = foodItemList[position]
        holder.bind(currentItem, onItemClick)
    }

    override fun getItemCount() = foodItemList.size
}
