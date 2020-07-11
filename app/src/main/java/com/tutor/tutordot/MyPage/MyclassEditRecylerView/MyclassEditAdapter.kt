package com.tutor.tutordot.MyPage.MyclassEditRecylerView

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tutor.tutordot.R

class MyclassEditAdapter(private val context : Context) : RecyclerView.Adapter<MyclassEditViewHolder>(){
    var datas= mutableListOf<MyclassEditData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyclassEditViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_myclass_edit, parent, false)
        return MyclassEditViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: MyclassEditViewHolder, position: Int) {
        holder.bind(datas[position])
    }

}