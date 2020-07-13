package com.tutor.tutordot.Calendar

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import com.tutor.tutordot.R
import kotlinx.android.synthetic.main.activity_schedule_add.*
import kotlinx.android.synthetic.main.activity_schedule_edit.*
import java.util.*

class ScheduleEditActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_edit)

        // 취소 버튼 누르면 일정 정보 화면으로 이동
        schedule_edit_btn_cancle.setOnClickListener{
            val intent = Intent(this, ScheduleInfoActivity::class.java)
            startActivity(intent)
        }

//        date_picker_edit.setOnDateChangedListener{
//
//        }

        // 날짜 선택
        // 날짜 선택
        date_picker_edit.setOnDateChangedListener{
                view, year, monthOfYear, dayOfMonth ->
            schedule_edit_date_txt.text = "${year}" + "년 ${monthOfYear+1}" + "월 ${dayOfMonth}" + "일"
        }

        // 시간 선택
        // Set a time change listener for time picker widget
        time_picker_edit.setOnTimeChangedListener{
                view,hourOfDay,minute->
            schedule_edit_start_txt.text = "${getHourAMPM(hourOfDay)} " + ": $minute ${getAMPM(hourOfDay)}"
        }

        // Set a time change listener for time picker widget
        time_picker2_edit.setOnTimeChangedListener{
                view,hourOfDay,minute->
            schedule_edit_end_txt.text = "${getHourAMPM(hourOfDay)} " + ": $minute ${getAMPM(hourOfDay)}"
        }


        schedule_edit_date_constraint.setOnClickListener{
            if(date_picker_edit.getVisibility() == View.GONE) {
                date_picker_edit.setVisibility(View.VISIBLE)
                fix4.text = "완료"
            } else {
                date_picker_edit.setVisibility(View.GONE)
                fix4.text = "수정"
            }
        }

        schedule_edit_start_constraint.setOnClickListener{
            if(time_picker_edit.getVisibility() == View.GONE) {
                time_picker_edit.setVisibility(View.VISIBLE)
                fix5.text = "완료"
            } else {
                time_picker_edit.setVisibility(View.GONE)
                fix5.text = "수정"
            }
        }

        schedule_edit_end_constraint.setOnClickListener{
            if(time_picker2_edit.getVisibility() == View.GONE) {
                time_picker2_edit.setVisibility(View.VISIBLE)
                fix6.text = "완료"
            } else {
                time_picker2_edit.setVisibility(View.GONE)
                fix6.text = "수정"
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