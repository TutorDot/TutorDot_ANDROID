package com.tutor.tutordot

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_btn_login.setOnClickListener{
                if (이메일.text.isNullOrBlank()||비밀번호.text.isNullOrBlank()){
                    Toast.makeText(this, "이메일과 비밀번호를 모두 입력하세요.", Toast.LENGTH_SHORT).show()
                }else {
                    val intent = Intent(this, CalenderActivity::class.java)
                    startActivity(intent)
                }
            }

        }

 }



