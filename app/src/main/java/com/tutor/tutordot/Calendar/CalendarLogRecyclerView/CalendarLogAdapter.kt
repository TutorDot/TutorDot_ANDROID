package com.tutor.tutordot.Calendar.CalendarLogRecyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tutor.tutordot.R

class CalendarLogAdapter(private val context : Context) : RecyclerView.Adapter<CalendarLogViewHolder>() {
    var datas : MutableList<CalendarLogData> = mutableListOf<CalendarLogData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarLogViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_calendarlog,parent,false)
        return CalendarLogViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: CalendarLogViewHolder, position: Int) {
        holder.bind(datas[position])
    }
}