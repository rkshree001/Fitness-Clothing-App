package com.fca.fitnessclothingapp.notification

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.fca.fitnessclothingapp.R
import com.fca.fitnessclothingapp.shoppingprocess.ShoppingProcessFragment
import com.fca.fitnessclothingapp.sportscenter.SportsCenterActivity
import com.fca.fitnessclothingapp.useraccountactivity.UserAccountActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class NotificationsInboxActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var user1: LinearLayout
    private lateinit var user2: LinearLayout
    private lateinit var user3: LinearLayout
    private lateinit var navigate_back: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications_inbox)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        navigate_back = findViewById(R.id.navigate_back)
        user1 = findViewById(R.id.user1)
        user2 = findViewById(R.id.user2)
        user3 = findViewById(R.id.user3)

        user1.setOnClickListener {
            startActivity(Intent(this, NotificationChatActivity::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        user2.setOnClickListener {
            startActivity(Intent(this, NotificationChatActivity::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        user3.setOnClickListener {
            startActivity(Intent(this, NotificationChatActivity::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        bottomNavigationView.itemRippleColor = null
        bottomNavigationView.selectedItemId = R.id.navigation_notification

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
                R.id.navigation_user -> {
                    startActivity(Intent(this, UserAccountActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
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


        navigate_back.setOnClickListener {
            val shoppingProcessFragment = ShoppingProcessFragment()

            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, shoppingProcessFragment)
                .addToBackStack(null)
                .commit()
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }




    }

    override fun onBackPressed() {
        super.onBackPressed()
        val shoppingProcessFragment = ShoppingProcessFragment()

        supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, shoppingProcessFragment)
            .addToBackStack(null)
            .commit()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }


}