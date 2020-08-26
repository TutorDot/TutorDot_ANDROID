package com.tutor.tutordot.Calendar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.tutor.tutordot.CalenderActivity
import com.tutor.tutordot.ClassLog.LogdateRecyclerView.modi_check
import com.tutor.tutordot.ClassLog.LogdateRecyclerView.ser_hw
import com.tutor.tutordot.ClassLog.LogdateRecyclerView.ser_progress
import com.tutor.tutordot.ClassLog.Server.LogModiRequest
import com.tutor.tutordot.ClassLog.complete
import com.tutor.tutordot.R
import com.tutor.tutordot.extention.customEnqueue
import com.tutor.tutordot.extention.showToast
import kotlinx.android.synthetic.main.activity_class_log_modification.*
import kotlinx.android.synthetic.main.activity_myinfo.*
import kotlinx.android.synthetic.main.activity_schedule_info.*

class ScheduleInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_info)
        //화면구현
        var color= intent.getStringExtra("color")
        var title= intent.getStringExtra("title")
        var start= intent.getStringExtra("start")
        var end= intent.getStringExtra("end")
        var date= intent.getStringExtra("date")
        var location= intent.getStringExtra("location")
        var cid=intent.getIntExtra("mycid", 0)

        if(color == "yellow")
            schedule_info_color.setImageResource(R.drawable.notice_color_img_yellow)
        if(color == "green")
            schedule_info_color.setImageResource(R.drawable.notice_color_img_green)
        if(color == "blue")
            schedule_info_color.setImageResource(R.drawable.notice_color_img_blue)
        if(color == "purple")
            schedule_info_color.setImageResource(R.drawable.notice_color_img_purple)
        if(color == "red")
            schedule_info_color.setImageResource(R.drawable.notice_color_img_red)

        schedule_info_txt.setText(title)
        schedule_info_date_txt.setText(date)
        schedule_info_start_txt.setText(start)
        schedule_info_end_txt.setText(end)
        schedule_info_location_txt.setText(location)



        schedule_info_edit.setOnClickListener {
            val intent = Intent(this, ScheduleEditActivity::class.java)
            startActivity(intent)
        }


        schedule_delte_btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                //서버에 전달
//                logRequestToServer.service.logModiRequest(
//                    LogModiRequest(
//                        classProgress = ser_progress,
//                        homework = ser_hw,
//                        hwPerformance = complete
//                    )//정보를 전달
//                ).customEnqueue(
//                    onError = { Log.d("올바르지 못한 요청입니다", "올바르지 못한 요청입니다") },
//                    onSuccess = {
//                        if (it.success) {
//                            Log.d("수정 완료", "수정 완료")
//                            showToast("수정이 완료되었습니다.")
//                        } else {
//                            Log.d("수정 실패", "수정 실패")
//                        }
//                    }
//                )
                val backIntent = Intent(this@ScheduleInfoActivity, CalenderActivity::class.java)
                startActivity(backIntent)
                finish()
            }
        })

        // 뒤로가기 버튼 이벤트
        btn_back.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                finish()
            }
        })
    }
}