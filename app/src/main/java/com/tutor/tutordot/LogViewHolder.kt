package com.tutor.tutordot

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LogViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val tv_times = itemView.findViewById<TextView>(R.id.tv_calendarlog_title)
    val tv_progress = itemView.findViewById<TextView>(R.id.tv_progress)
    val tv_homework = itemView.findViewById<TextView>(R.id.tv_calendarlog_times)

    val btn_circle = itemView.findViewById<ImageButton>(R.id.btn_circle)
    val btn_triangle = itemView.findViewById<ImageButton>(R.id.btn_triangle)
    val btn_x = itemView.findViewById<ImageButton>(R.id.btn_x)

    val iv_color = itemView.findViewById<ImageView>(R.id.img_color)

    //버튼 늘림 표시
    var circle : Boolean = false
    var triangel : Boolean = false
    var x : Boolean = false


    fun bind(logData : LogData) {
        tv_times.text = logData.times.toString() + "회차 " + logData.studytime.toString() + "시간 / " + logData.alltime.toString() + "시간"
        tv_progress.text = "진도 : " + logData.progress
        tv_homework.text = "숙제 : " + logData.homework


        btn_circle.setOnClickListener(object :View.OnClickListener {
            override fun onClick(v: View?) {
                //선택 안되어있을 경우
                if(!circle) {
                    circle = true
                    logData.complete = 1
                    btn_circle.setBackgroundResource(R.drawable.class_log_btn_circle_pick)
                    btn_triangle.setBackgroundResource(R.drawable.class_log_btn_triangle_unpick)
                    btn_x.setBackgroundResource(R.drawable.class_log_btn_x_unpick)
                }
                else {  //선택되어있을 경우
                    circle = false
                    logData.complete = 0
                    btn_circle.setBackgroundResource(R.drawable.class_log_btn_circle_unpick)
                    btn_triangle.setBackgroundResource(R.drawable.class_log_btn_triangle_unpick)
                    btn_x.setBackgroundResource(R.drawable.class_log_btn_x_unpick)
                }
            }
        })

        btn_triangle.setOnClickListener(object :View.OnClickListener {
            override fun onClick(v: View?) {
                //선택 안되어있을 경우
                if(!triangel) {
                    triangel = true
                    logData.complete = 2
                    btn_circle.setBackgroundResource(R.drawable.class_log_btn_circle_unpick)
                    btn_triangle.setBackgroundResource(R.drawable.class_log_btn_triangle_pick)
                    btn_x.setBackgroundResource(R.drawable.class_log_btn_x_unpick)
                }
                else {  //선택되어있을 경우
                    triangel = false
                    logData.complete = 0
                    btn_circle.setBackgroundResource(R.drawable.class_log_btn_circle_unpick)
                    btn_triangle.setBackgroundResource(R.drawable.class_log_btn_triangle_unpick)
                    btn_x.setBackgroundResource(R.drawable.class_log_btn_x_unpick)
                }
            }
        })

        btn_x.setOnClickListener(object :View.OnClickListener {
            override fun onClick(v: View?) {
                //선택 안되어있을 경우
                if(!x) {
                    x = true
                    logData.complete = 3
                    btn_circle.setBackgroundResource(R.drawable.class_log_btn_circle_unpick)
                    btn_triangle.setBackgroundResource(R.drawable.class_log_btn_triangle_unpick)
                    btn_x.setBackgroundResource(R.drawable.class_log_btn_x_pick)
                }
                else {  //선택되어있을 경우
                    x = false
                    logData.complete = 0
                    btn_circle.setBackgroundResource(R.drawable.class_log_btn_circle_unpick)
                    btn_triangle.setBackgroundResource(R.drawable.class_log_btn_triangle_unpick)
                    btn_x.setBackgroundResource(R.drawable.class_log_btn_x_unpick)
                }
            }
        })


        if (logData.complete == 0) {
            btn_circle.setBackgroundResource(R.drawable.class_log_btn_circle_unpick)
            btn_triangle.setBackgroundResource(R.drawable.class_log_btn_triangle_unpick)
            btn_x.setBackgroundResource(R.drawable.class_log_btn_x_unpick)
        }
        if (logData.complete == 1) {
            btn_circle.setBackgroundResource(R.drawable.class_log_btn_circle_pick)
            btn_triangle.setBackgroundResource(R.drawable.class_log_btn_triangle_unpick)
            btn_x.setBackgroundResource(R.drawable.class_log_btn_x_unpick)
        }
        if(logData.complete == 2) {
            btn_circle.setBackgroundResource(R.drawable.class_log_btn_circle_unpick)
            btn_triangle.setBackgroundResource(R.drawable.class_log_btn_triangle_pick)
            btn_x.setBackgroundResource(R.drawable.class_log_btn_x_unpick)
        }
        if(logData.complete == 3) {
            btn_circle.setBackgroundResource(R.drawable.class_log_btn_circle_unpick)
            btn_triangle.setBackgroundResource(R.drawable.class_log_btn_triangle_unpick)
            btn_x.setBackgroundResource(R.drawable.class_log_btn_x_pick)
        }

        if(logData.color == "yellow")
            iv_color.setImageResource(R.drawable.class_log_img_yellow)
        if(logData.color == "green")
            iv_color.setImageResource(R.drawable.class_log_img_green)
        if(logData.color == "blue")
            iv_color.setImageResource(R.drawable.class_log_img_skyblue)
    }
}