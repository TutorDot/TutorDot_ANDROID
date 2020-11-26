package com.tutor.tutordot.Startpage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tutor.tutordot.R
import kotlinx.android.synthetic.main.activity_sign_up2.*

class SignUpActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up2)

        btn_next2.setOnClickListener {
            val intent = Intent(this@SignUpActivity2, SignUpActivity3::class.java)
            startActivity(intent)
        }
        btn_back2.setOnClickListener {
            val intent = Intent(this@SignUpActivity2, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}