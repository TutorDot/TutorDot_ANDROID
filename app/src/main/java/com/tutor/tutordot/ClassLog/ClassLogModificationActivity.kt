package com.tutor.tutordot.ClassLog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.tutor.tutordot.*
import com.tutor.tutordot.ClassLog.LogRecyclerView.complete
import com.tutor.tutordot.ClassLog.LogdateRecyclerView.modi_check
import com.tutor.tutordot.ClassLog.LogdateRecyclerView.ser_hw
import com.tutor.tutordot.ClassLog.LogdateRecyclerView.ser_progress
import com.tutor.tutordot.ClassLog.Server.LogModiRequest
import com.tutor.tutordot.ClassLog.Server.LogRequestToServer
import com.tutor.tutordot.extention.customEnqueue
import com.tutor.tutordot.extention.showToast
import kotlinx.android.synthetic.main.activity_class_log_modification.*

class ClassLogModificationActivity : AppCompatActivity() {

    //버튼 늘림 표시
    var circle : Boolean = false
    var triangel : Boolean = false
    var x : Boolean = false

    //서버 연결
    val logRequestToServer = LogRequestToServer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_log_modification)

        //EditText의 기본 텍스트는 사용자가 이전에 입력했던 값으로
        et_log_modi_progress.setText(ser_progress)
        et_log_modi_hw.setText(ser_hw)

        //취소, 저장 버튼 이벤트
        btn_log_cancel.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val backIntent = Intent(this@ClassLogModificationActivity, CalenderActivity::class.java)
                startActivity(backIntent)
                finish()
            }
        })

        btn_log_save.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                ser_progress = et_log_modi_progress.text.toString()
                ser_hw = et_log_modi_hw.text.toString()
                modi_check = true

                //서버에 전달
                logRequestToServer.service.logModiRequest(
                    LogModiRequest(
                        classProgress = ser_progress,
                        homework = ser_hw,
                        hwPerformance = complete
                    )//정보를 전달
                ).customEnqueue(
                    onError = {showToast("올바르지 못한 요청입니다")},
                    onSuccess = {
                        if (it.success) {
                            showToast("회원가입 성공")
                        } else {
                            //haveData = false
                        }
                    }
                )

                //val intent = Intent(this@ClassLogModificationActivity, LogViewHolder::class.java)
                //intent.putExtra("mprogress", ser_progress)
                //intent.putExtra("mhw", ser_hw)
                //intent.putExtra("mcheck", modi_check)
                //setResult(Activity.RESULT_OK, intent)
                val backIntent = Intent(this@ClassLogModificationActivity, CalenderActivity::class.java)
                startActivity(backIntent)
                finish()
            }
        })

        btn_modi_circle.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                //선택 안되어있을 경우
                if(!circle) {
                    circle = true
                    complete = 1

                    btn_modi_circle.setBackgroundResource(R.drawable.class_log_btn_circle_pick)
                    btn_modi_triangle.setBackgroundResource(R.drawable.class_log_btn_triangle_unpick)
                    btn_modi_x.setBackgroundResource(R.drawable.class_log_btn_x_unpick)
                }
                else {  //선택되어있을 경우
                    circle = false
                    complete = 0

                    btn_modi_circle.setBackgroundResource(R.drawable.class_log_btn_circle_unpick)
                    btn_modi_triangle.setBackgroundResource(R.drawable.class_log_btn_triangle_unpick)
                    btn_modi_x.setBackgroundResource(R.drawable.class_log_btn_x_unpick)
                }
            }
        })

        btn_modi_triangle.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                //선택 안되어있을 경우
                if(!triangel) {
                    triangel = true
                    complete = 2

                    btn_modi_circle.setBackgroundResource(R.drawable.class_log_btn_circle_unpick)
                    btn_modi_triangle.setBackgroundResource(R.drawable.class_log_btn_triangle_pick)
                    btn_modi_x.setBackgroundResource(R.drawable.class_log_btn_x_unpick)
                }
                else {  //선택되어있을 경우
                    triangel = false
                    complete = 0

                    btn_modi_circle.setBackgroundResource(R.drawable.class_log_btn_circle_unpick)
                    btn_modi_triangle.setBackgroundResource(R.drawable.class_log_btn_triangle_unpick)
                    btn_modi_x.setBackgroundResource(R.drawable.class_log_btn_x_unpick)
                }
            }
        })

        btn_modi_x.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                //선택 안되어있을 경우
                if(!x) {
                    x = true
                    complete = 3

                    btn_modi_circle.setBackgroundResource(R.drawable.class_log_btn_circle_unpick)
                    btn_modi_triangle.setBackgroundResource(R.drawable.class_log_btn_triangle_unpick)
                    btn_modi_x.setBackgroundResource(R.drawable.class_log_btn_x_pick)
                }
                else {  //선택되어있을 경우
                    x = false
                    complete = 0

                    btn_modi_circle.setBackgroundResource(R.drawable.class_log_btn_circle_unpick)
                    btn_modi_triangle.setBackgroundResource(R.drawable.class_log_btn_triangle_unpick)
                    btn_modi_x.setBackgroundResource(R.drawable.class_log_btn_x_unpick)
                }
            }
        })
    }
}