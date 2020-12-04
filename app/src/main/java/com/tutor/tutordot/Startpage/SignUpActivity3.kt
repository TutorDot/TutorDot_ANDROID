package com.tutor.tutordot.Startpage

import android.app.DownloadManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tutor.tutordot.R
import com.tutor.tutordot.StartServer.RequestDuplicate
import com.tutor.tutordot.StartServer.SignupRequestToServer
import com.tutor.tutordot.extention.customEnqueue
import com.tutor.tutordot.extention.progressOFF
import com.tutor.tutordot.extention.showToast
import kotlinx.android.synthetic.main.activity_sign_up3.*

var join_id : String = ""
class SignUpActivity3 : AppCompatActivity() {
    val signupRequestToServer = SignupRequestToServer
    var duplicate : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actiList.add(this)
        setContentView(R.layout.activity_sign_up3)

        btn_next3.setOnClickListener {
            if (et_id.text.isNullOrBlank()){
                showToast("아이디를 입력해주세요.")
            }else {
                if(duplicate){
                    join_id = et_id.text.toString()
                    val intent = Intent(this@SignUpActivity3, SignUpActivity4::class.java)
                    startActivity(intent)
                }
                else
                    showToast("아이디 중복을 확인해주세요.")
            }
        }

        id_duplacat.setOnClickListener{
            if (et_id.text.isNullOrBlank()){
                showToast("아이디를 입력해주세요.")
            }else {
                signupRequestToServer.service.requestDuplicate(
                    RequestDuplicate(
                        email = et_id.text.toString()
                    )
                ).customEnqueue(
                    onError = {
                        showToast("중복된 정보입니다.")
                    },
                    onSuccess = {
                        if (it.success) {
                            showToast("사용가능한 아이디입니다.")
                            duplicate = true
                        } else {
                            showToast("중복된 아이디입니다.")
                            duplicate = false
                        }
                    }
                )
            }
        }

        btn_back3.setOnClickListener {
            val intent = Intent(this@SignUpActivity3, SignUpActivity2::class.java)
            startActivity(intent)
            finish()
        }
    }
}