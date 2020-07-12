package com.tutor.tutordot.MyPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tutor.tutordot.R
import kotlinx.android.synthetic.main.activity_onesentense.*

class AddclassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage_addclass)

        schedule_add_btn_cancle.setOnClickListener{
            val intent2= Intent(this, MyFragment::class.java)
            startActivity(intent2)
            finish()
        }
    }
}