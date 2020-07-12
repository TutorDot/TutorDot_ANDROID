package com.tutor.tutordot.Calendar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.tutor.tutordot.CalenderActivity
import com.tutor.tutordot.R
import kotlinx.android.synthetic.main.activity_schedule_info.*

class ScheduleInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_info)

        schedule_info_edit.setOnClickListener{
            val intent = Intent(this, ScheduleEditActivity::class.java)
            startActivity(intent)
        }

        // 뒤로가기 버튼 이벤트
        btn_back.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val backIntent = Intent(this@ScheduleInfoActivity, CalenderActivity::class.java)
                //startActivity(backIntent)
                finish()
            }
        })
    }
}