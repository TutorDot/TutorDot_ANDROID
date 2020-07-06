package com.tutor.tutordot

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class LogAdapter(private val context : Context) : RecyclerView.Adapter<LogViewHolder>() {
    var datas : MutableList<LogData> = mutableListOf<LogData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_classlog,parent,false)
        return LogViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        holder.bind(datas[position])
    }
}