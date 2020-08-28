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
import kotlinx.android.synthetic.main.activity_schedule_info.*

class ScheduleInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_info)

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