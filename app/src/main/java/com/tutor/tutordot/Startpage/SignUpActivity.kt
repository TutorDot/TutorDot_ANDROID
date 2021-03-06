package com.tutor.tutordot.Startpage

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tutor.tutordot.R
import com.tutor.tutordot.StartServer.RequestSignup
import com.tutor.tutordot.StartServer.SignupRequestToServer
import com.tutor.tutordot.extention.customEnqueue
import com.tutor.tutordot.extention.showToast
import kotlinx.android.synthetic.main.activity_sign_up.*


//var role: String = "tutor"
val actiList:ArrayList<Activity> = java.util.ArrayList()
class SignUpActivity : AppCompatActivity() {


    //버튼 늘림 표시
    var tutor : Boolean = false
    var tutee : Boolean = false
    var agree : Boolean = false

    val signupRequestToServer = SignupRequestToServer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actiList.add(this)
        setContentView(R.layout.activity_sign_up)

        signup_btn_tutor.setOnClickListener{
            //알림, 수업일지에 쓰임, 사용자 역할을 알기 위해 추가(기현)
            role = "tutor"
            
            if (tutor == false){//false
                signup_btn_tutor.setBackgroundResource(R.drawable.signup_btn_tutor_1)
                signup_btn_tutee.setBackgroundResource(R.drawable.signup_btn_tutee_2)
                tutor=true
            }else{//true
                //tutor=false
                signup_btn_tutor.setBackgroundResource(R.drawable.signup_btn_tutor_3)
                tutor=false
            }

        }
        signup_btn_tutee.setOnClickListener{
            //기현 추가
            role = "tutee"
            
            if (tutee == false){//false
                signup_btn_tutee.setBackgroundResource(R.drawable.signup_btn_tutee_4)
                signup_btn_tutor.setBackgroundResource(R.drawable.signup_btn_tutor_3)
                tutee=true
            }else{//true
                //tutor=false
                signup_btn_tutee.setBackgroundResource(R.drawable.signup_btn_tutee_2)
                tutee=false
            }

        }
        btn_next.setOnClickListener {
            if(!tutor && !tutee){
                showToast("역할중 하나를 선택해주세요.")
            }
            else {
                val intent = Intent(this@SignUpActivity, SignUpActivity2::class.java)
                startActivity(intent)
            }
        }
        btn_back.setOnClickListener {
            val intent = Intent(this@SignUpActivity, LoginFor1stActivity::class.java)
            startActivity(intent)
            finish()
        }
        /*
        signup_btn_agree.setOnClickListener{
            if (agree == false){//false
                signup_btn_agree.setBackgroundResource(R.drawable.signup_btn_agree_pick)
                agree=true
            }else{//true
                //tutor=false
                signup_btn_agree.setBackgroundResource(R.drawable.signup_btn_agree)
                agree=false
            }

        }

        signup_btn_signup.setOnClickListener{
            if (이메일.text.isNullOrBlank()||비밀번호.text.isNullOrBlank()||이름.text.isNullOrBlank()||비밀번호확인.text.isNullOrBlank()){
                //Toast.makeText(this, "빈칸을 모두 입력하세요.", Toast.LENGTH_SHORT).show()
                showToast("모두 입력해주세요.")
            }else if(비밀번호.text.toString() != 비밀번호확인.text.toString()){
                //Toast.makeText(this, "비밀번호와 비밀번호확인이 같은지 확인하세요.", Toast.LENGTH_SHORT).show()
                showToast("비밀번호를 확인해주세요.")
            }else if(!agree){
                //Toast.makeText(this, "이용약관 및 개인정보보호정책에 동의해주세요.", Toast.LENGTH_SHORT).show()
                showToast("이용약관 및 개인정보보호정책에 동의해주세요.")
            } else if(!tutor && !tutee){
                //Toast.makeText(this, "역할중 하나를 선택해주세요.", Toast.LENGTH_SHORT).show()
                showToast("역할중 하나를 선택해주세요.")
            }
            else{
                signupRequestToServer.service.requestSignup(
                    RequestSignup(
                        userName = 이름.text.toString(),
                        email = 이메일.text.toString(),
                        password = 비밀번호.text.toString(),
                        role = role
                    )//정보를 전달
                ).customEnqueue(
                    onError = {showToast("중복된 정보입니다.")},
                    onSuccess = {
                        if (it.success) {
                            showToast("회원가입 성공")
                            val intent = Intent(this@SignUpActivity, LoginFor1stActivity::class.java)
                            intent.putExtra("check", "1")
                            intent.putExtra("email", 이메일.text.toString())
                            intent.putExtra("password", 비밀번호.text.toString())
                            startActivity(intent)
                            finish()
                        } else {
                            showToast("내용을 확인하세요!")
                        }
                    }
                )
            }
        }*/
    }
}