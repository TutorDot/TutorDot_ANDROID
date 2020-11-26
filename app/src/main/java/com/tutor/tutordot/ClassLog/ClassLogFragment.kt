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
import com.tutor.tutordot.ClassLog.LogdateRecyclerView.ser_color
import com.tutor.tutordot.ClassLog.Server.*
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

var mm: String=""
var dd: String=""
var yy: String=""

var haveData : Boolean = true

class ClassLogFragment : Fragment() {

    //서버 연결
    val logRequestToServer = LogRequestToServer

    lateinit var logdateAdapter: LogdateAdapter

    //현재 달 구하기
    val curDate= Calendar.getInstance()
    val month = curDate.get(Calendar.MONTH) + 1

    lateinit var leid : ArrayList<Int>
    lateinit var lename : ArrayList<String>
    var lecnt : Int = 0

    //특정일지 조회 id
    var lid : Int = 0

    //토글 사용 확인
    var toggle_check = false

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

        loaddateDatas(month) //데이터를 어댑터에 전달

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

                loaddateDatas(mon)
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

                    loaddateDatas(mon)

                    logdateAdapter.notifyDataSetChanged()
                }
            }
        })

        val popup =
            PopupMenu(context, btn_class_choice)
        //Inflating the Popup using xml file
        popup.menuInflater
            .inflate(R.menu.popup_menu, popup.menu)

        //수업정보 받아옴 (토글 위해)
        logRequestToServer.service.lectureRequest(
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
                        Log.d("토글 수업 정보", response.body()!!.data.toString())

                        lecnt = response.body()!!.data.size
                        Log.d("수업 개수", "{$lecnt}")

                        lename = ArrayList()
                        leid = ArrayList()
                        for(i in 1..lecnt) {
                            lename.add(response.body()!!.data[i - 1].lectureName)
                            leid.add(response.body()!!.data[i-1].lectureId)
                            //수업 개수에 맞게 토글 항목 추가
                            popup.menu.add(response.body()!!.data[i - 1].lectureName)
                        }
                        Log.d("토글 수업 이름", "{$lename}")
                        Log.d("토글 수업 번호", "{$leid}")

                    }else{
                        Log.d("토글 수업 정보", "실패")
                    }
                }
            }
        })


        //상단 수업 선택 메뉴
        ll_log_choice.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                popup.setOnMenuItemClickListener { item ->
                    tv_class_choice.text = item.title
                    if (item.title.equals("전체")) {
                        ll_progress.visibility = View.GONE
                        loaddateDatas(month)
                        toggle_check = false
                    }
                    else {
                        toggle_check = true
                        ll_progress.visibility = View.VISIBLE


                        for(i in 1..lecnt) {
                            if(item.title.equals(lename[i-1]))
                                lid = leid[i-1]
                        }
                        Log.d("아이디", "${lid}")

                        //프로그레스바 서버에서 정보 받아옴
                        logRequestToServer.service.progressRequest(
                            "${myjwt}", "${lid}"
                        ).enqueue(object : Callback<ProgressResponse> {
                            override fun onFailure(call: Call<ProgressResponse>, t: Throwable) {
                                Log.d("통신 실패", "통신 실패")
                            }

                            override fun onResponse(
                                call: Call<ProgressResponse>,
                                response: Response<ProgressResponse>
                            ) {
                                if (response.isSuccessful) {
                                    if (response.body()!!.success) {
                                        Log.d("프로그레스바 서버", "프로그레스바 서버")
                                        Log.d("성공", response.body()!!.data.toString())

                                        val procnt = response.body()!!.data.size-1

                                        progressDate = response.body()!!.data[procnt].classDate
                                        progressCycle = response.body()!!.data[procnt].depositCycle
                                        progressTimes = response.body()!!.data[procnt].times
                                        progressHour = response.body()!!.data[procnt].hour
                                        tv_progress_times.setText(progressTimes.toString() + "회차 " + progressHour.toString() + "시간")
                                        tv_progress_alltime.setText(progressCycle.toString() + "회차")
                                        progressStatus = 100 * progressTimes / progressCycle
                                        pb_class.progress = progressStatus
                                        tv_percent.setText(progressStatus.toString() + "%")

                                        Log.d("과목 입금 주기", progressCycle.toString())
                                        Log.d("과목 회차", progressTimes.toString())
                                        Log.d("과목 총 수업시간", progressHour.toString())
                                        Log.d("과목 프로그레스바 퍼센트", progressStatus.toString())
                                        Log.d("과목 날짜", progressDate)
                                    } else {
                                        Log.d("실패", "실패")
                                    }
                                }
                            }
                        })

                        // 토글에 따라 서버 요청
                        var datedatas : MutableList<LogdateData> = mutableListOf<LogdateData>()
                        logRequestToServer.service.diaryRequest(
                            "${myjwt}","${lid}"
                        ).enqueue(object : Callback<DiaryResponse> {
                            override fun onFailure(call: Call<DiaryResponse>, t: Throwable) {
                                Log.d("특정일지 통신 실패", "${t}")
                                haveData = false
                                ll_rv.visibility = View.GONE
                                cl_empty.visibility = View.VISIBLE
                            }

                            override fun onResponse(
                                call: Call<DiaryResponse>,
                                response: Response<DiaryResponse>
                            ) {
                                // 통신 성공
                                if (response.isSuccessful) {   // statusCode가 200-300 사이일 때, 응답 body 이용 가능
                                    if (response.body()!!.success) {
                                        Log.d("토글 일지 받기 성공", response.body()!!.data.toString())
                                        if (response.body()!!.data.size == 0) {
                                            haveData = false
                                            ll_rv.visibility = View.GONE
                                            cl_empty.visibility = View.VISIBLE
                                        }
                                        else {
                                            cl_empty.visibility = View.GONE
                                            ll_rv.visibility = View.VISIBLE
                                            haveData = true
                                        }

                                        //바깥쪽 날짜 데이터
                                        var i: Int = 0
                                        for (i in 0 until response.body()!!.data.size) {
                                            if(response.body()!!.data[i].classDate != null){
                                                var cd = response.body()!!.data[i].classDate.split("-")
                                                yy = cd[0]
                                                mm = cd[1]
                                                dd = cd[2]}
                                            if(mm.toInt()==month){

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
                                                            first = false,
                                                            diaryId = response.body()!!.data[i].diaryId
                                                        )
                                                    )

                                                }
                                            }

                                        }
                                        datedatas =
                                            datedatas.sortedWith(compareBy<LogdateData> { it.month }.thenBy { it.day })
                                                .toMutableList()
                                        datedatas = datedatas.distinct().toMutableList()
                                        var j = 0
                                        if (datedatas.size > 0) {
                                            var mymon = "${datedatas[0].month}" + "${datedatas[0].day}"
                                            var tmp: String = ""
                                            datedatas[0].first = true
                                            for (i in 1 until datedatas.size) {
                                                tmp = "${datedatas[i].month}" + "${datedatas[i].day}"
                                                if (tmp != mymon) {
                                                    datedatas[i].first = true
                                                    mymon = tmp

                                                }
                                            }
                                        }


                                        logdateAdapter = LogdateAdapter(view!!.context, datedatas)
                                        rv_datelog.adapter = logdateAdapter
                                        logdateAdapter.datas = datedatas
                                        logdateAdapter.notifyDataSetChanged()
                                        // rv_datelog.adapter = logdateAdapter
                                    } else {
                                        Log.d("실패", "${response.body()}")
                                        haveData = false
                                        ll_rv.visibility = View.GONE
                                        cl_empty.visibility = View.VISIBLE
                                    }
                                }
                            }
                        })

                    }
                    true
                }
                popup.show() //showing popup menu
            }
        })
    }

    //서버 연결
    private fun loaddateDatas(monn : Int){
        if(!toggle_check){
            var datedatas : MutableList<LogdateData> = mutableListOf<LogdateData>()
            // 서버 요청
            logRequestToServer.service.logRequest(
                "${myjwt}"
            ).enqueue(object : Callback<LogResponse> {
                override fun onFailure(call: Call<LogResponse>, t: Throwable) {
                    Log.d("통신 실패", "${t}")
                    haveData = false
                    ll_rv.visibility = View.GONE
                    cl_empty.visibility = View.VISIBLE
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
                            if (response.body()!!.data.size == 0) {
                                haveData = false
                                ll_rv.visibility = View.GONE
                                cl_empty.visibility = View.VISIBLE
                            }
                            else {
                                cl_empty.visibility = View.GONE
                                ll_rv.visibility = View.VISIBLE
                                haveData = true
                            }

                            //바깥쪽 날짜 데이터
                            var i: Int = 0
                            for (i in 0 until response.body()!!.data.size) {
                                if(response.body()!!.data[i].classDate != null){
                                    var cd = response.body()!!.data[i].classDate.split("-")
                                    yy = cd[0]
                                    mm = cd[1]
                                    dd = cd[2]}
                                if(mm.toInt()==monn){

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
                                                first = false,
                                                diaryId = response.body()!!.data[i].diaryId
                                            )
                                        )

                                    }
                                }

                            }
                            datedatas =
                                datedatas.sortedWith(compareBy<LogdateData> { it.month }.thenBy { it.day })
                                    .toMutableList()
                            datedatas = datedatas.distinct().toMutableList()
                            var j = 0
                            if (datedatas.size > 0) {
                                var mymon = "${datedatas[0].month}" + "${datedatas[0].day}"
                                var tmp: String = ""
                                datedatas[0].first = true
                                for (i in 1 until datedatas.size) {
                                    tmp = "${datedatas[i].month}" + "${datedatas[i].day}"
                                    if (tmp != mymon) {
                                        datedatas[i].first = true
                                        mymon = tmp

                                    }
                                }
                            }


                            logdateAdapter = LogdateAdapter(view!!.context, datedatas)
                            rv_datelog.adapter = logdateAdapter
                            logdateAdapter.datas = datedatas
                            logdateAdapter.notifyDataSetChanged()
                            // rv_datelog.adapter = logdateAdapter
                        } else {
                            Log.d("실패", "${response.body()}")
                            haveData = false
                            ll_rv.visibility = View.GONE
                            cl_empty.visibility = View.VISIBLE
                        }
                    }
                }
            })
        } else{     //특정 일지 월 이동
            var datedatas : MutableList<LogdateData> = mutableListOf<LogdateData>()
            logRequestToServer.service.diaryRequest(
                "${myjwt}","${lid}"
            ).enqueue(object : Callback<DiaryResponse> {
                override fun onFailure(call: Call<DiaryResponse>, t: Throwable) {
                    Log.d("특정일지 통신 실패", "${t}")
                    haveData = false
                    ll_rv.visibility = View.GONE
                    cl_empty.visibility = View.VISIBLE
                }

                override fun onResponse(
                    call: Call<DiaryResponse>,
                    response: Response<DiaryResponse>
                ) {
                    // 통신 성공
                    if (response.isSuccessful) {   // statusCode가 200-300 사이일 때, 응답 body 이용 가능
                        if (response.body()!!.success) {
                            Log.d("토글 일지 받기 성공", response.body()!!.data.toString())
                            if (response.body()!!.data.size == 0) {
                                haveData = false
                                ll_rv.visibility = View.GONE
                                cl_empty.visibility = View.VISIBLE
                            }
                            else {
                                cl_empty.visibility = View.GONE
                                ll_rv.visibility = View.VISIBLE
                                haveData = true
                            }

                            //바깥쪽 날짜 데이터
                            var i: Int = 0
                            for (i in 0 until response.body()!!.data.size) {
                                if(response.body()!!.data[i].classDate != null){
                                    var cd = response.body()!!.data[i].classDate.split("-")
                                    yy = cd[0]
                                    mm = cd[1]
                                    dd = cd[2]}
                                if(mm.toInt()==monn){

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
                                                first = false,
                                                diaryId = response.body()!!.data[i].diaryId
                                            )
                                        )

                                    }
                                }

                            }
                            datedatas =
                                datedatas.sortedWith(compareBy<LogdateData> { it.month }.thenBy { it.day })
                                    .toMutableList()
                            datedatas = datedatas.distinct().toMutableList()
                            var j = 0
                            if (datedatas.size > 0) {
                                var mymon = "${datedatas[0].month}" + "${datedatas[0].day}"
                                var tmp: String = ""
                                datedatas[0].first = true
                                for (i in 1 until datedatas.size) {
                                    tmp = "${datedatas[i].month}" + "${datedatas[i].day}"
                                    if (tmp != mymon) {
                                        datedatas[i].first = true
                                        mymon = tmp

                                    }
                                }
                            }


                            logdateAdapter = LogdateAdapter(view!!.context, datedatas)
                            rv_datelog.adapter = logdateAdapter
                            logdateAdapter.datas = datedatas
                            logdateAdapter.notifyDataSetChanged()
                            // rv_datelog.adapter = logdateAdapter
                        } else {
                            Log.d("실패", "${response.body()}")
                            haveData = false
                            ll_rv.visibility = View.GONE
                            cl_empty.visibility = View.VISIBLE
                        }
                    }
                }
            })
        }
    }

    /*
    private fun loaddateDatas() {
        var datedatas : MutableList<LogdateData> = mutableListOf<LogdateData>()
        // 서버 요청
        logRequestToServer.service.logRequest(
            "${myjwt}"
        ).enqueue(object : Callback<LogResponse> {
            override fun onFailure(call: Call<LogResponse>, t: Throwable) {
                Log.d("통신 실패", "${t}")
                haveData = false
                ll_rv.visibility = View.GONE
                cl_empty.visibility = View.VISIBLE
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
                        if (response.body()!!.data.size == 0) {
                            haveData = false
                            ll_rv.visibility = View.GONE
                            cl_empty.visibility = View.VISIBLE
                        }
                        else {
                            cl_empty.visibility = View.GONE
                            ll_rv.visibility = View.VISIBLE
                            haveData = true
                        }

                        //바깥쪽 날짜 데이터
                        var i: Int = 0
                        for (i in 0 until response.body()!!.data.size) {
                            if(response.body()!!.data[i].classDate != null){
                                var cd = response.body()!!.data[i].classDate.split("-")
                                yy = cd[0]
                                mm = cd[1]
                                dd = cd[2]}

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
                                        first = false,
                                        diaryId = response.body()!!.data[i].diaryId
                                    )
                                )

                            }

                        }
                        datedatas =
                            datedatas.sortedWith(compareBy<LogdateData> { it.month }.thenBy { it.day })
                                .toMutableList()
                        datedatas = datedatas.distinct().toMutableList()
                        var j = 0
                        if (datedatas.size > 0) {
                            var mymon = "${datedatas[0].month}" + "${datedatas[0].day}"
                            var tmp: String = ""
                            datedatas[0].first = true
                            for (i in 1 until datedatas.size) {
                                tmp = "${datedatas[i].month}" + "${datedatas[i].day}"
                                if (tmp != mymon) {
                                    datedatas[i].first = true
                                    mymon = tmp

                                }
                            }
                        }


                        logdateAdapter = LogdateAdapter(view!!.context, datedatas)
                        rv_datelog.adapter = logdateAdapter
                        logdateAdapter.datas = datedatas
                        logdateAdapter.notifyDataSetChanged()

                    } else {
                        Log.d("실패", "${response.body()}")
                        haveData = false
                        ll_rv.visibility = View.GONE
                        cl_empty.visibility = View.VISIBLE
                    }
                }
            }
        })
    }*/
}