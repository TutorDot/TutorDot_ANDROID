package com.tutor.tutordot.Startpage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tutor.tutordot.CalenderActivity
import com.tutor.tutordot.R
import com.tutor.tutordot.StartServer.RequestLogin
import com.tutor.tutordot.StartServer.RequestToServer
import com.tutor.tutordot.extention.customEnqueue
import com.tutor.tutordot.extention.showToast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    val requestToServer = RequestToServer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_btn_login.setOnClickListener {
            if (et_email.text.isNullOrBlank() || et_pw.text.isNullOrBlank()) {
                showToast("이메일과 비밀번호를 모두 입력하세요.")
                //Toast.makeText(this, "이메일과 비밀번호를 모두 입력하세요.", Toast.LENGTH_SHORT).show()
            } else {
                //로그인 요청
                requestToServer.service.requestLogin(
                    RequestLogin(
                        email = et_email.text.toString(),
                        password = et_pw.text.toString()
                    )   //로그인 정보를 전달
                ).customEnqueue(
                    onError = { showToast("올바르지 못한 요청입니다") },
                    onSuccess = {
                        if (it.success) {
                            showToast("로그인 성공")
                            val intent = Intent(this@LoginActivity, CalenderActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            showToast("아이디/비밀번호를 확인하세요!")
                        }
                    }
                )
            }
            val tmp: String? = intent.getStringExtra("check")

            if (tmp.equals("1")) {
                val email = intent.getStringExtra("email")
                val password = intent.getStringExtra("password")
                et_email.setText(email)
                et_pw.setText(password)
            }
        }
    }
 }



