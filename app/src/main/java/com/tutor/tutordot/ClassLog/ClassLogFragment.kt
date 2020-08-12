package com.tutor.tutordot.ClassLog

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.tutor.tutordot.CalenderActivity
import com.tutor.tutordot.ClassLog.LogdateRecyclerView.LogdateAdapter
import com.tutor.tutordot.ClassLog.LogdateRecyclerView.LogdateData
import com.tutor.tutordot.R
import com.tutor.tutordot.ClassLog.LogdateRecyclerView.haveData
import com.tutor.tutordot.ClassLog.LogdateRecyclerView.ser_color
import com.tutor.tutordot.ClassLog.Server.LogRequestToServer
import com.tutor.tutordot.ClassLog.Server.LogResponse
import com.tutor.tutordot.ClassLog.Server.ProgressResponse
import com.tutor.tutordot.ClassLog.Server.ProgressResponse2
import com.tutor.tutordot.MainPagerAdapter
import com.tutor.tutordot.MyPage.MypageRecylerView.MypageAdapter
import com.tutor.tutordot.Startpage.myjwt
import kotlinx.android.synthetic.main.fragment_class_log.*
import kotlinx.android.synthetic.main.fragment_my.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


var month1 : Int = 7
var day1 : Int = 15
var day2 : Int = 14
var day3 : Int = 13

var mm: String=""
var dd: String=""
var yy: String=""

class ClassLogFragment : Fragment() {

    //서버 연결
    val logRequestToServer = LogRequestToServer

    lateinit var logdateAdapter: LogdateAdapter
    var datedatas : MutableList<LogdateData> = mutableListOf<LogdateData>()

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


        /*되는 코드 (Volley, 헤더는 못함)
        VolleyService.testVolley(view.context) { testSuccess ->
            if (testSuccess) {
                Log.d( "통신 성공!","성공") 
            } else {
                Log.d( "통신 실패!","실패")
            }
        }
*/
        //logdateAdapter = LogdateAdapter(view.context)
        //rv_datelog.adapter = logdateAdapter //리사이클러뷰의 어댑터를 지정해줌
        loaddateDatas() //데이터를 어댑터에 전달

        //프로그레스바 서버에서 받아온 날짜 데이터
        var progressDate : String
        //수업 회차
        var progressTimes : Int
        //총 수업시간
        var progressHour : Int
        //입금주기
        var progressCycle : Int
        //프로그레스바 값
        var progressStatus : Int


        
        //프로그레스바 값 지정 (나중에 서버에서 값 받아와서 지정)
        pb_class.progress = 75  //status

        if (pb_class.progress == 100)
            tv_percent.setTextColor(Color.parseColor("#FFFFFF"));

        tv_month_log.setText(month.toString() + "월 수업일지")
        var mon = month
        month1 = month

        //월 이전 이동
        btn_month_prev.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                mon--
                tv_month_log.setText(mon.toString() + "월 수업일지")
                btn_month_next.setImageResource(R.drawable.class_log_blank_btn_next_month)
                month1 = mon
                day1 = 15
                day2 = 14
                day3 = 13
                logdateAdapter.notifyDataSetChanged()
            }
        })
        //월 이후 이동
        btn_month_next.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                mon++
                if (mon == month) {
                    tv_month_log.setText(mon.toString() + "월 수업일지")
                    btn_month_next.setImageResource(R.drawable.class_log_btn_next_month)
                    month1 = mon
                    day1 = 15
                    day2 = 14
                    day3 = 13
                    logdateAdapter.notifyDataSetChanged()                }
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
                    else{
                        ll_progress.visibility = View.VISIBLE

                        if(item.title.equals("신연상 학생 수학 수업")){
                            ser_color = "yellow"

                            //프로그레스바 서버에서 정보 받아옴
                            logRequestToServer.service.progressRequest(
                                "${myjwt}"
                            ).enqueue(object :Callback<ProgressResponse>{
                                override fun onFailure(call: Call<ProgressResponse>, t: Throwable) {
                                    Log.d("통신 실패", "통신 실패")
                                }

                                override fun onResponse(
                                    call: Call<ProgressResponse>,
                                    response: Response<ProgressResponse>
                                ) {
                                    if(response.isSuccessful){
                                        if(response.body()!!.success){
                                            Log.d("첫번째 과목 프로그레스바 성공", "성공")

                                            progressDate = response.body()!!.data[5].classDate
                                            progressCycle = response.body()!!.data[5].depositCycle
                                            progressTimes = response.body()!!.data[5].times
                                            progressHour = response.body()!!.data[5].hour
                                            tv_progress_times.setText(progressTimes.toString() + "회차 " + progressHour.toString() + "시간")
                                            tv_progress_alltime.setText(progressCycle.toString() + "시간")
                                            progressStatus = 100*progressHour/progressCycle
                                            pb_class.progress = progressStatus
                                            tv_percent.setText(progressStatus.toString() + "%")

                                            Log.d("첫번째 과목 입금 주기", progressCycle.toString())
                                            Log.d("첫번째 과목 회차", progressTimes.toString())
                                            Log.d("첫번째 과목 총 수업시간", progressHour.toString())
                                            Log.d("첫번째 과목 프로그레스바 퍼센트", progressStatus.toString())
                                            Log.d("첫번째 과목 날짜", progressDate)
                                        }else{
                                            Log.d("실패", "실패")
                                        }
                                    }
                                }
                            })
                        }else{
                            ser_color = "green"

                            //프로그레스바 서버에서 정보 받아옴
                            logRequestToServer.service.progressRequest2(
                                "${myjwt}"
                            ).enqueue(object :Callback<ProgressResponse2>{
                                override fun onFailure(call: Call<ProgressResponse2>, t: Throwable) {
                                    Log.d("통신 실패", "통신 실패")
                                }
                                override fun onResponse(
                                    call: Call<ProgressResponse2>,
                                    response: Response<ProgressResponse2>
                                ) {
                                    if(response.isSuccessful){
                                        if(response.body()!!.success){
                                            Log.d("두번째 과목 프로그레스바 성공", "성공")
                                           // Log.d(response.body()!!.data.toString(),response.body()!!.data.toString())
                                            progressDate = response.body()!!.data[5].classDate
                                            progressCycle = response.body()!!.data[5].depositCycle
                                            progressTimes = response.body()!!.data[5].times
                                            progressHour = response.body()!!.data[5].hour
                                            tv_progress_times.setText(progressTimes.toString() + "회차 " + progressHour.toString() + "시간")
                                            tv_progress_alltime.setText(progressCycle.toString() + "시간")
                                            progressStatus = 100*progressHour/progressCycle
                                            pb_class.progress = progressStatus
                                            tv_percent.setText(progressStatus.toString() + "%")

                                            Log.d("두번째 과목 입금 주기", progressCycle.toString())
                                            Log.d("두번째 과목 회차", progressTimes.toString())
                                            Log.d("두번째 과목 총 수업시간", progressHour.toString())
                                            Log.d("두번째 과목 프로그레스바 퍼센트", progressStatus.toString())
                                            Log.d("두번째 과목 날짜", progressDate)
                                        }else{
                                            Log.d("실패", "실패")
                                        }
                                    }
                                }

                            })
                        }

                    }
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

    //서버 연결
    private fun loaddateDatas(){
        // 서버 요청
        logRequestToServer.service.logRequest(
            "${myjwt}"
        ).enqueue(object : Callback<LogResponse> {
            override fun onFailure(call: Call<LogResponse>, t: Throwable) {
                Log.d("통신 실패", "${t}")
            }

            override fun onResponse(
                call: Call<LogResponse>,
                response: Response<LogResponse>
            ) {
                // 통신 성공
                if (response.isSuccessful) {   // statusCode가 200-300 사이일 때, 응답 body 이용 가능
                    if (response.body()!!.success) {
                        Log.d("성공", "성공")
                        Log.d("데이터 받기성공", response.body()!!.data.toString())


                        //바깥쪽 날짜 데이터
                        var i: Int = 0
                        for (i in 0 until response.body()!!.data.size) {
                            var cd = response.body()!!.data[i].classDate.split("-")
                            yy = cd[0]
                            mm = cd[1]
                            dd = cd[2]

/*
                            if (mm[0].equals("0"))
                                mm = mm.replace("0", " ")
                            if (dd[0].equals("0"))
                                dd = dd.replace("0", " ")
*/
                            //Log.d("날짜", "${mm}")
                            //Log.d("날짜", "${dd}")

                            datedatas.apply {
                                add(
                                    LogdateData(
                                        month = mm.toInt(),
                                        day = dd.toInt(),
                                        color = response.body()!!.data[i].color,
                                        times = response.body()!!.data[i].times,
                                        studytime = response.body()!!.data[i].hour,
                                        alltime = response.body()!!.data[i].depositCycle,
                                        progress = response.body()!!.data[i].classProgress,
                                        homework = response.body()!!.data[i].homework,
                                        complete = response.body()!!.data[i].hwPerformance,
                                        first=false
                                    )
                                )

                            }

                    }
                        datedatas= datedatas.sortedWith(compareBy<LogdateData>{it.month}.thenBy{it.day}).toMutableList()
                        datedatas = datedatas.distinct().toMutableList()
                        var j=0
                        if(datedatas.size>0) {
                            var mymon = "${datedatas[0].month}"+"${datedatas[0].day}"
                            var tmp:String=""
                            datedatas[0].first=true
                            for (i in 1 until datedatas.size) {
                                tmp="${datedatas[i].month}"+"${datedatas[i].day}"
                                if (tmp != mymon ){
                                    datedatas[i].first=true
                                    mymon = tmp

                                }
                            }
                        }


                        logdateAdapter= LogdateAdapter(view!!.context, datedatas)
                        rv_datelog.adapter=logdateAdapter
                        logdateAdapter.datas=datedatas
                        logdateAdapter.notifyDataSetChanged()
                       // rv_datelog.adapter = logdateAdapter
                    }else {
                        Log.d("실패", "${response.body()}")
                    }
                }
            }

        })
    }

/*
    private fun loaddateDatas() {
        datedatas.apply {
            add(
                LogdateData(
                    month = month1,
                    day = day1
                )
            )
            add(
                LogdateData(
                    month = month1,
                    day = day2
                )
            )
            add(
                LogdateData(
                    month = month1,
                    day = day3
                )
            )
        }
        logdateAdapter.datas = datedatas
        logdateAdapter.notifyDataSetChanged()
    }

 */
}