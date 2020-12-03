package com.tutor.tutordot.Startpage

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tutor.tutordot.R
import com.tutor.tutordot.extention.showToast
import kotlinx.android.synthetic.main.activity_agree.*

class AgreeActivity : AppCompatActivity() {
    var agree1 : Boolean = false
    var agree2 : Boolean = false
    var agreeAll : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agree)

        ll_agreeall.setOnClickListener {
            if (agreeAll == false){//false
                check_all.setImageResource(R.drawable.agree_img_check)
                check_notice.setImageResource(R.drawable.agree_img_check)
                check_personal.setImageResource(R.drawable.agree_img_check)
                agree1=true
                agree2=true
                agreeAll=true
            }else{
                check_all.setImageResource(R.drawable.agree_img_uncheck)
                check_notice.setImageResource(R.drawable.agree_img_uncheck)
                check_personal.setImageResource(R.drawable.agree_img_uncheck)
                agreeAll=false
            }
        }

        ll_agreenotice.setOnClickListener {
            if (agree1 == false){//false
                check_notice.setImageResource(R.drawable.agree_img_check)
                agree1=true
            }else{//true
                check_notice.setImageResource(R.drawable.agree_img_uncheck)
                agree1=false
            }
        }

        ll_agreepersonal.setOnClickListener {
            if (agree2 == false){//false
                check_personal.setImageResource(R.drawable.agree_img_check)
                agree2=true
            }else{//true
                check_personal.setImageResource(R.drawable.agree_img_uncheck)
                agree2=false
            }
        }

        btn_agreego.setOnClickListener{
            if(agree1 && agree2) {
                val intent = Intent(this@AgreeActivity, SignUpActivity::class.java)
                startActivity(intent)
                finish()
            }
            else
                showToast("약관에 동의하시면 서비스 이용이 가능합니다.")
        }

        btn_agreeback.setOnClickListener{
            val intent = Intent(this@AgreeActivity, LoginFor1stActivity::class.java)
            startActivity(intent)
            finish()
        }

        look1.setOnClickListener{
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/view/tutordot/서비스-이용약관"))
            startActivity(intent)
        }
        look2.setOnClickListener{
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/view/tutordot/개인정보처리방침"))
            startActivity(intent)
        }
    }
}