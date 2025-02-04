package com.fca.fitnessclothingapp.shoppingprocess.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fca.fitnessclothingapp.R
import com.fca.fitnessclothingapp.modelclass.ShoppingItem
import com.fca.fitnessclothingapp.viewmodel.ShoppingProcessViewModel

class ShoppingItemAdapter(
    private val shoppingItems: List<ShoppingItem>,
    private val viewModel: ShoppingProcessViewModel
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingItemViewHolder>() {


    private var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_shopping, parent, false)
        return ShoppingItemViewHolder(view)
    }
    @SuppressLint("RecyclerView")
    override fun onBindViewHolder(holder: ShoppingItemViewHolder,  position: Int) {
        val item = shoppingItems[position]
        holder.productName.text = item.productName
        holder.productRate.text = "$ ${item.productRate}"
        holder.productDesc.text = item.productDesc
        holder.productImage.setImageResource(item.productImage)


        if (position == selectedPosition) {
            holder.itemView.setBackgroundColor(holder.itemView.context.getColor(R.color.white))
        } else {
            holder.itemView.setBackgroundColor(holder.itemView.context.getColor(R.color.white))
        }

        holder.itemView.setOnClickListener {
            selectedPosition = position
            notifyDataSetChanged()


            viewModel.onShoppingItemClicked(item)

        }
    }

    override fun getItemCount(): Int = shoppingItems.size

    class ShoppingItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName: TextView = itemView.findViewById(R.id.productName)
        val productRate: TextView = itemView.findViewById(R.id.productRate)
        val productDesc: TextView = itemView.findViewById(R.id.productDesc)
        val productImage: ImageView = itemView.findViewById(R.id.productImage)
    }
}
