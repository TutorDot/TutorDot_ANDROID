package com.tutor.tutordot.Calendar.CalendarLogAllRecyclerView

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tutor.tutordot.Calendar.CalendarLogRecyclerView.CalendarLogAdapter
import com.tutor.tutordot.Calendar.CalendarLogRecyclerView.CalendarLogData
import com.tutor.tutordot.R

class CalendarLogAllViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val calendarlog_all_date : TextView = itemView.findViewById<TextView>(R.id.calendarlog_all_date)
    val calendarlog_all_month : TextView = itemView.findViewById<TextView>(R.id.calendarlog_all_month)
    val rv_calendarlog_all : RecyclerView = itemView.findViewById<RecyclerView>(R.id.rv_calendarlog_all)

    lateinit var calendarLogAdapter: CalendarLogAdapter
    val datas : MutableList<CalendarLogData> = mutableListOf<CalendarLogData>()

    fun bind(calendarLogAllData : CalendarLogAllData){
        calendarlog_all_date.text = calendarLogAllData.day.toString()
        calendarlog_all_month.text = calendarLogAllData.month.toString() + "월"

        calendarLogAdapter = CalendarLogAdapter(itemView.context)
        rv_calendarlog_all.adapter = calendarLogAdapter //리사이클러뷰의 어댑터를 지정해줌
        loadDatas() //데이터를 어댑터에 전달
    }

    private fun loadDatas(){
        datas.apply {
            add(
                CalendarLogData(
                    starttime = "2:00PM",
                    endtime = "4:00PM",
                    img_color = "yellow",
                    times = 1,
                    title = "김회진 튜티 수학 수업",
                    studytime = 1,
                    location = "원당역 할리스"
                )
            )
            add(
                CalendarLogData(
                    starttime = "1:00PM",
                    endtime = "5:00PM",
                    img_color = "green",
                    times = 1,
                    title = "신연상 튜티 수학 수업",
                    studytime = 2,
                    location = "강남구청역 스타벅스"
                )
            )
            add(
                CalendarLogData(
                    starttime = "5:00PM",
                    endtime = "6:00PM",
                    img_color = "yellow",
                    times = 2,
                    title = "김회진 튜티 물리 수업",
                    studytime = 5,
                    location = "수유역 할리스"
                )
            )
        }
        calendarLogAdapter.datas = datas
        calendarLogAdapter.notifyDataSetChanged()
    }
}