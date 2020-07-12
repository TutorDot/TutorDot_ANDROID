package com.tutor.tutordot.MyPage

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.tutor.tutordot.CalenderActivity
import com.tutor.tutordot.R
import kotlinx.android.synthetic.main.activity_myclass_edit.*
import kotlinx.android.synthetic.main.activity_myinfo.*


class MyinfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myinfo)

        my_class_tap_btn_back.setOnClickListener(object : View.OnClickListener {
            override fun onClick(arg0: View?) {
                val it1 = Intent(applicationContext, CalenderActivity::class.java)
                startActivity(it1)
            }
        })

        my_class_tap_btn_edit.setOnClickListener{
            val intent2= Intent(this, MyclassEdit::class.java)
            startActivity(intent2)
            finish()
        }

        schedule_add_btn_cancle.setOnClickListener{
            val intent2= Intent(this, MyFragment::class.java)
            startActivity(intent2)
            finish()
        }
    }



}


