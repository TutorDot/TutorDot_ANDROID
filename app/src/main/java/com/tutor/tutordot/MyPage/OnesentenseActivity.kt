package com.tutor.tutordot.MyPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tutor.tutordot.R
import kotlinx.android.synthetic.main.activity_myinfo.*
import kotlinx.android.synthetic.main.activity_onesentense.*

class OnesentenseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onesentense)

        schedule_add_btn_cancle.setOnClickListener{
            val intent2= Intent(this, MyFragment::class.java)
            startActivity(intent2)
            finish()
        }
    }
}