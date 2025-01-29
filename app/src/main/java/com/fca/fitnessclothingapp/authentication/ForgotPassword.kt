package com.fca.fitnessclothingapp.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.fca.fitnessclothingapp.R

class ForgotPassword : AppCompatActivity() {

    private lateinit var create_password:LinearLayout
    private lateinit var navigate_wlc_signin:LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_forgot_password)

        create_password=findViewById(R.id.create_password)
        navigate_wlc_signin=findViewById(R.id.navigate_wlc_signin)

        create_password.setOnClickListener {

            val intent = Intent(this@ForgotPassword, CreatePassword::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        navigate_wlc_signin.setOnClickListener {

            val intent = Intent(this@ForgotPassword, SIgnInFCA::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }


    }
}