package com.tutor.tutordot.ClassLog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.tutor.tutordot.*
import com.tutor.tutordot.ClassLog.LogdateRecyclerView.*
import com.tutor.tutordot.ClassLog.Server.LogModiRequest
import com.tutor.tutordot.ClassLog.Server.LogRequestToServer
import com.tutor.tutordot.Startpage.myjwt
import com.tutor.tutordot.extention.customEnqueue
import com.tutor.tutordot.extention.progressOFF
import com.tutor.tutordot.extention.progressON
import com.tutor.tutordot.extention.showToast
import kotlinx.android.synthetic.main.activity_class_log_modification.*
import kotlinx.android.synthetic.main.fragment_my.*
import kotlinx.android.synthetic.main.item_classlog.*
import kotlin.math.log

var complete : Int = 0

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

        var mydiaryId:Int=0
        var mycolor: String=""
        var mytimes: String=""
        var myprogress: String=""
        var mycomplete: Int=0
        var myhomework: String=""
        mydiaryId= intent.getIntExtra("diaryId", 0)
        mycomplete= intent.getIntExtra("mycomplete", 0)
        mycolor= intent.getStringExtra("mycolor")
        mytimes= intent.getStringExtra("mytimes")
        myprogress= intent.getStringExtra("myprogress")
        myhomework= intent.getStringExtra("myhomework")




        //EditText의 기본 텍스트는 사용자가 이전에 입력했던 값으로
        //et_log_modi_progress.setText(ser_progress)
        et_log_modi_progress.setHint(myprogress)
        //et_log_modi_hw.setText(ser_hw)
        et_log_modi_hw.setHint(myhomework)

        //tv_log_modi_title.setText(ser_date_times.toString() + "회차 " +  ser_date_studytime.toString() + "시간 / " + ser_date_alltime.toString() + "시간")
        tv_log_modi_title.setText(mytimes)



        if (mycomplete == 0) {
            btn_modi_circle.setBackgroundResource(R.drawable.class_log_btn_circle_unpick)
            btn_modi_triangle.setBackgroundResource(R.drawable.class_log_btn_triangle_unpick)
            btn_modi_x.setBackgroundResource(R.drawable.class_log_btn_x_unpick)
        }
        if (mycomplete == 1) {
            btn_modi_circle.setBackgroundResource(R.drawable.class_log_btn_circle_pick)
            btn_modi_triangle.setBackgroundResource(R.drawable.class_log_btn_triangle_unpick)
            btn_modi_x.setBackgroundResource(R.drawable.class_log_btn_x_unpick)
        }
        if(mycomplete == 2) {
            btn_modi_circle.setBackgroundResource(R.drawable.class_log_btn_circle_unpick)
            btn_modi_triangle.setBackgroundResource(R.drawable.class_log_btn_triangle_pick)
            btn_modi_x.setBackgroundResource(R.drawable.class_log_btn_x_unpick)
        }
        if(mycomplete == 3) {
            btn_modi_circle.setBackgroundResource(R.drawable.class_log_btn_circle_unpick)
            btn_modi_triangle.setBackgroundResource(R.drawable.class_log_btn_triangle_unpick)
            btn_modi_x.setBackgroundResource(R.drawable.class_log_btn_x_pick)
        }

        if(mycolor == "yellow")
            iv_log_modi_color.setImageResource(R.drawable.notice_color_img_yellow)
        if(mycolor == "green")
            iv_log_modi_color.setImageResource(R.drawable.notice_color_img_green)
        if(mycolor == "blue")
            iv_log_modi_color.setImageResource(R.drawable.notice_color_img_blue)
        if(mycolor == "purple")
            iv_log_modi_color.setImageResource(R.drawable.notice_color_img_purple)
        if(mycolor == "red")
            iv_log_modi_color.setImageResource(R.drawable.notice_color_img_red)

        //취소, 저장 버튼 이벤트
        btn_log_cancel.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                //val backIntent = Intent(this@ClassLogModificationActivity, CalenderActivity::class.java)
                //startActivity(backIntent)
                finish()
            }
        })

        btn_log_save.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                progressON(this@ClassLogModificationActivity)
                ser_progress = et_log_modi_progress.text.toString()
                ser_hw = et_log_modi_hw.text.toString()
                modi_check = true

                //서버에 전달
                logRequestToServer.service.logModiRequest(
                    "${myjwt}",
                    LogModiRequest(
                        classProgress = ser_progress,
                        homework = ser_hw,
                        hwPerformance = complete
                    ),"${mydiaryId}"
                ).customEnqueue(
                    onError = {Log.d("올바르지 못한 요청입니다","올바르지 못한 요청입니다")},
                    onSuccess = {
                        if (it.success) {
                            Log.d("수정 완료","수정 완료")
                            showToast("수정이 완료되었습니다.")
                            progressOFF()
                            finish()
                        } else {
                            Log.d("수정 실패","수정 실패")
                        }
                    }
                )

                //val intent = Intent(this@ClassLogModificationActivity, LogViewHolder::class.java)
                //intent.putExtra("mprogress", ser_progress)
                //intent.putExtra("mhw", ser_hw)
                //intent.putExtra("mcheck", modi_check)
                //setResult(Activity.RESULT_OK, intent)
                //val backIntent = Intent(this@ClassLogModificationActivity, CalenderActivity::class.java)
                //startActivity(backIntent)
                //finish()
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

