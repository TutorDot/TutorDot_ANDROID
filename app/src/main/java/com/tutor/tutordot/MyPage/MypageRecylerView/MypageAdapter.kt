package com.tutor.tutordot.MyPage.MypageRecylerView

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tutor.tutordot.R

class MypageAdapter(private val context: Context) : RecyclerView.Adapter<MypageViewHolder>(){
    var datas= mutableListOf<MypageData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MypageViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_mypage,parent,false)
        return MypageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: MypageViewHolder, position: Int) {
        holder.bind(datas[position])
    }

}