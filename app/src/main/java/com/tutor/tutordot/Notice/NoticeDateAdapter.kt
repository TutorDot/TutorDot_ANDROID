package com.tutor.tutordot.Notice

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tutor.tutordot.R

class NoticeDateAdapter (private val context : Context) : RecyclerView.Adapter<NoticeDateViewHolder>() {
    var nd_datas:MutableList<NoticeDateData> = mutableListOf<NoticeDateData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeDateViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_notice_date, parent, false)
        return NoticeDateViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return nd_datas.size
    }

    override fun onBindViewHolder(holder: NoticeDateViewHolder, position: Int) {
        holder.bind(nd_datas[position])


    }
}