package com.fca.fitnessclothingapp.shoppingprocess

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fca.fitnessclothingapp.R
import com.fca.fitnessclothingapp.modelclass.ShoppingItem

class ShoppingProcessFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_shopping_process, container, false)

        val categoryRecyclerView: RecyclerView = view.findViewById(R.id.categoryRecyclerView)
        val categoryContentRecyclerView: RecyclerView = view.findViewById(R.id.categoryContentRecyclerView)
        categoryRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        val items = listOf(
            ShoppingItem("New Releases"),
            ShoppingItem("Women"),
            ShoppingItem("Men"),
            ShoppingItem("Kids"),
            ShoppingItem("Sale"),
            ShoppingItem("Trending Now"),
            ShoppingItem("Sportswear"),
            ShoppingItem("Shoes"),
            ShoppingItem("Accessories"),
            ShoppingItem("Gym Essentials"),
            ShoppingItem("Winter Collection"),
            ShoppingItem("Summer Collection"),
            ShoppingItem("Best Sellers"),
            ShoppingItem("Casual Wear"),
            ShoppingItem("Limited Edition"),
            ShoppingItem("Exclusive Deals")
        )

        val adapter = ShoppingAdapter(items)
        categoryRecyclerView.adapter = adapter

        return view


    }
}
