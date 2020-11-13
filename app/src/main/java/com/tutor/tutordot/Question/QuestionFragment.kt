package com.tutor.tutordot.Question

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tutor.tutordot.Question.QuestionRecyclerView.QuestionAdapter
import com.tutor.tutordot.Question.QuestionRecyclerView.QuestionData
import com.tutor.tutordot.R
import kotlinx.android.synthetic.main.fragment_question.*

var haveqData : Boolean = true

class QuestionFragment : Fragment() {


    lateinit var questionAdapter: QuestionAdapter
    var qdatas : MutableList<QuestionData> = mutableListOf<QuestionData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        questionAdapter = QuestionAdapter(view.context)
        rv_question.adapter = questionAdapter
        loadDatas()
    }

    private fun loadDatas(){
        qdatas.apply {
            add(
                QuestionData(
                    student = "red",
                    question = "이것은 무엇인가요 궁금해요 선생님 알려주세요!!",
                    questionpic = "url",
                    qtime = "11:00 오전"
                )
            )
            add(
                QuestionData(
                    student = "red",
                    question = "이것은 무엇인가요 궁금해요 선생님 알려주세요!!",
                    questionpic = "url",
                    qtime = "11:00 오후"
                )
            )
        }
        questionAdapter.qdatas = qdatas
        questionAdapter.notifyDataSetChanged()
    }
}