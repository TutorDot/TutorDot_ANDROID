package com.tutor.tutordot.Calendar.CalendarLogRecyclerView

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tutor.tutordot.Calendar.ScheduleInfoActivity
import com.tutor.tutordot.ClassLog.ClassLogModificationActivity
import com.tutor.tutordot.R

class CalendarLogViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
    val tv_starttime = itemView.findViewById<TextView>(R.id.tv_starttime)
    val tv_endtime = itemView.findViewById<TextView>(R.id.tv_endtime)
    val img_color = itemView.findViewById<ImageView>(R.id.img_color)
    val tv_calendarlog_title = itemView.findViewById<TextView>(R.id.tv_calendarlog_title)
    val tv_calendarlog_times = itemView.findViewById<TextView>(R.id.tv_calendarlog_times)
    val tv_calendarlog_location = itemView.findViewById<TextView>(R.id.tv_calendarlog_location)

    //일지 아이템 수정
    val cal_log_item = itemView.findViewById<ConstraintLayout>(R.id.cal_log_item)

    fun bind(calendarLogData : CalendarLogData){
        tv_starttime.text = calendarLogData.starttime
        tv_endtime.text = calendarLogData.endtime
        tv_calendarlog_title.text = calendarLogData.title
        tv_calendarlog_times.text = calendarLogData.times.toString() + "회차 " + calendarLogData.studytime.toString() + "시간"
        tv_calendarlog_location.text = calendarLogData.location


        if(calendarLogData.img_color == "yellow")
            img_color.setImageResource(R.drawable.notice_color_img_yellow)
        if(calendarLogData.img_color == "green")
            img_color.setImageResource(R.drawable.notice_color_img_green)
        if(calendarLogData.img_color == "blue")
            img_color.setImageResource(R.drawable.notice_color_img_blue)
        if(calendarLogData.img_color == "purple")
            img_color.setImageResource(R.drawable.notice_color_img_purple)
        if(calendarLogData.img_color == "red")
            img_color.setImageResource(R.drawable.notice_color_img_red)


        //일지 아이템 버튼 클릭 이벤트
        cal_log_item.setOnClickListener(object :View.OnClickListener {
            override fun onClick(v: View?) {
                val context: Context = v!!.context
                val nextIntent = Intent(v!!.context, ScheduleInfoActivity::class.java)
                context.startActivity(nextIntent)
            }
        })
    }
}