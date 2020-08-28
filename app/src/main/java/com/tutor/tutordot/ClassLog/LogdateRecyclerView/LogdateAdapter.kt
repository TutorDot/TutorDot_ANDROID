package com.tutor.tutordot.ClassLog.LogdateRecyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tutor.tutordot.ClassLog.Server.LogSomeData
import com.tutor.tutordot.R

class LogdateAdapter(private val context : Context, var datas : List<LogdateData>) : RecyclerView.Adapter<LogdateViewHolder>() {
    //var datas:MutableList<LogdateData> = mutableListOf<LogdateData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogdateViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_logdate, parent, false)
        return LogdateViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: LogdateViewHolder, position: Int) {
        holder.bind(datas[position])


    }
}

/*
class LogdateAdapter(private val context : Context) : RecyclerView.Adapter<LogdateViewHolder>() {
    var datas:MutableList<LogdateData> = mutableListOf<LogdateData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogdateViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_logdate, parent, false)
        return LogdateViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        if(datas.size == 0)
            haveData = false
        return datas.size
    }

    override fun onBindViewHolder(holder: LogdateViewHolder, position: Int) {
        holder.bind(datas[position])


    }

}

 */

