package com.tutor.tutordot.Calendar

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import com.tutor.tutordot.Calendar.Server.CalendarLogRequestToServer
import com.tutor.tutordot.CalenderActivity
import com.tutor.tutordot.ClassLog.LogdateRecyclerView.modi_check
import com.tutor.tutordot.ClassLog.LogdateRecyclerView.ser_hw
import com.tutor.tutordot.ClassLog.LogdateRecyclerView.ser_progress
import com.tutor.tutordot.ClassLog.Server.LogModiRequest
import com.tutor.tutordot.ClassLog.complete
import com.tutor.tutordot.R
import com.tutor.tutordot.Startpage.myjwt
import com.tutor.tutordot.Startpage.role
import com.tutor.tutordot.extention.customEnqueue
import com.tutor.tutordot.extention.moveActi
import com.tutor.tutordot.extention.showToast
import kotlinx.android.synthetic.main.activity_class_log_modification.*
import kotlinx.android.synthetic.main.activity_myinfo.*
import kotlinx.android.synthetic.main.activity_schedule_add.*
import kotlinx.android.synthetic.main.activity_schedule_info.*
import kotlinx.android.synthetic.main.fragment_calender.*


class ScheduleInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_info)

        val calendarLogRequestToServer = CalendarLogRequestToServer

        // 튜티에겐 편집,삭제 버튼 보이지 않음
        if(role == "tutee"){
            schedule_info_edit.visibility = View.GONE
            schedule_delte_btn.visibility = View.GONE
        }

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


        //상단 수업 선택 메뉴 (2차 릴리즈 기능)
//        schedule_info_edit.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View?) {
//                val popup =
//                    PopupMenu(getApplicationContext(), iv_schedule_add)
//                //Inflating the Popup using xml file
//                popup.menuInflater
//                    .inflate(R.menu.calendar_popup_menu, popup.menu)
//
//                //registering popup with OnMenuItemClickListener
//                popup.setOnMenuItemClickListener { item ->
////                    schedule_add_select_txt.setText(item.title)
//                    when(item?.itemId){
//                        R.id.edit -> {
//                            val context: Context = v!!.context
//                            val nextIntent = Intent(context, ScheduleEditActivity::class.java)
//                            nextIntent.putExtra("color",color)
//                            nextIntent.putExtra("start",start)
//                            nextIntent.putExtra("end",end)
//                            nextIntent.putExtra("title",title)
//                            nextIntent.putExtra("date",date)
//                            nextIntent.putExtra("location",location)
//                            nextIntent.putExtra("mycid",cid)
//                            moveActi(nextIntent, v)
//                        }
//                        R.id.delete -> {
//                            //서버에 전달
//                            calendarLogRequestToServer.service.scheduleDeleteRequest(
//                                "${myjwt}","${cid}"
//                            ).customEnqueue(
//                                onError = { Log.d("올바르지 못한 요청입니다", "올바르지 못한 요청입니다") },
//                                onSuccess = {
//                                    if (it.success) {
//                                        Log.d("삭제 완료", "삭제 완료")
//                                        showToast("삭제가 완료되었습니다.")
//                                    } else {
//                                        Log.d("삭제 실패", "삭제 실패")
//                                    }
//                                }
//                            )
//                            val backIntent = Intent(this@ScheduleInfoActivity, CalenderActivity::class.java)
//                            startActivity(backIntent)
//                            finish()
//                        }
//                    }
//                    true
//                }
//
//                popup.show() //showing popup menu
//            }
//        })



        // 여기 xml로 바꾸면서 수정함 (팝업 적용) -> 2차 릴리즈때는 이 아래로 다 삭제하고 위의 주석된 걸로 사용
        schedule_info_edit.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val context: Context = v!!.context
                val nextIntent = Intent(context, ScheduleEditActivity::class.java)
                nextIntent.putExtra("color",color)
                nextIntent.putExtra("start",start)
                nextIntent.putExtra("end",end)
                nextIntent.putExtra("title",title)
                nextIntent.putExtra("date",date)
                nextIntent.putExtra("location",location)
                nextIntent.putExtra("mycid",cid)
                moveActi(nextIntent, v)
            }
        })


//        val calendarLogRequestToServer = CalendarLogRequestToServer
        schedule_delte_btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                //서버에 전달
                calendarLogRequestToServer.service.scheduleDeleteRequest(
                    "${myjwt}","${cid}"
                ).customEnqueue(
                    onError = { Log.d("올바르지 못한 요청입니다", "올바르지 못한 요청입니다") },
                    onSuccess = {
                        if (it.success) {
                            Log.d("삭제 완료", "삭제 완료")
                            showToast("삭제가 완료되었습니다.")
                        } else {
                            Log.d("삭제 실패", "삭제 실패")
                        }
                    }
                )
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