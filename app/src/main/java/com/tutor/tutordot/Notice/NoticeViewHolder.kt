package com.tutor.tutordot.Notice

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tutor.tutordot.R

class NoticeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_notice = itemView.findViewById<TextView>(R.id.tv_notice)
        val tv_notice_msg = itemView.findViewById<TextView>(R.id.tv_notice_msg)
        val iv_notice = itemView.findViewById<ImageView>(R.id.iv_notice)


        fun bind(noticeData : NoticeData) {

            if(noticeData.color_class == "yellow"){
                when(noticeData.notice_type){
                    1 -> iv_notice.setImageResource(R.drawable.notice_img_class_yellow)
                    2 -> iv_notice.setImageResource(R.drawable.notice_img_money_yellow)
                    3 -> iv_notice.setImageResource(R.drawable.notice_img_classlog_yellow)
                    4 -> iv_notice.setImageResource(R.drawable.notice_img_notice_yellow)
                }
            }
            if(noticeData.color_class == "green"){
                when(noticeData.notice_type){
                    1 -> iv_notice.setImageResource(R.drawable.notice_img_class_green)
                    2 -> iv_notice.setImageResource(R.drawable.notice_img_money_green)
                    3 -> iv_notice.setImageResource(R.drawable.notice_img_classlog_green)
                    4 -> iv_notice.setImageResource(R.drawable.notice_img_notice_green)
                }
            }
            if(noticeData.color_class == "blue"){
                when(noticeData.notice_type){
                    1 -> iv_notice.setImageResource(R.drawable.notice_img_class_blue)
                    2 -> iv_notice.setImageResource(R.drawable.notice_img_money_blue)
                    3 -> iv_notice.setImageResource(R.drawable.notice_img_classlog_blue)
                    4 -> iv_notice.setImageResource(R.drawable.notice_img_notice_blue)
                }
            }
            if(noticeData.color_class == "purple"){
                when(noticeData.notice_type){
                    1 -> iv_notice.setImageResource(R.drawable.notice_img_class_purple)
                    2 -> iv_notice.setImageResource(R.drawable.notice_img_money_purple)
                    3 -> iv_notice.setImageResource(R.drawable.notice_img_classlog_purple)
                    4 -> iv_notice.setImageResource(R.drawable.notice_img_notice_purple)
                }
            }
            if(noticeData.color_class == "red"){
                when(noticeData.notice_type){
                    1 -> iv_notice.setImageResource(R.drawable.notice_img_class_red)
                    2 -> iv_notice.setImageResource(R.drawable.notice_img_money_red)
                    3 -> iv_notice.setImageResource(R.drawable.notice_img_classlog_red)
                    4 -> iv_notice.setImageResource(R.drawable.notice_img_notice_red)
                }
            }

        }
}