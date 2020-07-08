package com.tutor.tutordot

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TimePicker
import kotlinx.android.synthetic.main.activity_schedule_add.*
import java.util.*

class ScheduleAddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_add)


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


//        schedule_add_date_constraint.setOnClickListener{
//            if(date_picker.getVisibility() == View.GONE) {
//                time_picker.setVisibility(View.VISIBLE);
//            } else {
//                time_picker.setVisibility(View.GONE);
//                Log.d("선택", "아아이")
//            }
//        }

        schedule_add_start_constraint.setOnClickListener{
            if(time_picker.getVisibility() == View.GONE) {
                time_picker.setVisibility(View.VISIBLE);
            } else {
                time_picker.setVisibility(View.GONE);
            }
        }

        schedule_add_end_constraint.setOnClickListener{
            if(time_picker2.getVisibility() == View.GONE) {
                time_picker2.setVisibility(View.VISIBLE);
            } else {
                time_picker2.setVisibility(View.GONE);
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