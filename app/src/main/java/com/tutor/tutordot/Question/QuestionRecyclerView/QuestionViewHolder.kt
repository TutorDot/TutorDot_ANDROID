package com.tutor.tutordot.Question.QuestionRecyclerView

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.tutor.tutordot.R
import com.tutor.tutordot.Startpage.role

class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val qclass = itemView.findViewById<TextView>(R.id.tv_qclass)
    val qtime = itemView.findViewById<TextView>(R.id.tv_qtime)
    val question = itemView.findViewById<TextView>(R.id.tv_question)

    fun bind(questionData : QuestionData){
        qclass.text = questionData.student
        qtime.text = questionData.qtime
        question.text = questionData.question
    }


}