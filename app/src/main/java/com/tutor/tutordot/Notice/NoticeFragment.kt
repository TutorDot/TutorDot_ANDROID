package com.tutor.tutordot.Notice

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import com.tutor.tutordot.Calendar.Server.CalendarLogRequestToServer
import com.tutor.tutordot.ClassLog.LogdateRecyclerView.LogdateAdapter
import com.tutor.tutordot.ClassLog.LogdateRecyclerView.LogdateData
import com.tutor.tutordot.ClassLog.Server.DiaryResponse
import com.tutor.tutordot.ClassLog.Server.LectureResponse
import com.tutor.tutordot.ClassLog.Server.ProgressResponse
import com.tutor.tutordot.ClassLog.dd
import com.tutor.tutordot.ClassLog.haveData
import com.tutor.tutordot.ClassLog.mm
import com.tutor.tutordot.ClassLog.yy
import com.tutor.tutordot.Notice.NoticeRecyclerView.NoticeAdapter
import com.tutor.tutordot.Notice.NoticeRecyclerView.NoticeData
import com.tutor.tutordot.Notice.NoticeRecyclerView.haveNdata
import com.tutor.tutordot.Notice.Server.LecnotiResponse
import com.tutor.tutordot.Notice.Server.NoticeRequestToServer
import com.tutor.tutordot.Notice.Server.NoticeResponse
import com.tutor.tutordot.R
import com.tutor.tutordot.Startpage.myjwt
import kotlinx.android.synthetic.main.fragment_class_log.*
import kotlinx.android.synthetic.main.fragment_notice.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


class NoticeFragment : Fragment() {

    lateinit var noticeAdapter: NoticeAdapter
    //var n_datas: MutableList<NoticeData> = mutableListOf<NoticeData>()
    val noticeRequestToServer = NoticeRequestToServer

    lateinit var leid : ArrayList<Int>
    lateinit var lename : ArrayList<String>
    var lecnt : Int = 0
    lateinit var popup:PopupMenu

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)

        noticeRequestToServer.service.lectureRequest(
            "${myjwt}"
        ).enqueue(object :Callback<LectureResponse>{
            override fun onFailure(call: Call<LectureResponse>, t: Throwable) {
                Log.d("통신 실패", "통신 실패")
            }

            override fun onResponse(
                call: Call<LectureResponse>,
                response: Response<LectureResponse>
            ) {
                if(response.isSuccessful){
                    if(response.body()!!.success){
                        Log.d("토글 수업 정보", "성공")

                        lecnt = response.body()!!.data.size
                        lename = ArrayList()
                        leid = ArrayList()
                        for(i in 1..lecnt) {
                            lename.add(response.body()!!.data[i - 1].lectureName)
                            leid.add(response.body()!!.data[i-1].lectureId)
                            //수업 개수에 맞게 토글 항목 추가
                            popup.menu.add(response.body()!!.data[i - 1].lectureName)
                        }
                        Log.d("수업 이름", "{$lename}")

                    }else{
                        Log.d("토글 수업 정보", "실패")
                    }
                }
            }
        })
        alldata()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notice, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        noticeAdapter =
            NoticeAdapter(view.context)
        rv_notice_date.adapter = noticeAdapter //리사이클러뷰의 어댑터를 지정해줌
        loadndateDatas() //데이터를 어댑터에 전달
         */

    //수업정보 받아옴 (토글 위해) (2차 릴리즈에서는 btn_notice_filter1 로 변경)
        popup =
            PopupMenu(context, btn_notice_choice)
        //Inflating the Popup using xml file
        popup.menuInflater
            .inflate(R.menu.popup_menu, popup.menu)


        //상단 수업 선택 메뉴 (2차 릴리즈에서는 btn_notice_filter1 로 변경)
        btn_notice_choice.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                /*
                val popup =
                    PopupMenu(context, btn_notice_choice)
                //Inflating the Popup using xml file
                popup.menuInflater
                    .inflate(R.menu.popup_menu, popup.menu)
*/
                //registering popup with OnMenuItemClickListener (2차 릴리즈에서는 tv_notice_title 로 변경
                popup.setOnMenuItemClickListener { item ->
                    tv_notice_choice.text = item.title
                    if (item.title.equals("전체")) {
                        alldata()
                    }
                    else {
                        var lid : Int = 0
                        for(i in 1..lecnt) {
                            if(item.title.equals(lename[i-1]))
                                lid = leid[i-1]
                        }
                        Log.d("아이디", "${lid}")

                        //서버통신
                        var n_datas: MutableList<NoticeData> = mutableListOf<NoticeData>()
                        noticeRequestToServer.service.lecnotiRequest(
                            "${myjwt}","${lid}"
                        ).enqueue(object: Callback<LecnotiResponse> {
                            override fun onFailure(call: Call<LecnotiResponse>, t: Throwable) {
                                Log.d("특정 통신 실패", "notice통신 실패${t}")
                                haveNdata =false
                                ll_rv_notice.visibility = View.GONE
                                cl_empty_notice.visibility = View.VISIBLE
                            }

                            override fun onResponse(
                                call: Call<LecnotiResponse>,
                                response: Response<LecnotiResponse>
                            ) {
                                if(response.isSuccessful){
                                    if(response.body()!!.success){
                                        Log.d("특정 성공", "notice성공")
                                        Log.d(response.body()!!.data.toString(),response.body()!!.data.toString())
                                        if(response.body()!!.data.size==0){
                                            haveNdata =false
                                            ll_rv_notice.visibility = View.GONE
                                            cl_empty_notice.visibility = View.VISIBLE
                                        }
                                        var i: Int = 0
                                        for (i in 0 until response.body()!!.data.size) {
                                            var cd = response.body()!!.data[i].noticeDate.split("-")
                                            var yy = cd[0]
                                            var mm = cd[1]
                                            var dd = cd[2]

                                            n_datas.apply {
                                                add(
                                                    NoticeData(
                                                        month = mm.toInt(),
                                                        day = dd.toInt(),
                                                        color_class = response.body()!!.data[i].color,
                                                        notice_type = response.body()!!.data[i].noticeType,
                                                        notice_msg = response.body()!!.data[i].lectureName,
                                                        first2 = false

                                                    )
                                                )
                                            }
                                        }

                                        n_datas= n_datas.sortedWith(compareBy<NoticeData>{it.month}.thenBy{it.day}).toMutableList()
                                        n_datas = n_datas.distinct().toMutableList()
                                        var j=0
                                        if(n_datas.size>0) {
                                            var mymon = "${n_datas[0].month}"+"${n_datas[0].day}"
                                            var tmp:String=""
                                            n_datas[0].first2=true
                                            for (i in 1 until n_datas.size) {
                                                tmp="${n_datas[i].month}"+"${n_datas[i].day}"
                                                if (tmp != mymon ){
                                                    n_datas[i].first2=true
                                                    mymon = tmp

                                                }
                                            }
                                        }

                                        Log.d("뭐지", "${n_datas}")

                                        noticeAdapter= NoticeAdapter(view.context, n_datas)
                                        rv_notice_date.adapter=noticeAdapter
                                        noticeAdapter.n_datas=n_datas
                                        noticeAdapter.notifyDataSetChanged()

                                    }else{Log.d("실패", "특정 실패")}
                                }
                            }
                        })
                    }
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

        alldata()

    }

    private fun alldata(){
        var n_datas: MutableList<NoticeData> = mutableListOf<NoticeData>()
        //서버통신
        noticeRequestToServer.service.noticeRequest(
            "${myjwt}"
        ).enqueue(object: Callback<NoticeResponse> {
            override fun onFailure(call: Call<NoticeResponse>, t: Throwable) {
                Log.d("통신 실패", "notice통신 실패${t}")
            }

            override fun onResponse(
                call: Call<NoticeResponse>,
                response: Response<NoticeResponse>
            ) {
                if(response.isSuccessful){
                    if(response.body()!!.success){
                        Log.d("성공", "notice성공")
                        Log.d(response.body()!!.data.toString(),response.body()!!.data.toString())
                        if(response.body()!!.data.size==0){
                            haveNdata =false
                            ll_rv_notice.visibility = View.GONE
                            cl_empty_notice.visibility = View.VISIBLE
                        }
                        var i: Int = 0
                        for (i in 0 until response.body()!!.data.size) {
                            var cd = response.body()!!.data[i].noticeDate.split("-")
                            var yy = cd[0]
                            var mm = cd[1]
                            var dd = cd[2]

                            n_datas.apply {
                                add(
                                    NoticeData(
                                        month = mm.toInt(),
                                        day = dd.toInt(),
                                        color_class = response.body()!!.data[i].color,
                                        notice_type = response.body()!!.data[i].noticeType,
                                        notice_msg = response.body()!!.data[i].lectureName,
                                        first2 = false

                                    )
                                )
                            }
                        }

                        n_datas= n_datas.sortedWith(compareBy<NoticeData>{it.month}.thenBy{it.day}).toMutableList()
                        n_datas = n_datas.distinct().toMutableList()
                        var j=0
                        if(n_datas.size>0) {
                            var mymon = "${n_datas[0].month}"+"${n_datas[0].day}"
                            var tmp:String=""
                            n_datas[0].first2=true
                            for (i in 1 until n_datas.size) {
                                tmp="${n_datas[i].month}"+"${n_datas[i].day}"
                                if (tmp != mymon ){
                                    n_datas[i].first2=true
                                    mymon = tmp

                                }
                            }
                        }

                        Log.d("뭐지", "${n_datas}")

                        noticeAdapter= NoticeAdapter(view!!.context, n_datas)
                        rv_notice_date.adapter=noticeAdapter
                        noticeAdapter.n_datas=n_datas
                        noticeAdapter.notifyDataSetChanged()

                    }else{Log.d("실패", "myinfo실패")}
                }

            }
        })
    }

    private fun loadndateDatas() {
       /* n_datas.apply {
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

        */
        //noticeAdapter.n_datas = n_datas
        //noticeAdapter.notifyDataSetChanged()
    }
}