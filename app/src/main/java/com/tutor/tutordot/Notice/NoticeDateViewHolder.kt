package com.tutor.tutordot.Notice

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.tutor.tutordot.R

//알림 데이터 유무
var haveNdata: Boolean = true

class NoticeDateViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tv_notice_date : TextView = itemView.findViewById<TextView>(R.id.tv_notice_date)
    val rv_notice : RecyclerView = itemView.findViewById<RecyclerView>(R.id.rv_notice)

    lateinit var noticeAdapter: NoticeAdapter
    val n_datas : MutableList<NoticeData> = mutableListOf<NoticeData>()

    fun bind(noticedateData : NoticeDateData){
        tv_notice_date.text = noticedateData.month.toString() + "월 " + noticedateData.day.toString() + "일"

        noticeAdapter =
            NoticeAdapter(itemView.context)
        rv_notice.adapter = noticeAdapter //리사이클러뷰의 어댑터를 지정해줌
        loadDatas() //데이터를 어댑터에 전달
    }

    private fun loadDatas(){
        n_datas.apply {
            add(
                NoticeData(
                    color_class = "yellow",
                    notice_type = 1,
                    notice_msg = "신연상 튜터의 수학 수업이 내일 예정되어 있습니다."
                )
            )
            add(
                NoticeData(
                    color_class = "purple",
                    notice_type = 2,
                    notice_msg = "신연상 튜터의 물리 수업 회차가 끝났습니다."
                )
            )
            add(
                NoticeData(
                    color_class = "red",
                    notice_type = 3,
                    notice_msg = "신연상 튜터의 수학 수업 일지가 업데이트 되었습니다."
                )
            )
            add(
                NoticeData(
                    color_class = "green",
                    notice_type = 4,
                    notice_msg = "신연상 튜터의 영어 수업 정보가 변경되었습니다."
                )
            )
        }
        noticeAdapter.n_datas = n_datas
        noticeAdapter.notifyDataSetChanged()
    }
}