package com.fca.fitnessclothingapp.shoppingprocess

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fca.fitnessclothingapp.R
import com.fca.fitnessclothingapp.modelclass.ShoppingItem

class ShoppingAdapter(private val itemList: List<ShoppingItem>) :
    RecyclerView.Adapter<ShoppingAdapter.ViewHolder>() {

    private var selectedPosition = 0

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.itemName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_type, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.textView.text = item.name


        if (position == selectedPosition) {
            holder.textView.setTextColor(holder.itemView.context.getColor(R.color.black))
        } else {
            holder.textView.setTextColor(holder.itemView.context.getColor(R.color.gray_8D95A3))
        }


        holder.itemView.setOnClickListener {
            selectedPosition = position
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = itemList.size
}
