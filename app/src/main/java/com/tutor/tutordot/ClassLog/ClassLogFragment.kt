package com.tutor.tutordot.ClassLog

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.tutor.tutordot.ClassLog.LogdateRecyclerView.LogdateAdapter
import com.tutor.tutordot.ClassLog.LogdateRecyclerView.LogdateData
import com.tutor.tutordot.R
import com.tutor.tutordot.ClassLog.LogdateRecyclerView.haveData
import com.tutor.tutordot.ClassLog.Server.LogRequestToServer
import com.tutor.tutordot.ClassLog.Server.LogResponse
import com.tutor.tutordot.StartServer.RequestLogin
import com.tutor.tutordot.StartServer.RequestToServer
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_class_log.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class ClassLogFragment : Fragment() {

    val logRequestToServer = LogRequestToServer

    lateinit var logdateAdapter: LogdateAdapter
    val datedatas : MutableList<LogdateData> = mutableListOf<LogdateData>()

    //현재 달 구하기
    val curDate= Calendar.getInstance()
    val month = curDate.get(Calendar.MONTH) + 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_class_log, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logdateAdapter =
            LogdateAdapter(view.context)
        rv_datelog.adapter = logdateAdapter //리사이클러뷰의 어댑터를 지정해줌
        loaddateDatas() //데이터를 어댑터에 전달

        //프로그레스바 값 지정 (나중에 서버에서 값 받아와서 지정)
        pb_class.progress = 75  //status
        //pb_class.incrementProgressBy(5)  //5씩 증가되는 코드

        if (pb_class.progress == 100)
            tv_percent.setTextColor(Color.parseColor("#FFFFFF"));

        tv_month_log.setText(month.toString() + "월 수업일지")
        var mon = month

        //월 이전 이동
        btn_month_prev.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                mon--
                tv_month_log.setText(mon.toString() + "월 수업일지")
                btn_month_next.setImageResource(R.drawable.class_log_blank_btn_next_month)
            }
        })
        //월 이후 이동
        btn_month_next.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                mon++
                if (mon == month) {
                    tv_month_log.setText(mon.toString() + "월 수업일지")
                    btn_month_next.setImageResource(R.drawable.class_log_btn_next_month)
                }
            }
        })

        //상단 수업 선택 메뉴
        ll_log_choice.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val popup =
                    PopupMenu(context, btn_class_choice)
                //Inflating the Popup using xml file
                popup.menuInflater
                    .inflate(R.menu.popup_menu, popup.menu)

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener { item ->
                    tv_class_choice.text = item.title
                    if (item.title.equals("전체"))
                        ll_progress.visibility = View.GONE
                    else
                        ll_progress.visibility = View.VISIBLE
                    true
                }
                popup.show() //showing popup menu
            }
        })

        if (haveData == true) {
            cl_empty.visibility = View.GONE
            ll_rv.visibility = View.VISIBLE
        } else {
            ll_rv.visibility = View.GONE
            cl_empty.visibility = View.VISIBLE

        }


        /* 팝업 메뉴 아이템 추가할 때 사용할 코드
        val menu = PopupMenu(context, view)

        menu.menu.add("One")
        menu.menu.add("Two")
        menu.menu.add("Three")

        menu.show()
        */
    }


    private fun loaddateDatas(){
        datedatas.apply {
            add(
                LogdateData(
                    month = 5,
                    day = 5
                )
            )
            add(
                LogdateData(
                    month = 6,
                    day = 6
                )
            )
            add(
                LogdateData(
                    month = 7,
                    day = 7
                )
            )
        }
        logdateAdapter.datas = datedatas
        logdateAdapter.notifyDataSetChanged()
    }
}