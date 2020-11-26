package com.tutor.tutordot.Startpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tutor.tutordot.R
import com.tutor.tutordot.SignUpActivity4
import kotlinx.android.synthetic.main.activity_sign_up3.*

class SignUpActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up3)

        btn_next3.setOnClickListener {
            val intent = Intent(this@SignUpActivity3, SignUpActivity2::class.java)
            startActivity(intent)
        }
        btn_back3.setOnClickListener {
            val intent = Intent(this@SignUpActivity3, SignUpActivity4::class.java)
            startActivity(intent)
        }
    }
}