package com.tutor.tutordot.Calendar

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import com.tutor.tutordot.R
import kotlinx.android.synthetic.main.activity_schedule_add.*
import kotlinx.android.synthetic.main.activity_schedule_add.fix1
import kotlinx.android.synthetic.main.activity_schedule_add.fix2
import kotlinx.android.synthetic.main.activity_schedule_add.fix3
import kotlinx.android.synthetic.main.activity_schedule_edit.*
import kotlinx.android.synthetic.main.fragment_calender.*
import java.util.*

class ScheduleAddActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_add)

        // 취소 버튼 누르면 캘린더뷰로 이동
        schedule_add_btn_cancle.setOnClickListener{
            finish()
        }

        //상단 수업 선택 메뉴
//        calendar_select.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View?) {
//                val popup =
//                    PopupMenu(context, calendar_select)
//                //Inflating the Popup using xml file
//                popup.menuInflater
//                    .inflate(R.menu.popup_menu, popup.menu)
//
//                //registering popup with OnMenuItemClickListener
//                popup.setOnMenuItemClickListener { item ->
//                    tv_calendar_title.text = item.title
//
//                    true
//                }
//                popup.show() //showing popup menu
//            }
//        })


        // 날짜 선택
        date_picker.setOnDateChangedListener{
            view, year, monthOfYear, dayOfMonth ->
            schedule_add_date_txt.text = "${year}" + "년 ${monthOfYear+1}" + "월 ${dayOfMonth}" + "일"
        }

        // 시간 선택
        // Set a time change listener for time picker widget
        time_picker.setOnTimeChangedListener{
                view,hourOfDay,minute->
            schedule_add_start_txt.text = "${getHourAMPM(hourOfDay)} " + ": $minute ${getAMPM(hourOfDay)}"
        }

        // Set a time change listener for time picker widget
        time_picker2.setOnTimeChangedListener{
                view,hourOfDay,minute->
            schedule_add_end_txt.text = "${getHourAMPM(hourOfDay)} " + ": $minute ${getAMPM(hourOfDay)}"
        }


        schedule_add_date_constraint.setOnClickListener{
            if(date_picker.getVisibility() == View.GONE) {
                date_picker.setVisibility(View.VISIBLE)
                fix1.text = "완료"
            } else {
                date_picker.setVisibility(View.GONE)
                fix1.text = "수정"
            }
        }

        schedule_add_start_constraint.setOnClickListener{
            if(time_picker.getVisibility() == View.GONE) {
                time_picker.setVisibility(View.VISIBLE)
                fix2.text = "완료"
            } else {
                time_picker.setVisibility(View.GONE)
                fix2.text = "수정"
            }
        }

        schedule_add_end_constraint.setOnClickListener{
            if(time_picker2.getVisibility() == View.GONE) {
                time_picker2.setVisibility(View.VISIBLE)
                fix3.text = "완료"
            } else {
                time_picker2.setVisibility(View.GONE)
                fix3.text = "수정"
            }
        }
    }


    // Custom method to get time picker current time as string
    private fun getPickerTime(timePicker: TimePicker):String{
        val hour = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePicker.hour
        } else {
            timePicker.currentHour
        }

        val minute = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            timePicker.minute
        }else{
            timePicker.currentMinute
        }

        return "${getHourAMPM(hour)} : $minute ${getAMPM(hour)}"
    }


    // Custom method to get AM PM value from provided hour
    private fun getAMPM(hour:Int):String{
        return if(hour>11)"PM" else "AM"
    }


    // Custom method to get hour for AM PM time format
    private fun getHourAMPM(hour:Int):Int{
        // Return the hour value for AM PM time format
        var modifiedHour = if (hour>11)hour-12 else hour
        if (modifiedHour == 0){modifiedHour = 12}
        return modifiedHour
    }


    // Custom method to set time picker time
    private fun setPickerTime(timePicker: TimePicker){
        // Get random time
        val hour = randomInRange(0,23)
        val minute = randomInRange(0,59)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePicker.hour = hour
            timePicker.minute = minute
        }else{
            timePicker.currentHour = hour
            timePicker.currentMinute = minute
        }
    }


    // Custom method to get a random number from the provided range
    private fun randomInRange(min:Int, max:Int):Int{
        //define a new Random class
        val r = Random()

        //get the next random number within range
        // Including both minimum and maximum number
        return r.nextInt((max - min) + 1) + min;
    }
}