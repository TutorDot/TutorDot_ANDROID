package com.tutor.tutordot.ClassLog.LogdateRecyclerView

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tutor.tutordot.ClassLog.LogRecyclerView.LogAdapter
import com.tutor.tutordot.ClassLog.LogRecyclerView.LogData
import com.tutor.tutordot.R


//나중에 레트로핏 보면서 수정
var ser_progress : String = "서버에서 받는 진도 데이터"
var ser_hw : String = "서버에서 받는 숙제 데이터"
var modi_check : Boolean = false
var haveData : Boolean = true

class LogdateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tv_date : TextView = itemView.findViewById<TextView>(R.id.tv_date)
    val rv_log : RecyclerView = itemView.findViewById<RecyclerView>(R.id.rv_log)

    lateinit var logAdapter: LogAdapter
    val datas : MutableList<LogData> = mutableListOf<LogData>()

    fun bind(logdateData : LogdateData){
        tv_date.text = logdateData.month.toString() + "월 " + logdateData.day.toString() + "일"

        logAdapter =
            LogAdapter(itemView.context)
        rv_log.adapter = logAdapter //리사이클러뷰의 어댑터를 지정해줌
        loadDatas() //데이터를 어댑터에 전달
    }

    private fun loadDatas(){
        datas.apply {
            add(
                LogData(
                    color = "yellow",
                    times = 1,
                    studytime = 1,
                    alltime = 15,
                    progress = ser_progress,
                    homework = ser_hw,
                    complete = 0
                )
            )
            add(
                LogData(
                    color = "green",
                    times = 2,
                    studytime = 2,
                    alltime = 15,
                    progress = "수학2",
                    homework = "수학의 정석 풀기2",
                    complete = 0
                )
            )
            add(
                LogData(
                    color = "blue",
                    times = 3,
                    studytime = 3,
                    alltime = 15,
                    progress = "수학3",
                    homework = "수학의 정석 풀기3",
                    complete = 0
                )
            )
        }
        logAdapter.datas = datas
        logAdapter.notifyDataSetChanged()
    }
}