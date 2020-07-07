package com.tutor.tutordot

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_logdate.*

class LogdateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tv_date : TextView = itemView.findViewById<TextView>(R.id.tv_date)
    val rv_log : RecyclerView = itemView.findViewById<RecyclerView>(R.id.rv_log)

    lateinit var logAdapter: LogAdapter
    val datas : MutableList<LogData> = mutableListOf<LogData>()

    fun bind(logdateData : LogdateData){
        tv_date.text = logdateData.month.toString() + "월 " + logdateData.day.toString() + "일"

        logAdapter = LogAdapter(itemView.context)
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
                    progress = "수학",
                    homework = "수학의 정석 풀기",
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