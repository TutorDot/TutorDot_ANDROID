package com.tutor.tutordot

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LogViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val tv_times = itemView.findViewById<TextView>(R.id.tv_times)
    val tv_progress = itemView.findViewById<TextView>(R.id.tv_progress)
    val tv_homework = itemView.findViewById<TextView>(R.id.tv_homework)

    val btn_circle = itemView.findViewById<ImageButton>(R.id.btn_circle)
    val btn_triangle = itemView.findViewById<ImageButton>(R.id.btn_triangle)
    val btn_x = itemView.findViewById<ImageButton>(R.id.btn_x)

    fun bind(logData : LogData) {
        tv_times.text = logData.times.toString() + "회차 " + logData.studytime.toString() + "시간 / " + logData.alltime.toString() + "시간"
        tv_progress.text = "진도 : " + logData.progress
        tv_homework.text = "숙제 : " + logData.homework

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
    }
}