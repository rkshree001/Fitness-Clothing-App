package com.fca.fitnessclothingapp.shoppingprocess

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fca.fitnessclothingapp.R
import com.fca.fitnessclothingapp.modelclass.ShoppingItem

class DetailedScreenActivity : AppCompatActivity() {
    private lateinit var navigate_back: LinearLayout
    private lateinit var productNameTextView: TextView
    private lateinit var productRateTextView: TextView
    private lateinit var productDescTextView: TextView
    private lateinit var qty: TextView
    private lateinit var productImageView: ImageView
    private lateinit var decreaseQty: ImageView
    private lateinit var increaseQty: ImageView

    private var currentQty = 1  // Initialize quantity to 1
    private lateinit var shoppingItem: ShoppingItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_screen)

        // Initialize Views
        productNameTextView = findViewById(R.id.productNameTextView)
        productRateTextView = findViewById(R.id.productRateTextView)
        productDescTextView = findViewById(R.id.productDescTextView)
        productImageView = findViewById(R.id.productImageView)
        qty = findViewById(R.id.qty)
        increaseQty = findViewById(R.id.increaseQty)
        decreaseQty = findViewById(R.id.decreaseQty)
        navigate_back = findViewById(R.id.navigate_back)


        shoppingItem = intent.getSerializableExtra("shoppingItem") as ShoppingItem


        productNameTextView.text = shoppingItem.productName
        productRateTextView.text = "$ ${shoppingItem.productRate}"
        productDescTextView.text = shoppingItem.productDesc
        productImageView.setImageResource(shoppingItem.productImage)


        qty.text = currentQty.toString()


        increaseQty.setOnClickListener {
            currentQty++
            if (currentQty > 0) {
                qty.text = currentQty.toString()
                updateProductRate()
            }
        }

        decreaseQty.setOnClickListener {
            currentQty--
            if (currentQty < 1) {
                currentQty = 1

            }
            qty.text = currentQty.toString()
            updateProductRate()
        }


        navigate_back.setOnClickListener {
            val intent = Intent(this@DetailedScreenActivity, NewReleasesScreenActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }
    }


    private fun updateProductRate() {
        val totalPrice = shoppingItem.productRate.toFloat() * currentQty
        productRateTextView.text = "$ ${"%.2f".format(totalPrice)}"
    }
}
