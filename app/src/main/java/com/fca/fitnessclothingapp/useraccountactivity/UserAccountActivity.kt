package com.fca.fitnessclothingapp.useraccountactivity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.fca.fitnessclothingapp.R
import com.fca.fitnessclothingapp.shoppingprocess.NewReleasesScreenActivity
import com.fca.fitnessclothingapp.shoppingprocess.ShoppingProcessFragment
import com.fca.fitnessclothingapp.sportscenter.SportsCenterActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class UserAccountActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_account)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        bottomNavigationView.itemRippleColor = null
        bottomNavigationView.selectedItemId = R.id.navigation_user

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    val fragment = ShoppingProcessFragment()


                    fragmentTransaction.replace(android.R.id.content, fragment)
                    fragmentTransaction.addToBackStack(null)

                    fragmentTransaction.commit()

                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                    true
                }

                R.id.navigation_location -> {
                    startActivity(Intent(this, SportsCenterActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)


                    true
                }
                else -> false
            }
        }
    }
}