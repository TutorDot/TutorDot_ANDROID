package com.tutor.tutordot

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.tutor.tutordot.R
import kotlinx.android.synthetic.main.fragment_class_log.*
import kotlinx.android.synthetic.main.item_logdate.*


class ClassLogFragment : Fragment() {

    lateinit var logdateAdapter: LogdateAdapter
    val datedatas : MutableList<LogdateData> = mutableListOf<LogdateData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_class_log, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logdateAdapter = LogdateAdapter(view.context)
        rv_datelog.adapter = logdateAdapter //리사이클러뷰의 어댑터를 지정해줌
        loaddateDatas() //데이터를 어댑터에 전달
    }

    private fun loaddateDatas(){
        datedatas.apply {
            add(
                LogdateData(
                    month = 5,
                    day = 5
                ))
            add(
                LogdateData(
                    month = 6,
                    day = 6
                ))
            add(
                LogdateData(
                    month = 7,
                    day = 7
                ))
        }
        logdateAdapter.datas = datedatas
        logdateAdapter.notifyDataSetChanged()
    }
}