package com.tutor.tutordot.Startpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tutor.tutordot.R
import com.tutor.tutordot.StartServer.RequestSignup
import com.tutor.tutordot.StartServer.SignupRequestToServer
import com.tutor.tutordot.extention.customEnqueue
import com.tutor.tutordot.extention.showToast
import kotlinx.android.synthetic.main.activity_sign_up4.*

class SignUpActivity4 : AppCompatActivity() {

    val signupRequestToServer = SignupRequestToServer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up4)

        btn_back4.setOnClickListener {
            val intent = Intent(this@SignUpActivity4, SignUpActivity4::class.java)
            startActivity(intent)
        }
        btn_signup.setOnClickListener{
            if (et_pw.text.isNullOrBlank()||et_pw2.text.isNullOrBlank()){
                //Toast.makeText(this, "빈칸을 모두 입력하세요.", Toast.LENGTH_SHORT).show()
                showToast("모두 입력해주세요.")
            }else if(et_pw.text.toString() != et_pw2.text.toString()){
                //Toast.makeText(this, "비밀번호와 비밀번호확인이 같은지 확인하세요.", Toast.LENGTH_SHORT).show()
                showToast("비밀번호를 확인해주세요.")
            }else {

                signupRequestToServer.service.requestSignup(
                    RequestSignup(
                        userName = join_name,
                        email = join_id,
                        password = et_pw.text.toString(),
                        role = role
                    )//정보를 전달
                ).customEnqueue(
                    onError = { showToast("중복된 정보입니다.") },
                    onSuccess = {
                        if (it.success) {
                            showToast("회원가입 성공")
                            val intent =
                                Intent(this@SignUpActivity4, SignupActivity5::class.java)
                            intent.putExtra("check", "1")
                            intent.putExtra("email", join_id)
                            intent.putExtra("password", et_pw.text.toString())
                            startActivity(intent)
                            finish()
                        } else {
                            showToast("회원가입 실패")
                        }
                    }
                )
            }
        }
    }
}