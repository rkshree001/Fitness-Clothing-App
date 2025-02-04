package com.fca.fitnessclothingapp.shoppingprocess

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fca.fitnessclothingapp.R
import com.fca.fitnessclothingapp.authentication.SIgnInFCA
import com.fca.fitnessclothingapp.modelclass.ShoppingItem
import com.fca.fitnessclothingapp.shoppingprocess.adapter.ShoppingItemAdapter
import com.fca.fitnessclothingapp.viewmodel.ShoppingProcessViewModel

class NewReleasesScreenActivity : AppCompatActivity() {
    private val shoppingProcessViewModel: ShoppingProcessViewModel by viewModels()

    private lateinit var categoryContentRecyclerView : RecyclerView
    private lateinit var navigate_back: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_new_releases_screen)
        navigate_back = findViewById(R.id.navigate_back)

        categoryContentRecyclerView = findViewById(R.id.categoryContentRecyclerView)
        categoryContentRecyclerView.layoutManager = GridLayoutManager(this, 2)

        navigate_back.setOnClickListener {

            val fragmentTransaction = supportFragmentManager.beginTransaction()
            val fragment = ShoppingProcessFragment()


            fragmentTransaction.replace(android.R.id.content, fragment)
            fragmentTransaction.addToBackStack(null)

            fragmentTransaction.commit()

            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }


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
            ),ShoppingItem(
                productName = "Dri-FIT UltraBreathe",
                productRate = "$65",
                productDesc = "Padded Sports Bra",
                productImage = R.drawable.dry_fit_top
            ),ShoppingItem(
                productName = "Long Sleeve Top",
                productRate = "$95",
                productDesc = "Women's Velour",
                productImage = R.drawable.long_slevee_top
            )
        )


        val adapterShoppingItem = ShoppingItemAdapter(shoppingItems,shoppingProcessViewModel)
        categoryContentRecyclerView.adapter = adapterShoppingItem


        shoppingProcessViewModel.selectedCategory.observe(this@NewReleasesScreenActivity, { category ->
            if (category == "Swoosh T-Shirt") {
                val intent = Intent(this, DetailedScreenActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            } else {


            }
        })

        shoppingProcessViewModel.clickedItem.observe(this, { item ->
            item?.let {
//                if (item.productName == "Swoosh T-Shirt") {
                    val intent = Intent(this, DetailedScreenActivity::class.java)

                    intent.putExtra("shoppingItem", it)

                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
//                } else {
//
//                  Toast.makeText(this, "Clicked: ${item.productName}", Toast.LENGTH_SHORT).show()
//                }

            }
        })
    }
}