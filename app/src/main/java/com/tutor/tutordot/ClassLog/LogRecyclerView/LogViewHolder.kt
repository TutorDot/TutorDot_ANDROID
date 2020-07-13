package com.tutor.tutordot.ClassLog.LogRecyclerView

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.tutor.tutordot.ClassLog.ClassLogModificationActivity
import com.tutor.tutordot.ClassLog.LogdateRecyclerView.modi_check
import com.tutor.tutordot.ClassLog.LogdateRecyclerView.ser_hw
import com.tutor.tutordot.ClassLog.LogdateRecyclerView.ser_progress
import com.tutor.tutordot.R
import com.tutor.tutordot.role


class LogViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val tv_times = itemView.findViewById<TextView>(R.id.tv_times)
    val tv_progress = itemView.findViewById<TextView>(R.id.tv_progress)
    val tv_homework = itemView.findViewById<TextView>(R.id.tv_homework)

    val btn_circle = itemView.findViewById<ImageButton>(R.id.btn_circle)
    val btn_triangle = itemView.findViewById<ImageButton>(R.id.btn_triangle)
    val btn_x = itemView.findViewById<ImageButton>(R.id.btn_x)

    val iv_color = itemView.findViewById<ImageView>(R.id.iv_color)

    //버튼 늘림 표시
    var circle : Boolean = false
    var triangel : Boolean = false
    var x : Boolean = false

    //일지 아이템 수정
    val cl_log_item = itemView.findViewById<ConstraintLayout>(R.id.cl_log_item)

    fun bind(logData : LogData) {
        //일지 내용 수정
        if(modi_check) {
            logData.progress =
                ser_progress
            logData.homework =
                ser_hw
            modi_check = false
        }

        tv_times.text = logData.times.toString() + "회차 " + logData.studytime.toString() + "시간 / " + logData.alltime.toString() + "시간"
        tv_progress.text = "진도 : " + logData.progress
        tv_homework.text = "숙제 : " + logData.homework

        //튜터일 경우 수정 가능
        if(role == "tutor"){
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
        }

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
            iv_color.setImageResource(R.drawable.notice_color_img_yellow)
        if(logData.color == "green")
            iv_color.setImageResource(R.drawable.notice_color_img_green)
        if(logData.color == "blue")
            iv_color.setImageResource(R.drawable.notice_color_img_blue)
        if(logData.color == "purple")
            iv_color.setImageResource(R.drawable.notice_color_img_purple)
        if(logData.color == "red")
            iv_color.setImageResource(R.drawable.notice_color_img_red)

        //일지 아이템 버튼 클릭 이벤트
        //튜터일때 수정 가능
        if(role == "tutor") {
            cl_log_item.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    val context: Context = v!!.context
                    val nextIntent = Intent(v!!.context, ClassLogModificationActivity::class.java)
                    context.startActivity(nextIntent)
                }
            })
        }
    }
}