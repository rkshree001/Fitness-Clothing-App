package com.fca.fitnessclothingapp.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.fca.fitnessclothingapp.R

class CreatePassword : AppCompatActivity() {

    private lateinit var sign_Up_create_pss:TextView
    private lateinit var navigate_wlc_signin:LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_password)

        sign_Up_create_pss=findViewById(R.id.sign_Up_create_pss)
        navigate_wlc_signin=findViewById(R.id.navigate_wlc_signin)


        sign_Up_create_pss.setOnClickListener {

            val intent = Intent(this@CreatePassword, SignUp::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        navigate_wlc_signin.setOnClickListener {

            val intent = Intent(this@CreatePassword, SIgnInFCA::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        testMethod()

    }

    private fun testMethod() {

        /*A TEST METHOD FOR GIT PUSH*/
    }
}