package com.tutor.tutordot.Question.QuestionRecyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tutor.tutordot.ClassLog.LogdateRecyclerView.LogdateViewHolder
import com.tutor.tutordot.Question.haveqData
import com.tutor.tutordot.R

class QuestionAdapter (private val context : Context) : RecyclerView.Adapter<QuestionViewHolder>() {
    var qdatas:MutableList<QuestionData> = mutableListOf<QuestionData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_question, parent, false)
        return QuestionViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        if(qdatas.size == 0)
            haveqData = false
        return qdatas.size
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(qdatas[position])


    }
}