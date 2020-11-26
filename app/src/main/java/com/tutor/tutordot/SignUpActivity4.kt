package com.tutor.tutordot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tutor.tutordot.Startpage.LoginFor1stActivity
import com.tutor.tutordot.Startpage.SignUpActivity2
import com.tutor.tutordot.Startpage.SignUpActivity3
import kotlinx.android.synthetic.main.activity_sign_up3.*
import kotlinx.android.synthetic.main.activity_sign_up4.*

class SignUpActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up4)

        btn_back4.setOnClickListener {
            val intent = Intent(this@SignUpActivity4, SignUpActivity4::class.java)
            startActivity(intent)
        }
        btn_signup.setOnClickListener{
            val intent = Intent(this@SignUpActivity4, LoginFor1stActivity::class.java)
            startActivity(intent)
        }
    }
}