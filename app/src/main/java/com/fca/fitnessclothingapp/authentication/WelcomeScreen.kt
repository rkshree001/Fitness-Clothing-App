package com.fca.fitnessclothingapp.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.fca.fitnessclothingapp.R


class WelcomeScreen : AppCompatActivity() {

    private lateinit var navigate_sign_in: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_screen)
        initView()

        navigate_sign_in.setOnClickListener {
            val intent = Intent(this@WelcomeScreen, SIgnInFCA::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

    }

    private fun initView() {
        navigate_sign_in=findViewById(R.id.navigate_sign_in)


    }
}