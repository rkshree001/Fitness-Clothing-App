package com.fca.fitnessclothingapp.shoppingprocess

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fca.fitnessclothingapp.R
import com.fca.fitnessclothingapp.modelclass.CategoryItem
import com.fca.fitnessclothingapp.modelclass.ShoppingItem
import com.fca.fitnessclothingapp.shoppingprocess.adapter.ShoppingAdapter
import com.fca.fitnessclothingapp.shoppingprocess.adapter.ShoppingItemAdapter
import com.fca.fitnessclothingapp.sportscenter.SportsCenterActivity
import com.fca.fitnessclothingapp.useraccountactivity.UserAccountActivity
import com.fca.fitnessclothingapp.viewmodel.ShoppingProcessViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class ShoppingProcessFragment : Fragment() {



    private val shoppingProcessViewModel: ShoppingProcessViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_shopping_process, container, false)

        val categoryRecyclerView: RecyclerView = view.findViewById(R.id.categoryRecyclerView)
        val categoryContentRecyclerView: RecyclerView = view.findViewById(R.id.categoryContentRecyclerView)
        categoryRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//        categoryContentRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        categoryContentRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        val bottomNavigationView: BottomNavigationView = view.findViewById(R.id.bottomNavigationView)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_user -> {
                    val intent = Intent(requireContext(), UserAccountActivity::class.java)
                    startActivity(intent)
                    requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    true
                }
                R.id.navigation_location -> {
                    val intent = Intent(requireContext(), SportsCenterActivity::class.java)
                    startActivity(intent)
                    requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    true
                }
                else -> false
            }
        }


        bottomNavigationView.itemRippleColor = null


        val items = listOf(
            CategoryItem("New Releases"),
            CategoryItem("Women"),
            CategoryItem("Men"),
            CategoryItem("Kids"),
            CategoryItem("Sale"),
            CategoryItem("Trending Now"),
            CategoryItem("Sportswear"),
            CategoryItem("Shoes"),
            CategoryItem("Accessories"),
            CategoryItem("Gym Essentials"),
            CategoryItem("Winter Collection"),
            CategoryItem("Summer Collection"),
            CategoryItem("Best Sellers"),
            CategoryItem("Casual Wear"),
            CategoryItem("Limited Edition"),
            CategoryItem("Exclusive Deals")
        )


        val adapter = ShoppingAdapter(items, shoppingProcessViewModel)
        categoryRecyclerView.adapter = adapter

        shoppingProcessViewModel.selectedCategory.observe(viewLifecycleOwner, { category ->
            if (category == "New Releases") {
                val intent = Intent(requireContext(), NewReleasesScreenActivity::class.java)
                startActivity(intent)
                requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            } else {
                Toast.makeText(requireContext(), "Selected category: $category", Toast.LENGTH_SHORT).show()
            }
        })


        val shoppingItems = listOf(
            ShoppingItem(
                productName = "Swoosh T-Shirt",
                productRate = "$95",
                productDesc = "Women's Light support",
                productImage = R.drawable.fit_girl
            ),
            ShoppingItem(
                productName = "Pro Dri-Fit",
                productRate = "$70",
                productDesc = "Man's Tank Top",
                productImage = R.drawable.fit_boy
            )
        )

        val adapterShoppingItem = ShoppingItemAdapter(shoppingItems,shoppingProcessViewModel)
        categoryContentRecyclerView.adapter = adapterShoppingItem




        return view


    }
}
