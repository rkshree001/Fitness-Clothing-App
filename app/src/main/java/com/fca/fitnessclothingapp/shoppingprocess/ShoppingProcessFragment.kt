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

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        val items = listOf(
            ShoppingItem("Running Shoes"),
            ShoppingItem("Sports T-Shirt"),
            ShoppingItem("Gym Gloves" ),
            ShoppingItem("Water Bottle")
        )

        val adapter = ShoppingAdapter(items)
        recyclerView.adapter = adapter




        return view


    }
}
