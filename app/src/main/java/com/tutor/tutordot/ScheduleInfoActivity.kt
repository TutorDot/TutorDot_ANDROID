package com.tutor.tutordot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_schedule_info.*

class ScheduleInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_info)

        schedule_info_edit.setOnClickListener{
            val intent = Intent(this, ScheduleEditActivity::class.java)
            startActivity(intent)
        }
    }
}