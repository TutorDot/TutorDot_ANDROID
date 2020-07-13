package com.tutor.tutordot.MyPage.MyclassEditRecylerView

import android.annotation.SuppressLint
import android.os.Build
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.TimePicker
import androidx.recyclerview.widget.RecyclerView
import com.tutor.tutordot.MyPage.MyclassEditRecylerView.MyclassEditData
import com.tutor.tutordot.R
import kotlinx.android.synthetic.main.activity_schedule_edit.*
import java.util.*

class MyclassEditViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tp_weekday=itemView.findViewById<TextView>(R.id.tp_weekday)
    val tp_start=itemView.findViewById<TextView>(R.id.tp_start)
    val tp_end=itemView.findViewById<TextView>(R.id.tp_end)
    val tp_myclass_time1=itemView.findViewById<TimePicker>(R.id.tp_myclass_time1)
    val tp_myclass_time2=itemView.findViewById<TimePicker>(R.id.tp_myclass_time2)
    val btn_start=itemView.findViewById<ImageButton>(R.id.btn_start)
    val btn_end=itemView.findViewById<ImageButton>(R.id.btn_end)


    @SuppressLint("SetTextI18n")
    fun bind(myclassEditData: MyclassEditData) {
        tp_weekday.text=myclassEditData.weekday
        tp_start.text=myclassEditData.starttime
        tp_end.text=myclassEditData.endtime

        // 시간 선택
        // Set a time change listener for time picker widget
        tp_myclass_time1.setOnTimeChangedListener{
                view,hourOfDay,minute->
            tp_start.text = "${getHourAMPM(hourOfDay)} " + ": $minute ${getAMPM(hourOfDay)}"

        }
        // Set a time change listener for time picker widget
        tp_myclass_time2.setOnTimeChangedListener{
                view,hourOfDay,minute->
            tp_end.text = "${getHourAMPM(hourOfDay)} " + ": $minute ${getAMPM(hourOfDay)}"
        }

        btn_start.setOnClickListener{
            if(tp_myclass_time1.getVisibility() == View.GONE) {
                tp_myclass_time1.setVisibility(View.VISIBLE)
                tp_myclass_time2.setVisibility(View.GONE)
            } else {
                tp_myclass_time1.setVisibility(View.GONE)
            }
        }

        btn_end.setOnClickListener{
            if(tp_myclass_time2.getVisibility() == View.GONE) {
                tp_myclass_time2.setVisibility(View.VISIBLE)
                tp_myclass_time1.setVisibility(View.GONE)
            } else {
                tp_myclass_time2.setVisibility(View.GONE)
            }
        }

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

    // Custom method to get time picker current time as string
    private fun getPickerTime(timePicker: TimePicker):String{
        val hour = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePicker.hour
        } else {
            timePicker.currentHour
        }

        val minute = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val geeks = listOf<Int>(0, 30)
            timePicker.minute
        }else{
            timePicker.currentMinute
        }

        return "${getHourAMPM(hour)} : $minute ${getAMPM(hour)}"
    }

    // Custom method to get a random number from the provided range
    private fun randomInRange(min:Int, max:Int):Int{
        //define a new Random class
        val r = Random()

        //get the next random number within range
        // Including both minimum and maximum number
        return r.nextInt((max - min) + 1) + min;
    }

    private fun randomInRange2(start:Int):Int{
        //define a new Random class
        val r = Random()

        //get the next random number within range
        // Including both minimum and maximum number
        //return r.nextInt((max - min) + 1) + min;
        return r.nextInt(start + 30);
    }
    // Custom method to set time picker time
    private fun setPickerTime(timePicker: TimePicker){
        // Get random time
        val hour = randomInRange(0,23)
        val minute = randomInRange2(0)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePicker.hour = hour
            timePicker.minute = minute
        }else{
            timePicker.currentHour = hour
            timePicker.currentMinute = minute
        }
    }

}