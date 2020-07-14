package com.tutor.tutordot.Notice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import com.tutor.tutordot.ClassLog.LogdateRecyclerView.LogdateAdapter
import com.tutor.tutordot.ClassLog.LogdateRecyclerView.LogdateData
import com.tutor.tutordot.ClassLog.LogdateRecyclerView.haveData
import com.tutor.tutordot.R
import kotlinx.android.synthetic.main.fragment_class_log.*
import kotlinx.android.synthetic.main.fragment_notice.*

class NoticeFragment : Fragment() {

    lateinit var noticeDateAdapter: NoticeDateAdapter
    val n_datedatas: MutableList<NoticeDateData> = mutableListOf<NoticeDateData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notice, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noticeDateAdapter =
            NoticeDateAdapter(view.context)
        rv_notice_date.adapter = noticeDateAdapter //리사이클러뷰의 어댑터를 지정해줌
        loadndateDatas() //데이터를 어댑터에 전달

        //상단 수업 선택 메뉴
        ll_noticee_choice.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val popup =
                    PopupMenu(context, btn_notice_choice)
                //Inflating the Popup using xml file
                popup.menuInflater
                    .inflate(R.menu.popup_menu, popup.menu)

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener { item ->
                    tv_notice_choice.text = item.title
                    true
                }
                popup.show() //showing popup menu
            }
        })

        //데이터 있을때 / 없을때
        if(haveNdata ==true) {
            cl_empty_notice.visibility =View.GONE
            ll_rv_notice.visibility = View.VISIBLE
        }
        else {
            ll_rv_notice.visibility = View.GONE
            cl_empty_notice.visibility =View.VISIBLE

        }
    }
    private fun loadndateDatas() {
        n_datedatas.apply {
            add(
                NoticeDateData(
                    month = 5,
                    day = 5
                )
            )
            add(
                NoticeDateData(
                    month = 6,
                    day = 6
                )
            )
            add(
                NoticeDateData(
                    month = 7,
                    day = 7
                )
            )
        }
        noticeDateAdapter.nd_datas = n_datedatas
        noticeDateAdapter.notifyDataSetChanged()
    }
}