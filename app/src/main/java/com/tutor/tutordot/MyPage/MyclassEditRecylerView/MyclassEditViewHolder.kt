package com.tutor.tutordot.MyPage.MyclassEditRecylerView

import android.annotation.SuppressLint
import android.os.Build
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.tutor.tutordot.R
import java.util.*


class MyclassEditViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tp_weekday=itemView.findViewById<TextView>(R.id.tp_weekday)
    val tp_start=itemView.findViewById<TextView>(R.id.tp_start)
    val tp_end=itemView.findViewById<TextView>(R.id.tp_end)

    val tp_day=itemView.findViewById<NumberPicker>(R.id.tp_day)
    val tp_myclass_time1=itemView.findViewById<TimePicker>(R.id.tp_myclass_time1)
    val tp_myclass_time2=itemView.findViewById<NumberPicker>(R.id.tp_myclass_time2)
    val endtime_set= itemView.findViewById<ConstraintLayout>(R.id.layoutthird)

    val btn_day=itemView.findViewById<ImageButton>(R.id.btn_day)
    val btn_start=itemView.findViewById<ImageButton>(R.id.btn_start)
    val btn_end=itemView.findViewById<ImageButton>(R.id.btn_end)
    val btn_x=itemView.findViewById<ImageButton>(R.id.btn_x)
    val btn_save=itemView.findViewById<Button>(R.id.btn_save)




    @SuppressLint("SetTextI18n")
    fun bind(myclassEditData: MyclassEditData) {
        val adata= mutableListOf<MyclassEditData>()

        tp_weekday.text=myclassEditData.weekday
        tp_start.text=myclassEditData.starttime
        tp_end.text=myclassEditData.endtime

        val dayvalues = arrayOf("월", "화", "수", "목", "금", "토", "일")
        tp_day.minValue=0
        tp_day.maxValue=dayvalues.size-1
        tp_day.displayedValues= dayvalues

        val timevalues = arrayOf("30분", "1시간", "1시간 30분", "2시간", "2시간 30분", "3시간", "3시간 30분", "4시간", "4시간 30분", "5시간")
        tp_myclass_time2.minValue=0
        tp_myclass_time2.maxValue=timevalues.size-1
        tp_myclass_time2.displayedValues= timevalues


        var tmptime: Int = 2
        var tmpmin: Int = 30
        var howlongh: Int = 1
        var howlongm: Int = 30
        var assign: Int
        var zero: String = "0"



        // 시간 선택하면 글씨 나오게
        // Set a time change listener for time picker widget

        tp_myclass_time1.setOnTimeChangedListener{
                view,hourOfDay,minute->
            if(minute/10==0) zero="0" else zero=""
            tp_start.text = "${getHourAMPM(hourOfDay)}" + ":"+zero+ "$minute ${getAMPM(hourOfDay)}"
            tmptime=hourOfDay
            tmpmin=minute
        }
        // Set a time change listener for time picker widget
        tp_myclass_time2.setOnValueChangedListener{
                numberPicker, oldValue, newValue ->
                howlongh=(newValue+1)/2
                howlongm=((newValue+1)%2)*30
            assign=((tmpmin+howlongm)%60)/10
            if( assign== 0) zero="0" else zero=""
            tp_end.text = "${getHourAMPM(tmptime+howlongh)}" + ":"+ zero +"${(tmpmin+howlongm)%60} ${getAMPM(tmptime+howlongh)}"

        }
        tp_day.setOnValueChangedListener{
            numberPicker, oldValue, newValue -> tp_weekday.text= "${dayvalues[newValue]}"
        }



        //버튼누르면 피커뷰 나오게
        btn_start.setOnClickListener{
            if(tp_myclass_time1.getVisibility() == View.GONE) {
                tp_myclass_time1.setVisibility(View.VISIBLE)
                endtime_set.setVisibility(View.GONE)
                tp_day.setVisibility(View.GONE)
            } else {
                tp_myclass_time1.setVisibility(View.GONE)
            }
        }
        btn_end.setOnClickListener{
            if(endtime_set.getVisibility() == View.GONE) {
                endtime_set.setVisibility(View.VISIBLE)
                tp_myclass_time1.setVisibility(View.GONE)
                tp_day.setVisibility(View.GONE)
            } else {
                endtime_set.setVisibility(View.GONE)
            }
        }
        btn_day.setOnClickListener{
            if(tp_day.getVisibility() == View.GONE) {
                endtime_set.setVisibility(View.GONE)
                tp_myclass_time1.setVisibility(View.GONE)
                tp_day.setVisibility(View.VISIBLE)
            } else {
                tp_day.setVisibility(View.GONE)
            }
        }

        btn_save.setOnClickListener(){
            tp_day.setVisibility(View.GONE)
            tp_myclass_time1.setVisibility(View.GONE)
            endtime_set.setVisibility(View.GONE)
            adata.add(
                MyclassEditData(
                    weekday = tp_weekday.text.toString(),
                    starttime = tp_start.text.toString(),
                    endtime = tp_end.text.toString()
                )
            )
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

    // Custom method to set time picker time
    private fun setPickerTime(timePicker: MutableList<Any>){
        // Get random time
        val hour = randomInRange(0,23)
        val min = randomInRange(0, 59)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            timePicker.hour = hour
//            timePicker.minute = minute
        }else{
//            timePicker.currentHour = hour
//            timePicker.currentMinute = minute
        }
    }

}