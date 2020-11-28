package com.tutor.tutordot.Startpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tutor.tutordot.R
import kotlinx.android.synthetic.main.activity_sign_up3.*
import kotlinx.android.synthetic.main.activity_signup5.*

class SignupActivity5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup5)

        btn_goLogin.setOnClickListener {
            val intent = Intent(this@SignupActivity5, LoginFor1stActivity::class.java)
            startActivity(intent)
        }
    }
}