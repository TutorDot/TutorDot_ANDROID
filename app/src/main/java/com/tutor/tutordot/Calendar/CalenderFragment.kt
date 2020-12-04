package com.tutor.tutordot.Calendar

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.CalendarMode
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.tutor.tutordot.Calendar.CalendarLogRecyclerView.CalendarLogAdapter
import com.tutor.tutordot.Calendar.CalendarLogRecyclerView.CalendarLogData
import com.tutor.tutordot.Calendar.CalendarLogRecyclerView.haveCalendarData
import com.tutor.tutordot.Calendar.Server.*
import com.tutor.tutordot.ClassLog.Server.LectureResponse
import com.tutor.tutordot.LoadingDialog
import com.tutor.tutordot.R
import com.tutor.tutordot.StartServer.RequestToServer
import com.tutor.tutordot.Startpage.looking
import com.tutor.tutordot.Startpage.myjwt
import com.tutor.tutordot.Startpage.role
import kotlinx.android.synthetic.main.fragment_calender.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.concurrent.Executors
import kotlin.collections.ArrayList


class CalenderFragment : Fragment() {
    // 일정 추가 서버 연결
    val requestToServer = RequestToServer

    // 캘린더 일정 더미
    val datas: MutableList<CalendarLogData> = mutableListOf<CalendarLogData>()
    lateinit var calendarLogAdapter: CalendarLogAdapter

    //현재 달 구하기
    val curDate = Calendar.getInstance()

//    var time: String? = null
//    var kcal: kotlin.String? = null
    var menu: kotlin.String? = null
    private val oneDayDecorator = OneDayDecorator()
    val sundayDecorator = SundayDecorator()
//    var cursor: Cursor? = null

    lateinit var materialCalendarView: MaterialCalendarView

    lateinit var leid1 : ArrayList<Int>
    lateinit var lename1 : ArrayList<String>
    lateinit var lecolor1 : ArrayList<String>
    var lecnt1 : Int = 0
    lateinit var lcolor : String

    var select : Boolean = false
    var toggleselect1 : Boolean = false
    var lid : Int = 0

    lateinit var dd : CalendarDay

    var redday2: List<String> = listOf()
    var yellowday2: List<String> = listOf()
    var greenday2: List<String> = listOf()
    var blueday2: List<String> = listOf()
    var purpleday2: List<String> = listOf()

    val calendarlogRequestToServer = CalendarLogRequestToServer

    private lateinit var dialogforcal:Dialog


    inner class ApiSimulator internal constructor(var Time_Result: List<String>, var vs: String) :
        AsyncTask<Void, Void, List<CalendarDay>>() {
        @Suppress("UNREACHABLE_CODE")

        override fun doInBackground(vararg voids: Void): List<CalendarDay> {
            try {
                Thread.sleep(500)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            val calendar = Calendar.getInstance()
            val dates: ArrayList<CalendarDay> = ArrayList()
            val dates2: ArrayList<CalendarDay> = ArrayList()
            val dates3: ArrayList<CalendarDay> = ArrayList()
            val dates4: ArrayList<CalendarDay> = ArrayList()
            val dates5: ArrayList<CalendarDay> = ArrayList()

            /*특정날짜 달력에 점표시해주는곳 -> 점 색깔별로 날짜 저장*/
            /*월은 0이 1월 년,일은 그대로*/
            //string 문자열인 Time_Result 을 받아와서 -를 기준으로 자르고 string을 int 로 변환
            for (i in Time_Result.indices) {
                //val day = CalendarDay.from(calendar)
                val time = Time_Result[i].split("-".toRegex()).toTypedArray()
                val year = time[0].toInt()
                val month = time[1].toInt()
                val dayy = time[2].toInt()

                // 날짜 마지막 안 찍히고 오늘 날짜에도 찍히는 것 해결한 부분
                calendar[year, month - 1] = dayy
                val day = CalendarDay.from(calendar)
                when (vs) {
                    "red" -> {
                        dates.add(day)
                    }
                    "yellow" -> {
                        dates2.add(day)
                    }
                    "green" -> {
                        dates3.add(day)
                    }
                    "blue" -> {
                        dates4.add(day)
                    }
                    "purple" -> {
                        dates5.add(day)
                    }
                    //                else {
                    //                    dates.add(day)
                    //                }
                }
            }
            Log.d("빨", "${dates}")
            Log.d("노", "${dates2}")
            Log.d("초", "${dates3}")
            Log.d("파", "${dates4}")
            Log.d("보", "${dates5}")

            return when(vs){
                "red" -> dates
                "yellow" -> dates2
                "green" -> dates3
                "blue" -> dates4
                "purple" -> dates5
                else -> dates
            }
        }

        override fun onPostExecute(calendarDays: List<CalendarDay>) {
            super.onPostExecute(calendarDays)

            // 시도1,2(this@CalenderFramgent 빼고 실행) -> 점 1개 찍힘
            if (vs == "red"){      // 빨간점 찍기
                materialCalendarView.addDecorator(
                    EventDecorator(Color.parseColor("#f28d8d"), calendarDays
//                    this@CalenderFragment
                    )
                )
            }
            if (vs == "yellow"){    // 노란점 찍기
                materialCalendarView.addDecorator(
                    EventDecorator(Color.parseColor("#ffe966"), calendarDays
//                    this@CalenderFragment
                    )
                )
            }
            if (vs == "green"){     // 초록점 찍기
                materialCalendarView.addDecorator(
                    EventDecorator(Color.parseColor("#90d672"), calendarDays
//                    this@CalenderFragment
                    )
                )
            }
            if (vs == "blue"){      // 파란점 찍기
                materialCalendarView.addDecorator(
                    EventDecorator(Color.parseColor("#86d5e3"), calendarDays
//                    this@CalenderFragment
                    )
                )
            }
            if (vs == "purple"){    // 보라점 찍기
                materialCalendarView.addDecorator(
                    EventDecorator(Color.parseColor("#b88de3"), calendarDays
//                    this@CalenderFragment
                    )
                )
            }

//            // 시도3(현재적용) -> 점 여러개 찍힘
//            materialCalendarView.addDecorator(
//                context?.let { EventDecorator(it, calendarDays) }
//            )

//            dialogforcal.dismiss()
        }

//        override fun onCancelled() {
//            super.onCancelled()
//            Log.d("비동기 종료", "ok")
//        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calender, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialogforcal = LoadingDialog(view!!.context)
        CoroutineScope(Dispatchers.Main).launch {
            dialogforcal.show()


            materialCalendarView = view.findViewById(R.id.calendarView) as MaterialCalendarView
            materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2019, 0, 1)) // 달력의 시작
                .setMaximumDate(CalendarDay.from(2030, 11, 31)) // 달력의 끝
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit()
//         materialCalendarView.isDynamicHeightEnabled = true   // 월에 따라 캘린더 크기 동적변화

            // 2차 릴리즈 때 캘린더 디자인 수정 (캘린더 윗부분 글자 앞으로)
//        val testcaledar = view.findViewById(R.id.tv_calendar) as TextView
//        testcaledar.bringToFront()


            // 오늘 날짜 보여주기 (둘러보기 날짜 고정)
            if (looking == true) {
                materialCalendarView.setCurrentDate(CalendarDay.from(2020, 11, 16))
                calendarlog_all_date.text = "16"
                calendarlog_all_month.text = "12월"
            } else {
                calendarlog_all_date.text = curDate.get(Calendar.DATE).toString()
                calendarlog_all_month.text = (curDate.get(Calendar.MONTH) + 1).toString() + "월"
            }


            Log.d("제일처음시작", "전체출력")
            calAlldata()
            Log.d("제일처음시작", "클릭출력")
            clickAlldata()

            // 점찍을 날짜
            val redresult = redday2
            Log.d("redresult2", "${redresult}")

            val yellowresult = yellowday2
            Log.d("yellowresult2", "${yellowresult}")

            val greenresult = greenday2
            val blueresult = blueday2
            val purpleresult = purpleday2


            ApiSimulator(redresult, "red").executeOnExecutor(Executors.newSingleThreadExecutor())  // 빨간점 찍는날
            ApiSimulator(yellowresult, "yellow").executeOnExecutor(Executors.newSingleThreadExecutor())  // 노란점 찍는날
            ApiSimulator(greenresult, "green").executeOnExecutor(Executors.newSingleThreadExecutor())  // 초록점 찍는날
            ApiSimulator(blueresult, "blue").executeOnExecutor(Executors.newSingleThreadExecutor())  // 파란점 찍는날
            ApiSimulator(purpleresult, "purple").executeOnExecutor(Executors.newSingleThreadExecutor())  // 보라점 찍는날


            // 일요일 이벤트
            materialCalendarView.addDecorators(sundayDecorator)

            // 오늘날짜 이벤트
            if (looking == true) {
                materialCalendarView.addDecorators(
                    CurrentDayDecorator(activity, CalendarDay.from(2020, 11, 16))
                )
            } else {
                materialCalendarView.addDecorators(
                    CurrentDayDecorator(activity, CalendarDay.today())
                )
            }

        }


        // 튜티에겐 플로팅 버튼 보이지 않음
        if(role == "tutee"){
            floatingActionButton.visibility = View.GONE
        }

        //수업정보 받아옴 (토글 위해)
        val popup = PopupMenu(context, calendar_select)
        //Inflating the Popup using xml file
        popup.menuInflater
            .inflate(R.menu.popup_menu_cal1, popup.menu)


        calendarlogRequestToServer.service.callectureRequest(
            "${myjwt}"
        ).enqueue(object :Callback<CalLectureResponse>{
            override fun onFailure(call: Call<CalLectureResponse>, t: Throwable) {
                Log.d("통신 실패", "통신 실패")
            }

            override fun onResponse(
                call: Call<CalLectureResponse>,
                response: Response<CalLectureResponse>
            ) {
                if(response.isSuccessful){
                    if(response.body()!!.success){
                        Log.d("토글 수업 정보1", "성공")

                        lecnt1 = response.body()!!.data.size
                        lename1 = ArrayList()
                        leid1 = ArrayList()
                        lecolor1 = ArrayList()

                        for(i in 1..lecnt1) {
                            lename1.add(response.body()!!.data[i - 1].lectureName)
                            leid1.add(response.body()!!.data[i-1].lectureId)
                            lecolor1.add(response.body()!!.data[i - 1].color)
                            //수업 개수에 맞게 토글 항목 추가
                            popup.menu.add(response.body()!!.data[i - 1].lectureName)
                        }

                    }else{
                        Log.d("토글 수업 정보1", "실패")
                    }
                }
            }
        })


        //상단 수업 선택 메뉴
        constarintlayout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                popup.setOnMenuItemClickListener { item ->
                    tv_calendar_title.text = item.title

                    if (item.title.equals("전체")) {
                        toggleselect1 = false
                        calAlldata()
                        clickAlldata()
                        //여기 동작안함?
                    }

                    // 특정 수업 서버 연결
                    else {
//                        var lid : Int = 0
                        for(i in 1..lecnt1) {
                            if(item.title.equals(lename1[i-1])){
                                lid = leid1[i-1]
                                lcolor = lecolor1[i - 1]
                            }
                        }
                        Log.d("아이디", "${lid}")
                        toggleselect1 = true

                        // 기존 점 지우기
//                        materialCalendarView.removeDecorator(EventDecorator(Color.parseColor("#f28d8d"), redday2))
                        materialCalendarView.removeDecorators()

                        // 일요일 이벤트 다시
                        materialCalendarView.addDecorators(sundayDecorator)

                        // 오늘날짜 이벤트 다시
                       if (looking == true) {
                            materialCalendarView.addDecorators(
                                CurrentDayDecorator(activity, CalendarDay.from(2020, 11, 16))
                            )
                        } else {
                            materialCalendarView.addDecorators(
                                CurrentDayDecorator(activity, CalendarDay.today())
                            )
                        }

                        //오늘기준 수업표시
                        // 서버 연결
                        val datas: MutableList<CalendarData> = mutableListOf<CalendarData>()
                        calendarLogAdapter= CalendarLogAdapter(view!!.context, datas)

                        //val calendarlogRequestToServer = CalendarLogRequestToServer
                        lateinit var myyear : String

                        if(looking == true){
                            myyear = "2020"
                        }
                        else{
                            myyear = CalendarDay.today().year.toString()
                        }
                        var mymonth = calendarlog_all_month.text.toString().replace("월", "")
                        var mydate = calendarlog_all_date.text.toString()

                        if (mymonth.toInt() < 10) {
                            mymonth = "0$mymonth"
                        }
                        if (mydate.toInt() < 10) {
                            mydate = "0$mydate"
                        }
                        var formatted = "$myyear-$mymonth-$mydate"

                        var redday:MutableList<String> = mutableListOf()
                        var yellowday:MutableList<String> = mutableListOf()
                        var greenday:MutableList<String> = mutableListOf()
                        var blueday:MutableList<String> = mutableListOf()
                        var purpleday:MutableList<String> = mutableListOf()

                        val calendarlogRequestToServer = CalendarLogRequestToServer

                        // 서버 요청
                        calendarlogRequestToServer.service.calRequest(
                            "${myjwt}","${lid}"
                        ).enqueue(object : Callback<CalResponse> {
                            override fun onFailure(call: Call<CalResponse>, t: Throwable) {
                                Log.d("1 특정 통신 실패", "${t}")
                            }

                            override fun onResponse(
                                call: Call<CalResponse>,
                                response: Response<CalResponse>
                            ) {
                                // 통신 성공
                                if (response.isSuccessful) {   // statusCode가 200-300 사이일 때, 응답 body 이용 가능
                                    if (response.body()!!.success) {  // 참고 코드에서 없는 부분
                                        Log.d("확인1", "ok2")

                                        // 데이터 전체 한번에 받아와서 날짜 같으면 그 날짜 데이터 추가
                                        var i: Int = 0
                                        var c: Int = 0
                                        for (i in 0 until response.body()!!.data.size) {
                                            Log.d("확인1", "ok")
                                            // 점 찍을 날짜 배열에 추가
                                            when(response.body()!!.data[i].color) {
                                                "red" -> {
                                                    var c: Int = 0
                                                    redday.add(c, response.body()!!.data[i].classDate)//
                                                    Log.d("빨강날", "${redday}")
                                                    c += 1

                                                    redday2 = redday.distinct()
                                                    Log.d("진짜빨강날", "${redday2}")
                                                }
                                                "yellow" -> {
                                                    var c: Int = 0
                                                    yellowday.add(c, response.body()!!.data[i].classDate)//
                                                    Log.d("노랑날222", "${yellowday}")
                                                    c += 1

                                                    yellowday2 = yellowday.distinct()
                                                    Log.d("진짜노랑날", "${yellowday2}")
                                                }
                                                "green" -> {
                                                    var c: Int = 0
                                                    greenday.add(c, response.body()!!.data[i].classDate)//
                                                    Log.d("초록날", "${greenday}")
                                                    c += 1

                                                    greenday2 = greenday.distinct()
                                                    Log.d("진짜초록날", "${greenday2}")
                                                }
                                                "blue" -> {
                                                    var c: Int = 0
                                                    blueday.add(c, response.body()!!.data[i].classDate)//
                                                    Log.d("파랑날", "${blueday}")
                                                    c += 1

                                                    blueday2 = blueday.distinct()
                                                    Log.d("진짜파랑날", "${blueday2}")
                                                }
                                                "purple" -> {
                                                    var c: Int = 0
                                                    purpleday.add(c, response.body()!!.data[i].classDate)//
                                                    Log.d("보라날", "${purpleday}")
                                                    c += 1

                                                    purpleday2 = purpleday.distinct()
                                                    Log.d("진짜보라날", "${purpleday2}")
                                                }
                                            }

                                        if (formatted.toString() == response.body()!!.data[i].classDate) {
                                                Log.d("test2", "동일")
                                                Log.d("test2", "${response.body()!!.data[i].classDate}")
                                                datas.apply {
                                                    add(
                                                        CalendarData(
                                                            classId = "${response.body()!!.data[i].classId}".toInt(),
                                                            startTime = "${response.body()!!.data[i].startTime}",
                                                            endTime = "${response.body()!!.data[i].endTime}",
                                                            color = "${response.body()!!.data[i].color}",
                                                            times = "${response.body()!!.data[i].times}".toInt(),
                                                            lectureName = "${response.body()!!.data[i].lectureName}",
                                                            hour = "${response.body()!!.data[i].hour}".toInt(),
                                                            location = "${response.body()!!.data[i].location}",
                                                            classDate = "${response.body()!!.data[i].classDate}"
                                                        )
                                                    )
                                                }
                                                Log.i("test", "${response.body()!!.data[i].lectureName}")
                                                Log.i("test", "${response.body()!!.data[i].color}")
                                                calendarLogAdapter.notifyDataSetChanged()
                                            } else {
                                                continue
                                            }
                                        }

                                        // 점찍을 날짜
                                        when(lcolor){
                                            "red" -> ApiSimulator(redday2, "red").executeOnExecutor(Executors.newSingleThreadExecutor())  // 빨간점 찍는날
                                            "yellow" -> ApiSimulator(yellowday2, "yellow").executeOnExecutor(Executors.newSingleThreadExecutor())  // 노란점 찍는날
                                            "green" -> ApiSimulator(greenday2, "green").executeOnExecutor(Executors.newSingleThreadExecutor())  // 초록점 찍는날
                                            "blue" -> ApiSimulator(blueday2, "blue").executeOnExecutor(Executors.newSingleThreadExecutor())  // 파란점 찍는날
                                            "purple" -> ApiSimulator(purpleday2, "purple").executeOnExecutor(Executors.newSingleThreadExecutor())  // 보라점 찍는날
                                        }
                                    }
                                    if(datas.size == 0) {
                                        cl_calendar_empty.visibility = View.VISIBLE
                                        rv_calendarlog.visibility = View.GONE
                                        haveCalendarData = false
                                    }

                                    else {
                                        cl_calendar_empty.visibility = View.GONE
                                        rv_calendarlog.visibility = View.VISIBLE
                                        haveCalendarData = true
                                    }
                                    calendarLogAdapter = CalendarLogAdapter(getActivity()!!.getApplicationContext(), datas)
                                    calendarLogAdapter.notifyDataSetChanged()
                                    rv_calendarlog.adapter = calendarLogAdapter

                                } else {
                                    Log.d("1 특정 실패", "${response.message()}")
                                }
                            }
                        })

                        // 캘린더 날짜 클릭 변경
                        materialCalendarView.setOnDateChangedListener { widget, date, selected ->
                            val Year = date.year
                            var Month = (date.month + 1).toString()
                            var Day = (date.day).toString()
                            var datas: MutableList<CalendarData> = mutableListOf<CalendarData>()
                            calendarlog_all_date.text = "${Day}"
                            calendarlog_all_month.text = "$Month" + "월"

                            dd = date
                            Log.d("여긴 상단바에서 클릭하면", "오는 곳인데")

                            // 날짜 포맷 통일
                            if (Month.toInt() < 10) {
                                Month = "0$Month"
                            }
                            if (Day.toInt() < 10) {
                                Day = "0$Day"
                            }

                            val shot_Day = "$Year-$Month-$Day"
                            Log.i("shot_Day test", shot_Day + "")

                            // 서버 연결
                            calendarLogAdapter= CalendarLogAdapter(view!!.context, datas)

                            val calendarlogRequestToServer = CalendarLogRequestToServer
                            // 서버 요청
                            calendarlogRequestToServer.service.calRequest(
                                "${myjwt}", "${lid}"
                            ).enqueue(object : Callback<CalResponse> {
                                override fun onFailure(call: Call<CalResponse>, t: Throwable) {
                                    Log.d("1 특정 통신 실패", "${t}")
                                }

                                override fun onResponse(
                                    call: Call<CalResponse>,
                                    response: Response<CalResponse>
                                ) {
                                    // 통신 성공
                                    if (response.isSuccessful) {   // statusCode가 200-300 사이일 때, 응답 body 이용 가능
                                        if (response.body()!!.success) {  // 참고 코드에서 없는 부분

                                            Log.d("받아온 데이터 ", response.body()!!.data.toString())

                                            var i: Int = 0
                                            for (i in 0 until response.body()!!.data.size) {
                                                Log.d("인덱스", "${i}")

                                                if (shot_Day == response.body()!!.data[i].classDate) {
                                                    Log.d("test", "동일")
                                                    Log.d("test", "${response.body()!!.data[i].classDate}")
                                                    datas.apply {
                                                        add(
                                                            CalendarData(
                                                                classId = "${response.body()!!.data[i].classId}".toInt(),
                                                                startTime = "${response.body()!!.data[i].startTime}",
                                                                endTime = "${response.body()!!.data[i].endTime}",
                                                                color = "${response.body()!!.data[i].color}",
                                                                times = "${response.body()!!.data[i].times}".toInt(),
                                                                lectureName = "${response.body()!!.data[i].lectureName}",
                                                                hour = "${response.body()!!.data[i].hour}".toInt(),
                                                                location = "${response.body()!!.data[i].location}",
                                                                classDate = "${response.body()!!.data[i].classDate}"
                                                            )
                                                        )
                                                    }
                                                    Log.i("test", "${response.body()!!.data[i].lectureName}")
                                                    Log.i("test", "${response.body()!!.data[i].color}")
                                                    calendarLogAdapter.notifyDataSetChanged()
                                                } else {
                                                    continue
                                                }
                                            }
//                                            toggleData()  // 왜 특정수업 선택했을 때 resume하면 여기로 올까

                                        }
                                        if(datas.size == 0) {
                                            cl_calendar_empty.visibility = View.VISIBLE
                                            rv_calendarlog.visibility = View.GONE
                                            haveCalendarData = false
                                        }

                                        else {
                                            cl_calendar_empty.visibility = View.GONE
                                            rv_calendarlog.visibility = View.VISIBLE
                                            haveCalendarData = true
                                        }
                                        calendarLogAdapter = CalendarLogAdapter(getActivity()!!.getApplicationContext(), datas)
                                        calendarLogAdapter.notifyDataSetChanged()
                                        rv_calendarlog.adapter = calendarLogAdapter

                                    } else {
                                        Log.d("1 특정 실패", "${response.message()}")

                                    }
                                }
                            })
                            // 여기 있으면 그 날짜 클릭하고 바로 회색 표시가 사라짐
                            //materialCalendarView.clearSelection()
                        }
                    }
                    true
                }
                popup.show() //showing popup menu
            }
        })




        // 플로팅 버튼 누르면 일정 추가
        floatingActionButton.setOnClickListener {
            if (role == "tutor" && looking == true){
                Toast.makeText(activity, "둘러보기 계정은 일정추가가 불가능합니다.", Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(activity, ScheduleAddActivity::class.java)
                startActivity(intent)
            }
        }

    }

    override fun onResume() {
        super.onResume()

        dialogforcal = LoadingDialog(view!!.context)
        CoroutineScope(Dispatchers.Main).launch {
            dialogforcal.show()
//            delay(2000)
//            dialogforcal.dismiss()
        }

        Log.d("다시시작","전체출력")

        // 상단 수업 목록 받아오기 (토글메뉴)
        val popup = PopupMenu(context, calendar_select)
        //Inflating the Popup using xml file
        popup.menuInflater
            .inflate(R.menu.popup_menu_cal1, popup.menu)


        calendarlogRequestToServer.service.callectureRequest(
            "${myjwt}"
        ).enqueue(object :Callback<CalLectureResponse>{
            override fun onFailure(call: Call<CalLectureResponse>, t: Throwable) {
                Log.d("통신 실패", "통신 실패")
            }

            override fun onResponse(
                call: Call<CalLectureResponse>,
                response: Response<CalLectureResponse>
            ) {
                if(response.isSuccessful){
                    if(response.body()!!.success){
                        Log.d("토글 수업 정보1", "성공")

                        lecnt1 = response.body()!!.data.size
                        lename1 = ArrayList()
                        leid1 = ArrayList()
                        lecolor1 = ArrayList()

                        for(i in 1..lecnt1) {
                            lename1.add(response.body()!!.data[i - 1].lectureName)
                            leid1.add(response.body()!!.data[i-1].lectureId)
                            lecolor1.add(response.body()!!.data[i - 1].color)
                            //수업 개수에 맞게 토글 항목 추가
                            popup.menu.add(response.body()!!.data[i - 1].lectureName)
                        }

                    }else{
                        Log.d("토글 수업 정보1", "실패")
                    }
                }
            }
        })

//        if(toggleselect1 == true){
//            Log.d("토글선택","됨")
//            toggleData()
//        }
//        if(toggleselect1 == false){
//            Log.d("토글선택","안됨")
//            calAlldata()
//        }
        calAlldata() // 이거 없애면 일정삭제는 점 반영되지만 수업추가가 안됨, 놔두면 반대임. 수업해제는 둘 다 안됨

        // 점찍을 날짜
        val redresult = redday2
        val yellowresult = yellowday2
        val greenresult = greenday2
        val blueresult = blueday2
        val purpleresult = purpleday2

        ApiSimulator(redresult, "red").executeOnExecutor(Executors.newSingleThreadExecutor())  // 빨간점 찍는날
        ApiSimulator(yellowresult, "yellow").executeOnExecutor(Executors.newSingleThreadExecutor())  // 노란점 찍는날
        ApiSimulator(greenresult, "green").executeOnExecutor(Executors.newSingleThreadExecutor())  // 초록점 찍는날
        ApiSimulator(blueresult, "blue").executeOnExecutor(Executors.newSingleThreadExecutor())  // 파란점 찍는날
        ApiSimulator(purpleresult, "purple").executeOnExecutor(Executors.newSingleThreadExecutor())  // 보라점 찍는날
    }

    private fun calAlldata(){
        //오늘기준 수업표시
        // 서버 연결
        val datas: MutableList<CalendarData> = mutableListOf<CalendarData>()
        calendarLogAdapter= CalendarLogAdapter(view!!.context, datas)

        //val calendarlogRequestToServer = CalendarLogRequestToServer
        lateinit var myyear : String

        if(looking == true){
            myyear = "2020"
        }
        else{
            myyear = CalendarDay.today().year.toString()
        }
        var mymonth = calendarlog_all_month.text.toString().replace("월", "")
        var mydate = calendarlog_all_date.text.toString()

        if (mymonth.toInt() < 10) {
            mymonth = "0$mymonth"
        }
        if (mydate.toInt() < 10) {
            mydate = "0$mydate"
        }
        var formatted = "$myyear-$mymonth-$mydate"

        var redday:MutableList<String> = mutableListOf()
        var yellowday:MutableList<String> = mutableListOf()
        var greenday:MutableList<String> = mutableListOf()
        var blueday:MutableList<String> = mutableListOf()
        var purpleday:MutableList<String> = mutableListOf()

        val calendarlogRequestToServer = CalendarLogRequestToServer

        // 서버 요청
        calendarlogRequestToServer.service.calendarlogRequest(
            "${myjwt}"
        ).enqueue(object : Callback<CalendarLogResponseData> {
            override fun onFailure(call: Call<CalendarLogResponseData>, t: Throwable) {
                Log.d("통신 실패", "${t}")
            }

            override fun onResponse(
                call: Call<CalendarLogResponseData>,
                response: Response<CalendarLogResponseData>
            ) {
                // 통신 성공
                if (response.isSuccessful) {   // statusCode가 200-300 사이일 때, 응답 body 이용 가능
                    if (response.body()!!.success) {  // 참고 코드에서 없는 부분

                        // 데이터 전체 한번에 받아와서 점 색깔별로 날짜 배열 추가
                        var i: Int = 0
                        for (i in 0 until response.body()!!.data.size) {
                            // 점 찍을 날짜 배열에 추가
                            when(response.body()!!.data[i].color){
                                "red" -> {
                                    var c: Int = 0
                                    redday.add(c,response.body()!!.data[i].classDate)//
                                    Log.d("빨강날", "${redday}")
                                    c += 1

                                    redday2 = redday.distinct()
                                    Log.d("진짜빨강날", "${redday2}")
                                }
                                "yellow" -> {
                                    var c: Int = 0
                                    yellowday.add(c,response.body()!!.data[i].classDate)//
                                    Log.d("노랑날", "${yellowday}")
                                    c += 1

                                    yellowday2 = yellowday.distinct()
                                    Log.d("진짜노랑날", "${yellowday2}")
                                }
                                "green" -> {
                                    var c: Int = 0
                                    greenday.add(c,response.body()!!.data[i].classDate)//
                                    Log.d("초록날", "${greenday}")
                                    c += 1

                                    greenday2 = greenday.distinct()
                                    Log.d("진짜초록날", "${greenday2}")
                                }
                                "blue" -> {
                                    var c: Int = 0
                                    blueday.add(c,response.body()!!.data[i].classDate)//
                                    Log.d("파랑날", "${blueday}")
                                    c += 1

                                    blueday2 = blueday.distinct()
                                    Log.d("진짜파랑날", "${blueday2}")
                                }
                                "purple" -> {
                                    var c: Int = 0
                                    purpleday.add(c,response.body()!!.data[i].classDate)//
                                    Log.d("보라날", "${purpleday}")
                                    c += 1

                                    purpleday2 = purpleday.distinct()
                                    Log.d("진짜보라날", "${purpleday2}")
                                }
                            }

                            // 데이터 전체 한번에 받아와서 날짜 같으면 그 날짜 데이터 추가(오늘날짜)
                            if (formatted.toString() == response.body()!!.data[i].classDate) {
                                Log.d("calandertest", "동일")
//                                Log.d("test", "${response.body()!!.data[i].classDate}")
                                datas.apply {
                                    add(
                                        CalendarData(
                                            classId = "${response.body()!!.data[i].classId}".toInt(),
                                            startTime = "${response.body()!!.data[i].startTime}",
                                            endTime = "${response.body()!!.data[i].endTime}",
                                            color = "${response.body()!!.data[i].color}",
                                            times = "${response.body()!!.data[i].times}".toInt(),
                                            lectureName = "${response.body()!!.data[i].lectureName}",
                                            hour = "${response.body()!!.data[i].hour}".toInt(),
                                            location = "${response.body()!!.data[i].location}",
                                            classDate = "${response.body()!!.data[i].classDate}"
                                        )
                                    )
                                }
                                Log.i("test", "${response.body()!!.data[i].lectureName}")
                                Log.i("test", "${response.body()!!.data[i].color}")
                                calendarLogAdapter.notifyDataSetChanged()
                            } else {
                                continue
                            }
                        }
                        ApiSimulator(redday2, "red").executeOnExecutor(Executors.newSingleThreadExecutor())  // 빨간점 찍는날
                        ApiSimulator(yellowday2, "yellow").executeOnExecutor(Executors.newSingleThreadExecutor())  // 노란점 찍는날
                        ApiSimulator(greenday2, "green").executeOnExecutor(Executors.newSingleThreadExecutor())  // 초록점 찍는날
                        ApiSimulator(blueday2, "blue").executeOnExecutor(Executors.newSingleThreadExecutor())  // 파란점 찍는날
                        ApiSimulator(purpleday2, "purple").executeOnExecutor(Executors.newSingleThreadExecutor())  // 보라점 찍는날

                    }
                    if(datas.size == 0) {
                        cl_calendar_empty.visibility = View.VISIBLE
                        rv_calendarlog.visibility = View.GONE
                        haveCalendarData = false
                    }

                    else {
                        cl_calendar_empty.visibility = View.GONE
                        rv_calendarlog.visibility = View.VISIBLE
                        haveCalendarData = true
                    }
                    calendarLogAdapter = CalendarLogAdapter(getActivity()!!.getApplicationContext(), datas)
                    calendarLogAdapter.notifyDataSetChanged()
                    rv_calendarlog.adapter = calendarLogAdapter
                    dialogforcal.dismiss()

                } else {
                    Log.d("실패", "${response.message()}")
                    dialogforcal.dismiss()
                }
            }
        })
        Log.d("여기출력","해라")
    }

    private fun clickAlldata(){
        // 캘린더 날짜 클릭 변경
        materialCalendarView.setOnDateChangedListener { widget, date, selected ->
            val Year = date.year
            var Month = (date.month + 1).toString()
            var Day = (date.day).toString()
            var datas: MutableList<CalendarData> = mutableListOf<CalendarData>()
            calendarlog_all_date.text = "${Day}"
            calendarlog_all_month.text = "$Month" + "월"

            Log.d("클릭하면?","오나")
            dd = date

            // 날짜 포맷 통일
            if (Month.toInt() < 10) {
                Month = "0$Month"
            }
            if (Day.toInt() < 10) {
                Day = "0$Day"
            }

            val shot_Day = "$Year-$Month-$Day"
            Log.i("shot_Day test", shot_Day + "")

            select = true   //날짜 선택 되어 있다는 것 알림

            // 서버 연결
            calendarLogAdapter= CalendarLogAdapter(view!!.context, datas)

            val calendarlogRequestToServer = CalendarLogRequestToServer
            // 서버 요청
            calendarlogRequestToServer.service.calendarlogRequest(
                "${myjwt}"
            ).enqueue(object : Callback<CalendarLogResponseData> {
                override fun onFailure(call: Call<CalendarLogResponseData>, t: Throwable) {
                    Log.d("통신 실패", "${t}")
                }

                override fun onResponse(
                    call: Call<CalendarLogResponseData>,
                    response: Response<CalendarLogResponseData>
                ) {
                    // 통신 성공
                    if (response.isSuccessful) {   // statusCode가 200-300 사이일 때, 응답 body 이용 가능
                        if (response.body()!!.success) {  // 참고 코드에서 없는 부분

                            Log.d("받아온 데이터 ", response.body()!!.data.toString())

                            var i: Int = 0
                            for (i in 0 until response.body()!!.data.size) {
                                Log.d("인덱스", "${i}")

                                if (shot_Day == response.body()!!.data[i].classDate) {
                                    Log.d("test", "동일")
//                                    Log.d("test", "${response.body()!!.data[i].classDate}")
                                    datas.apply {
                                        add(
                                            CalendarData(
                                                classId = "${response.body()!!.data[i].classId}".toInt(),
                                                startTime = "${response.body()!!.data[i].startTime}",
                                                endTime = "${response.body()!!.data[i].endTime}",
                                                color = "${response.body()!!.data[i].color}",
                                                times = "${response.body()!!.data[i].times}".toInt(),
                                                lectureName = "${response.body()!!.data[i].lectureName}",
                                                hour = "${response.body()!!.data[i].hour}".toInt(),
                                                location = "${response.body()!!.data[i].location}",
                                                classDate = "${response.body()!!.data[i].classDate}"
                                            )
                                        )
                                    }
                                    Log.i("test", "${response.body()!!.data[i].lectureName}")
                                    Log.i("SecondAlldata", "${response.body()!!.data[i].color}")
                                    calendarLogAdapter.notifyDataSetChanged()
                                } else {
                                    continue
                                }
                            }

                        }
                        if(datas.size == 0) {
                            cl_calendar_empty.visibility = View.VISIBLE
                            rv_calendarlog.visibility = View.GONE
                            haveCalendarData = false
                        }

                        else {
                            cl_calendar_empty.visibility = View.GONE
                            rv_calendarlog.visibility = View.VISIBLE
                            haveCalendarData = true
                        }
                        calendarLogAdapter = CalendarLogAdapter(getActivity()!!.getApplicationContext(), datas)
                        calendarLogAdapter.notifyDataSetChanged()
                        rv_calendarlog.adapter = calendarLogAdapter

                    } else {
                        Log.d("실패", "${response.message()}")

                    }
                }
            })
        }
    }


    private fun toggleData(){
        Log.d("아이디", "${lid}")
        toggleselect1 = true

        // 기존 점 지우기
        materialCalendarView.removeDecorators()

        // 일요일 이벤트 다시
        materialCalendarView.addDecorators(sundayDecorator)

        // 오늘날짜 이벤트 다시
        if (looking == true) {
            materialCalendarView.addDecorators(
                CurrentDayDecorator(activity, CalendarDay.from(2020, 11, 16))
            )
        } else {
            materialCalendarView.addDecorators(
                CurrentDayDecorator(activity, CalendarDay.today())
            )
        }
        // 점찍을 날짜
        Log.d("다시 돌아온색", "${lcolor}")
        when(lcolor){
            "red" -> ApiSimulator(redday2, "red").executeOnExecutor(Executors.newSingleThreadExecutor())  // 빨간점 찍는날
            "yellow" -> ApiSimulator(yellowday2, "yellow").executeOnExecutor(Executors.newSingleThreadExecutor())  // 노란점 찍는날
            "green" -> ApiSimulator(greenday2, "green").executeOnExecutor(Executors.newSingleThreadExecutor())  // 초록점 찍는날
            "blue" -> ApiSimulator(blueday2, "blue").executeOnExecutor(Executors.newSingleThreadExecutor())  // 파란점 찍는날
            "purple" -> ApiSimulator(purpleday2, "purple").executeOnExecutor(Executors.newSingleThreadExecutor())  // 보라점 찍는날
        }
    }

}