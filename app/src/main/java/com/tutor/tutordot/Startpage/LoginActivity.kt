package com.tutor.tutordot.Startpage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tutor.tutordot.CalenderActivity
import com.tutor.tutordot.R
import com.tutor.tutordot.StartServer.RequestLogin
import com.tutor.tutordot.StartServer.RequestToServer
import com.tutor.tutordot.Startpage.AutoLogin.MySharedPreferences
import com.tutor.tutordot.extention.customEnqueue
import com.tutor.tutordot.extention.showToast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    //서버 이용
    val requestToServer = RequestToServer
    //자동로그인에 필요한 변수
    var autologincheck : Boolean = false
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        ///// login_auto  로그인체크박스 id

        login_btn_autologin_picktouchguide.setOnClickListener {
            if (autologincheck == false) {//false
                login_btn_autologin_picktouchguide.setBackgroundResource(R.drawable.login_btn_autologinagree_pick)
                autologincheck = true
                //MySharedPreferences.islogin = true
            } else {//true
                login_btn_autologin_picktouchguide.setBackgroundResource(R.drawable.login_b_btn_autologin_disagree_pick)
                autologincheck = false
                MySharedPreferences.islogin = false
            }
        }
        
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
                    onError = { showToast("아이디/비밀번호를 확인하세요!") },
                    onSuccess = {
                        if (it.success) {
                            /////
                            if(autologincheck) {
                                MySharedPreferences.email = et_email.text.toString()
                                MySharedPreferences.password = et_pw.text.toString()
                                MySharedPreferences.islogin = true
                                //.clear()
                            }
                            /////

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



