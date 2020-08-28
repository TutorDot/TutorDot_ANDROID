package com.tutor.tutordot.Notice

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tutor.tutordot.ClassLog.LogRecyclerView.LogData
import com.tutor.tutordot.ClassLog.LogRecyclerView.LogViewHolder
import com.tutor.tutordot.R

class NoticeAdapter(private val context : Context) : RecyclerView.Adapter<NoticeViewHolder>() {
    var n_datas : MutableList<NoticeData> = mutableListOf<NoticeData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_notice,parent,false)
        return NoticeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return n_datas.size
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.bind(n_datas[position])
    }
}