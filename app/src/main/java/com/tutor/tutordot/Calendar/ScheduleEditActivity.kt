package com.tutor.tutordot.Calendar

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.tutor.tutordot.Calendar.Server.CalendarLogRequestToServer
import com.tutor.tutordot.Calendar.Server.ScheduleAddResponse
import com.tutor.tutordot.Calendar.Server.ScheduleEditRequest
import com.tutor.tutordot.Calendar.Server.ScheduleEditResponse
import com.tutor.tutordot.CalenderActivity
import com.tutor.tutordot.ClassLog.LogdateRecyclerView.modi_check
import com.tutor.tutordot.ClassLog.LogdateRecyclerView.ser_hw
import com.tutor.tutordot.ClassLog.LogdateRecyclerView.ser_progress
import com.tutor.tutordot.ClassLog.Server.LogModiRequest
import com.tutor.tutordot.ClassLog.Server.LogRequestToServer
import com.tutor.tutordot.ClassLog.complete
import com.tutor.tutordot.R
import com.tutor.tutordot.extention.customEnqueue
import com.tutor.tutordot.extention.showToast
import kotlinx.android.synthetic.main.activity_class_log_modification.*
import kotlinx.android.synthetic.main.activity_schedule_add.*
import kotlinx.android.synthetic.main.activity_schedule_edit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ScheduleEditActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_edit)

        var start_time : String = ""
        var end_time : String = ""
        var date_time : String = ""

        //서버 연결
        val calendarLogRequestToServer = CalendarLogRequestToServer

        // 날짜 선택
        date_picker_edit.setOnDateChangedListener{
                view, year, monthOfYear, dayOfMonth ->
            schedule_edit_date_txt.text = "${year}" + "-${monthOfYear+1}" + "-${dayOfMonth}"

            // 날짜 포맷 통일
            if (monthOfYear < 10) {
                schedule_edit_date_txt.text = "${year}" + "-0${monthOfYear+1}" + "-${dayOfMonth}"
                //Log.i("shot_Day test", "${schedule_edit_date_txt.text}")
            }
            if (dayOfMonth < 10) {
                schedule_edit_date_txt.text = "${year}" + "-${monthOfYear+1}" + "-0${dayOfMonth}"
            }
            if (monthOfYear < 10 && dayOfMonth < 10) {
                schedule_edit_date_txt.text = "${year}" + "-0${monthOfYear+1}" + "-0${dayOfMonth}"
            }

            date_time = "${schedule_edit_date_txt.text}"
            Log.i("수정된 날짜: ", "${date_time}")


        }

        // 시간 선택
        // Set a time change listener for time picker widget
        time_picker_edit.setOnTimeChangedListener{
                view,hourOfDay,minute->
            schedule_edit_start_txt.text = "${getHourAMPM(hourOfDay)}" + ":$minute${getAMPM(hourOfDay)}"

            // 시간 포맷 통일
            if (getHourAMPM(hourOfDay) < 10) {
                schedule_edit_start_txt.text = "0${getHourAMPM(hourOfDay)}" + ":$minute${getAMPM(hourOfDay)}"
                //Log.i("time test1", "0${getHourAMPM(hourOfDay)}" + ":$minute ${getAMPM(hourOfDay)}")
            }

            if (minute < 10) {
                schedule_edit_start_txt.text = "${getHourAMPM(hourOfDay)}" + ":0$minute${getAMPM(hourOfDay)}"
            }
            if (getHourAMPM(hourOfDay) < 10 && minute < 10) {
                schedule_edit_start_txt.text = "0${getHourAMPM(hourOfDay)}" + ":0$minute${getAMPM(hourOfDay)}"
            }

            start_time = "${schedule_edit_start_txt.text}"
            Log.i("수정된 시간: ", "${start_time}")
        }

        // Set a time change listener for time picker widget
        time_picker2_edit.setOnTimeChangedListener{
                view,hourOfDay,minute->
            schedule_edit_end_txt.text = "${getHourAMPM(hourOfDay)} " + ": $minute${getAMPM(hourOfDay)}"

            // 시간 포맷 통일
            if (getHourAMPM(hourOfDay) < 10) {
                schedule_edit_end_txt.text = "0${getHourAMPM(hourOfDay)}" + ":$minute${getAMPM(hourOfDay)}"
                //Log.i("time test1", "0${getHourAMPM(hourOfDay)}" + ":$minute ${getAMPM(hourOfDay)}")
            }

            if (minute < 10) {
                schedule_edit_end_txt.text = "${getHourAMPM(hourOfDay)}" + ":0$minute${getAMPM(hourOfDay)}"
            }
            if (getHourAMPM(hourOfDay) < 10 && minute < 10) {
                schedule_edit_end_txt.text = "0${getHourAMPM(hourOfDay)}" + ":0$minute${getAMPM(hourOfDay)}"
            }

            end_time = "${schedule_edit_end_txt.text}"
            Log.i("수정된 시간: ", "${end_time}")
           }


        schedule_edit_location_txt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(searchtext: Editable?) {

            }

            override fun beforeTextChanged(searchtext: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(searchtext: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.i("수정된 장소: ", "${schedule_edit_location_txt.text}")
            }
        })


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

        // 취소 버튼 누르면 일정 정보 화면으로 이동
        schedule_edit_btn_cancle.setOnClickListener{
            finish()
        }

        //저장 버튼 누르면 일정 정보 화면으로 이동
        schedule_edit_btn_save.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                //서버에 전달
                calendarLogRequestToServer.service.scheduleEditRequest(
                    ScheduleEditRequest(
                        lectureId = 1,
                        date = date_time,
                        startTime = start_time,
                        endTime = end_time,
                        location = schedule_edit_location_txt.text.toString()
                    )//정보를 전달
                ).enqueue(object : Callback<ScheduleEditResponse> { // Callback 등록 (서버 통신 비동기적 요청)

                    // 비동기 요청 후 응답을 받았을 때 수행할 행동이 정의된 곳
                    override fun onFailure(call: Call<ScheduleEditResponse>, t: Throwable){
                        // 통신 실패
                        //Toast.makeText(this@ScheduleEditActivity, "통신 실패", Toast.LENGTH_SHORT).show()
                        Log.d("통신 실패","${t}")
                    }
                    override fun onResponse(
                        call: Call<ScheduleEditResponse>,
                        response: Response<ScheduleEditResponse>
                    ) {
                        // 통신 성공
                        if(response.isSuccessful){  // statusCode가 200-300 사이일 때, 응답 body 이용 가능
                            if(response.body()!!.success){  // ResponseLogin의 success가 true인 경우 -> 로그인
                                //Toast.makeText(this@ScheduleEditActivity, "추가 성공", Toast.LENGTH_SHORT).show()
                                showToast("일정 수정이 완료되었습니다.")

                                Log.d("startTime","${schedule_edit_start_txt}")
                                Log.d("location","${schedule_edit_location_txt}")

                                val intent = Intent(this@ScheduleEditActivity, CalenderActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else{
                                //Toast.makeText(this@ScheduleAddActivity, "추가 실패", Toast.LENGTH_SHORT).show()
                                Log.d("수정 실패","수정 실패")
                            }
                        }
                    }
                })
                val backIntent = Intent(this@ScheduleEditActivity, CalenderActivity::class.java)
                startActivity(backIntent)
                finish()
            }
        })
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