package com.tutor.tutordot.ClassLog.LogRecyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tutor.tutordot.Calendar.Server.CalendarData
import com.tutor.tutordot.ClassLog.LogdateRecyclerView.haveData
import com.tutor.tutordot.ClassLog.Server.LogSomeData
import com.tutor.tutordot.R


////서버 연동

class LogAdapter(private val context: Context, var datas: MutableList<LogData>) : RecyclerView.Adapter<LogViewHolder>() {
    //var datas : MutableList<LogData> = mutableListOf<LogData>()
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

/*
class LogAdapter(private val context : Context) : RecyclerView.Adapter<LogViewHolder>() {
    var datas : MutableList<LogData> = mutableListOf<LogData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_classlog,parent,false)
        return LogViewHolder(view)
    }

    override fun getItemCount(): Int {
        if(datas.size == 0)
            haveData = false
        return datas.size
    }

    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        holder.bind(datas[position])
    }
}


 */
