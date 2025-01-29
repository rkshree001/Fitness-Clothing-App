package com.fca.fitnessclothingapp.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.fca.fitnessclothingapp.R

class SignUp : AppCompatActivity() {
    private lateinit var navigate_wlc_signin: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        navigate_wlc_signin=findViewById(R.id.navigate_wlc_signin)

        navigate_wlc_signin.setOnClickListener {

            val intent = Intent(this@SignUp, WelcomeScreen::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }


    }
}