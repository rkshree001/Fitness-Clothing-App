package com.fca.fitnessclothingapp.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.fca.fitnessclothingapp.R
import com.fca.fitnessclothingapp.shoppingprocess.    ShoppingProcessFragment

class SIgnInFCA : AppCompatActivity() {

    private lateinit var navigate_wlc_scn: LinearLayout
    private lateinit var sign_in: LinearLayout
    private lateinit var forgot_password_sign_in: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_fca)

        initView()


        navigate_wlc_scn.setOnClickListener {

            val intent = Intent(this@SIgnInFCA, WelcomeScreen::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        sign_in.setOnClickListener {
            val shoppingProcessFragment = ShoppingProcessFragment()

            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, shoppingProcessFragment)
                .addToBackStack(null)
                .commit()
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        forgot_password_sign_in.setOnClickListener {

            val intent = Intent(this@SIgnInFCA, ForgotPassword::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }


    }

    private fun initView() {
        navigate_wlc_scn=findViewById(R.id.navigate_wlc_scn)
        forgot_password_sign_in=findViewById(R.id.forgot_password_sign_in)
        sign_in=findViewById(R.id.sign_in)


    }
}