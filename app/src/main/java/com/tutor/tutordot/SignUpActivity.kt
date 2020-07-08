package com.tutor.tutordot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.비밀번호
import kotlinx.android.synthetic.main.activity_sign_up.이메일

class SignUpActivity : AppCompatActivity() {

    //버튼 늘림 표시
    var tutor : Boolean = false
    var tutee : Boolean = false
    var agree : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        signup_btn_tutor.setOnClickListener{
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
                Toast.makeText(this, "빈칸을 모두 입력하세요.", Toast.LENGTH_SHORT).show()
            }else if(비밀번호.text.toString() != 비밀번호확인.text.toString()){
                Toast.makeText(this, "비밀번호와 비밀번호확인이 같은지 확인하세요.", Toast.LENGTH_SHORT).show()
            }else if(!agree){
                Toast.makeText(this, "이용약관 및 개인정보보호정책에 동의해주세요.", Toast.LENGTH_SHORT).show()
            } else if(!tutor && !tutee){
                Toast.makeText(this, "역할중 하나를 선택해주세요.", Toast.LENGTH_SHORT).show()
            }
            else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }




    }
}