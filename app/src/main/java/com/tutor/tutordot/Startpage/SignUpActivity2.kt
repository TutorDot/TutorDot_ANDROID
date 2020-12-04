package com.tutor.tutordot.Startpage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tutor.tutordot.R
import com.tutor.tutordot.extention.showToast
import kotlinx.android.synthetic.main.activity_sign_up2.*

var join_name : String = ""
class SignUpActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        actiList.add(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up2)

        btn_next2.setOnClickListener {
            if (et_name.text.isNullOrBlank()){
                showToast("이름을 입력해주세요.")
            }else{
                join_name = et_name.text.toString()
                val intent = Intent(this@SignUpActivity2, SignUpActivity3::class.java)
                startActivity(intent)
            }
        }
        btn_back2.setOnClickListener {
            val intent = Intent(this@SignUpActivity2, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}