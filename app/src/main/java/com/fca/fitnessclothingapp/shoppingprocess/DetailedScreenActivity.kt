package com.fca.fitnessclothingapp.shoppingprocess

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.fca.fitnessclothingapp.R
import com.fca.fitnessclothingapp.authentication.WelcomeScreen
import com.fca.fitnessclothingapp.modelclass.ShoppingItem

class DetailedScreenActivity : AppCompatActivity() {
    private lateinit var navigate_back: LinearLayout
    private lateinit var productNameTextView: TextView
//    private lateinit var productRateTextView: TextView
    private lateinit var productDescTextView: TextView
    private lateinit var productImageView: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_screen)

        productNameTextView = findViewById(R.id.productNameTextView)
//        productRateTextView = findViewById(R.id.productRateTextView)
        productDescTextView = findViewById(R.id.productDescTextView)
        productImageView = findViewById(R.id.productImageView)

        val shoppingItem = intent.getSerializableExtra("shoppingItem") as ShoppingItem

        // Set the values in the views
        productNameTextView.text = shoppingItem.productName
//        productRateTextView.text = shoppingItem.productRate
        productDescTextView.text = shoppingItem.productDesc
        productImageView.setImageResource(shoppingItem.productImage)

        navigate_back = findViewById(R.id.navigate_back)




        navigate_back.setOnClickListener {

            val intent = Intent(this@DetailedScreenActivity, NewReleasesScreenActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)

        }

    }
}