package com.tutor.tutordot.Calendar.CalendarLogAllRecyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tutor.tutordot.R

class CalendarLogAllAdapter(private val context : Context) : RecyclerView.Adapter<CalendarLogAllViewHolder>() {
    var datas:MutableList<CalendarLogAllData> = mutableListOf<CalendarLogAllData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarLogAllViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_calendarlog_all, parent, false)
        return CalendarLogAllViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: CalendarLogAllViewHolder, position: Int) {
        holder.bind(datas[position])
    }
}