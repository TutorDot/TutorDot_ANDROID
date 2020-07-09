package com.tutor.tutordot.MyPage.MyclassEditRecylerView

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tutor.tutordot.MyPage.MyclassEditRecylerView.MyclassEditData
import com.tutor.tutordot.R

class MyclassEditViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val item_myclass_edit_text=itemView.findViewById<TextView>(R.id.item_myclass_edit_text)

    fun bind(myclassEditData: MyclassEditData) {
        item_myclass_edit_text.text=myclassEditData.classtime
    }
}