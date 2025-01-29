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
     }

    override fun getItemCount(): Int = itemList.size
}
