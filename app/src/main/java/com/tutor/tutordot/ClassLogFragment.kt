package com.tutor.tutordot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tutor.tutordot.R
import kotlinx.android.synthetic.main.fragment_class_log.*


class ClassLogFragment : Fragment() {

    lateinit var logAdapter: LogAdapter
    val datas : MutableList<LogData> = mutableListOf<LogData>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_class_log, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logAdapter = LogAdapter(view.context)
        rv_log.adapter = logAdapter //리사이클러뷰의 어댑터를 지정해줌
        loadDatas() //데이터를 어댑터에 전달
    }

    private fun loadDatas(){
        datas.apply {
            add(
                LogData(times = 1,
                    studytime = 1,
                    alltime = 15,
                    progress = "수학",
                    homework = "수학의 정석 풀기",
                    complete = 1
                )
            )
            add(
                LogData(times = 2,
                    studytime = 2,
                    alltime = 15,
                    progress = "수학2",
                    homework = "수학의 정석 풀기2",
                    complete = 2
                )
            )
            add(
                LogData(times = 3,
                    studytime = 3,
                    alltime = 15,
                    progress = "수학3",
                    homework = "수학의 정석 풀기3",
                    complete = 3
                )
            )
        }
        logAdapter.datas = datas
        logAdapter.notifyDataSetChanged()
    }
}