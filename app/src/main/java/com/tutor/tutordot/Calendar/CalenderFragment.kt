package com.tutor.tutordot.Calendar

import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.graphics.Insets.add
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.CalendarMode
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.tutor.tutordot.Calendar.CalendarLogRecyclerView.CalendarLogAdapter
import com.tutor.tutordot.Calendar.CalendarLogRecyclerView.CalendarLogData
import com.tutor.tutordot.Calendar.CalendarLogRecyclerView.haveCalendarData
import com.tutor.tutordot.Calendar.Server.*
import com.tutor.tutordot.CalenderActivity
import com.tutor.tutordot.ClassLog.Server.LectureResponse
import com.tutor.tutordot.ClassLog.Server.LogResponse
import com.tutor.tutordot.MainPagerAdapter
import com.tutor.tutordot.R
import com.tutor.tutordot.StartServer.RequestToServer
import com.tutor.tutordot.Startpage.AutoLogin.MySharedPreferences
import com.tutor.tutordot.Startpage.myjwt
import com.tutor.tutordot.Startpage.role
import kotlinx.android.synthetic.main.fragment_calender.*
import kotlinx.android.synthetic.main.item_calendarlog.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.Executors


class CalenderFragment : Fragment() {
    // 일정 추가 서버 연결
    val requestToServer = RequestToServer

    // 캘린더 일정 더미
    val datas: MutableList<CalendarLogData> = mutableListOf<CalendarLogData>()
    lateinit var calendarLogAdapter: CalendarLogAdapter

    //현재 달 구하기
    val curDate = Calendar.getInstance()

    var time: String? = null
    var kcal: kotlin.String? = null
    var menu: kotlin.String? = null
    private val oneDayDecorator = OneDayDecorator()
    val sundayDecorator = SundayDecorator()
    var cursor: Cursor? = null

    lateinit var materialCalendarView: MaterialCalendarView

    lateinit var leid1 : ArrayList<Int>
    lateinit var lename1 : ArrayList<String>
    var lecnt1 : Int = 0

    var select : Boolean = false
    lateinit var dd : CalendarDay


    inner class ApiSimulator internal constructor(var Time_Result: Array<String>, var vs: String) :
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
            Log.d("리턴값1", "${dates}")
            Log.d("리턴값2", "${dates2}")
            Log.d("리턴값3", "${dates3}")
            Log.d("리턴값4", "${dates4}")
            Log.d("리턴값5", "${dates5}")

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
            if (vs == "red"){
                materialCalendarView.addDecorator(
                    EventDecorator(
                        // 빨간점 찍기
                        Color.parseColor("#f28d8d"), calendarDays
//                    this@CalenderFragment
                    )
                )
            }
            if (vs == "yellow"){
                materialCalendarView.addDecorator(
                    EventDecorator(
                        // 노란점 찍기
                        Color.parseColor("#ffe966"), calendarDays
//                    this@CalenderFragment
                    )
                )
            }
            if (vs == "green"){
                materialCalendarView.addDecorator(
                    EventDecorator(
                        // 초록점 찍기
                        Color.parseColor("#90d672"), calendarDays
//                    this@CalenderFragment
                    )
                )
            }
            if (vs == "blue"){
                materialCalendarView.addDecorator(
                    EventDecorator(
                        // 파란점 찍기
                        Color.parseColor("#86d5e3"), calendarDays
//                    this@CalenderFragment
                    )
                )
            }
            if (vs == "purple"){
                materialCalendarView.addDecorator(
                    EventDecorator(
                        // 보라점 찍기
                        Color.parseColor("#b88de3"), calendarDays
//                    this@CalenderFragment
                    )
                )
            }

//            // 시도3(현재적용) -> 점 여러개 찍힘
//            materialCalendarView.addDecorator(
//                context?.let { EventDecorator(it, calendarDays) }
//            )

            // 연습용
//            val decoratorArray =
//                arrayOfNulls<EventDecorator>(3) //Max 3 dots
//
//            for (i in decoratorArray.indices) decoratorArray[i] =
//                EventDecorator(myColor, myRadius, i)
//
//            /* dayInstanceMap contains all the mappings. */
//
//            /* dayInstanceMap contains all the mappings. */
//            for ((currDay, currDayCount) in dayInstanceMap.entrySet()) {
//                for (i in 0 until currDayCount) decoratorArray[i].addDate(currDay)
//            }
//
//            // 일정 개수에 맞게 점 찍기
//            materialCalendarView.addDecorator(EventDecorator(oneEventDays, oneColors))
//            materialCalendarView.addDecorator(OneDayDecorator(twoEventDays, twoColors))
//            materialCalendarView.addDecorator(OneDayDecorator(threeEventDays, threeColors))
//            materialCalendarView.addDecorator(OneDayDecorator(fourEventDays, fourColors))
//            materialCalendarView.addDecorator(OneDayDecorator(fiveEventDays, fiveColors))
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calender, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        // 오늘날짜 보여주기
        calendarlog_all_date.text = curDate.get(Calendar.DATE).toString()
        calendarlog_all_month.text = (curDate.get(Calendar.MONTH) + 1).toString() + "월"

        Log.i("today date", "${calendarlog_all_date.text}")
        Log.i("today month", "${calendarlog_all_month.text}")

        // 오늘날짜 이벤트
        materialCalendarView.addDecorators(
            oneDayDecorator, CurrentDayDecorator(activity, CalendarDay.today())
        )

        // 일요일 이벤트
        materialCalendarView.addDecorators(sundayDecorator)


        // 점찍을 날짜
        val redresult =
            arrayOf("2020-11-01","2020-11-11","2020-11-20","2020-11-27","2020-11-28")
//        for (i in 0..4) {
//            val eventCount = 3
//            materialCalendarView.addAnEvent(arr.get(i), eventCount, getEventDataList(eventCount))
//        }
        val yellowresult =
            arrayOf("2020-11-03","2020-11-04")

        val greenresult =
            arrayOf("2020-11-03","2020-11-04")

        val blueresult =
            arrayOf("2020-11-13","2020-11-14")

        val purpleresult =
            arrayOf("2020-11-23","2020-11-24")

        ApiSimulator(redresult, "red").executeOnExecutor(Executors.newSingleThreadExecutor())  // 빨간점 찍는날
        ApiSimulator(yellowresult, "yellow").executeOnExecutor(Executors.newSingleThreadExecutor())  // 노란점 찍는날
        ApiSimulator(greenresult, "green").executeOnExecutor(Executors.newSingleThreadExecutor())  // 초록점 찍는날
        ApiSimulator(blueresult, "blue").executeOnExecutor(Executors.newSingleThreadExecutor())  // 파란점 찍는날
        ApiSimulator(purpleresult, "purple").executeOnExecutor(Executors.newSingleThreadExecutor())  // 보라점 찍는날


        // 튜티에겐 플로팅 버튼 보이지 않음
        if(role == "tutee"){
            floatingActionButton.visibility = View.GONE
        }

        //수업정보 받아옴 (토글 위해)
        val popup = PopupMenu(context, calendar_select)
        //Inflating the Popup using xml file
        popup.menuInflater
            .inflate(R.menu.popup_menu, popup.menu)

        val calendarlogRequestToServer = CalendarLogRequestToServer
        calendarlogRequestToServer.service.lectureRequest(
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
                        Log.d("토글 수업 정보1", "성공")

                        lecnt1 = response.body()!!.data.size
                        lename1 = ArrayList()
                        leid1 = ArrayList()

                        for(i in 1..lecnt1) {
                            lename1.add(response.body()!!.data[i - 1].lectureName)
                            leid1.add(response.body()!!.data[i-1].lectureId)
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
                        calAlldata()
                        clickAlldata()
//                        if(select){
//                            val Year = dd.year
//                            var Month = (dd.month + 1).toString()
//                            var Day = (dd.day).toString()
//                            var datas: MutableList<CalendarData> = mutableListOf<CalendarData>()
//                            calendarlog_all_date.text = "${Day}"
//                            calendarlog_all_month.text = "${Month}" + "월"
//
//
//                            // 날짜 포맷 통일
//                            if (Month.toInt() < 10) {
//                                Month = "0$Month"
//                            }
//                            if (Day.toInt() < 10) {
//                                Day = "0$Day"
//                            }
//
//                            val shot_Day = "$Year-$Month-$Day"
//                            Log.i("shot_Day test", shot_Day + "")
//
//                            select = true   //날짜 선택 되어 있다는 것 알림
//
//                            // 서버 연결
//                            calendarLogAdapter= CalendarLogAdapter(view!!.context, datas)
//
//                            val calendarlogRequestToServer = CalendarLogRequestToServer
//                            // 서버 요청
//                            calendarlogRequestToServer.service.calendarlogRequest(
//                                "${myjwt}"
//                            ).enqueue(object : Callback<CalendarLogResponseData> {
//                                override fun onFailure(call: Call<CalendarLogResponseData>, t: Throwable) {
//                                    Log.d("통신 실패", "${t}")
//                                }
//
//                                override fun onResponse(
//                                    call: Call<CalendarLogResponseData>,
//                                    response: Response<CalendarLogResponseData>
//                                ) {
//                                    // 통신 성공
//                                    if (response.isSuccessful) {   // statusCode가 200-300 사이일 때, 응답 body 이용 가능
//                                        if (response.body()!!.success) {  // 참고 코드에서 없는 부분
//
//                                            Log.d("받아온 데이터 ", response.body()!!.data.toString())
//
//                                            var i: Int = 0
//                                            for (i in 0 until response.body()!!.data.size) {
//                                                Log.d("인덱스", "${i}")
//
//                                                if (shot_Day == response.body()!!.data[i].classDate) {
//                                                    Log.d("test", "동일")
////                                                    Log.d("test", "${response.body()!!.data[i].classDate}")
//                                                    datas.apply {
//                                                        add(
//                                                            CalendarData(
//                                                                classId = "${response.body()!!.data[i].classId}".toInt(),
//                                                                startTime = "${response.body()!!.data[i].startTime}",
//                                                                endTime = "${response.body()!!.data[i].endTime}",
//                                                                color = "${response.body()!!.data[i].color}",
//                                                                times = "${response.body()!!.data[i].times}".toInt(),
//                                                                lectureName = "${response.body()!!.data[i].lectureName}",
//                                                                hour = "${response.body()!!.data[i].hour}".toInt(),
//                                                                location = "${response.body()!!.data[i].location}",
//                                                                classDate = "${response.body()!!.data[i].classDate}"
//                                                            )
//                                                        )
//                                                    }
//                                                    Log.i("test", "${response.body()!!.data[i].lectureName}")
//                                                    Log.i("test", "${response.body()!!.data[i].color}")
//                                                    calendarLogAdapter.notifyDataSetChanged()
//                                                } else {
//                                                    continue
//                                                }
//                                            }
//
//                                        }
//                                        if(datas.size == 0) {
//                                            cl_calendar_empty.visibility = View.VISIBLE
//                                            rv_calendarlog.visibility = View.GONE
//                                            haveCalendarData = false
//                                        }
//
//                                        else {
//                                            cl_calendar_empty.visibility = View.GONE
//                                            rv_calendarlog.visibility = View.VISIBLE
//                                            haveCalendarData = true
//                                        }
//                                        calendarLogAdapter = CalendarLogAdapter(getActivity()!!.getApplicationContext(), datas)
//                                        calendarLogAdapter.notifyDataSetChanged()
//                                        rv_calendarlog.adapter = calendarLogAdapter
//
//                                    } else {
//                                        Log.d("실패", "${response.message()}")
//
//                                    }
//                                }
//                            })
//                            // 여기 있으면 그 날짜 클릭하고 바로 회색 표시가 사라짐
//                            //materialCalendarView.clearSelection()
//                        }
                    }
                    // 특정 수업 서버 연결
                    else {
                        var lid : Int = 0
                        for(i in 1..lecnt1) {
                            if(item.title.equals(lename1[i-1]))
                                lid = leid1[i-1]
                        }
                        Log.d("아이디", "${lid}")

                        //오늘기준 수업표시
                        // 서버 연결
                        val datas: MutableList<CalendarData> = mutableListOf<CalendarData>()
                        calendarLogAdapter= CalendarLogAdapter(view!!.context, datas)

                        //val calendarlogRequestToServer = CalendarLogRequestToServer
                        val mydate=LocalDate.now()
                        val formatter = DateTimeFormatter.ISO_DATE
                        val formatted = mydate.format(formatter)

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

                                        // 데이터 전체 한번에 받아와서 날짜 같으면 그 날짜 데이터 추가
                                        var i: Int = 0
                                        for (i in 0 until response.body()!!.data.size) {

                                            if (formatted.toString() == response.body()!!.data[i].classDate) {
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

//                        if(select){
//                            val Year = dd.year
//                            var Month = (dd.month + 1).toString()
//                            var Day = (dd.day).toString()
//                            var datas: MutableList<CalendarData> = mutableListOf<CalendarData>()
//                            calendarlog_all_date.text = "${Day}"
//                            calendarlog_all_month.text = "$Month" + "월"
//
//
//                            // 날짜 포맷 통일
//                            if (Month.toInt() < 10) {
//                                Month = "0$Month"
//                            }
//                            if (Day.toInt() < 10) {
//                                Day = "0$Day"
//                            }
//
//                            val shot_Day = "$Year-$Month-$Day"
//                            Log.i("shot_Day test", shot_Day + "")
//
//                            // 서버 연결
//                            calendarLogAdapter= CalendarLogAdapter(view!!.context, datas)
//
//                            val calendarlogRequestToServer = CalendarLogRequestToServer
//                            // 서버 요청
//                            calendarlogRequestToServer.service.calRequest(
//                                "${myjwt}", "${lid}"
//                            ).enqueue(object : Callback<CalResponse> {
//                                override fun onFailure(call: Call<CalResponse>, t: Throwable) {
//                                    Log.d("1 특정 통신 실패", "${t}")
//                                }
//
//                                override fun onResponse(
//                                    call: Call<CalResponse>,
//                                    response: Response<CalResponse>
//                                ) {
//                                    // 통신 성공
//                                    if (response.isSuccessful) {   // statusCode가 200-300 사이일 때, 응답 body 이용 가능
//                                        if (response.body()!!.success) {  // 참고 코드에서 없는 부분
//
//                                            Log.d("받아온 데이터 ", response.body()!!.data.toString())
//
//                                            var i: Int = 0
//                                            for (i in 0 until response.body()!!.data.size) {
//                                                Log.d("인덱스", "${i}")
//
//                                                if (shot_Day == response.body()!!.data[i].classDate) {
//                                                    Log.d("test", "동일")
//                                                    Log.d("test", "${response.body()!!.data[i].classDate}")
//                                                    datas.apply {
//                                                        add(
//                                                            CalendarData(
//                                                                classId = "${response.body()!!.data[i].classId}".toInt(),
//                                                                startTime = "${response.body()!!.data[i].startTime}",
//                                                                endTime = "${response.body()!!.data[i].endTime}",
//                                                                color = "${response.body()!!.data[i].color}",
//                                                                times = "${response.body()!!.data[i].times}".toInt(),
//                                                                lectureName = "${response.body()!!.data[i].lectureName}",
//                                                                hour = "${response.body()!!.data[i].hour}".toInt(),
//                                                                location = "${response.body()!!.data[i].location}",
//                                                                classDate = "${response.body()!!.data[i].classDate}"
//                                                            )
//                                                        )
//                                                    }
//                                                    Log.i("test", "${response.body()!!.data[i].lectureName}")
//                                                    Log.i("test", "${response.body()!!.data[i].color}")
//                                                    calendarLogAdapter.notifyDataSetChanged()
//                                                } else {
//                                                    continue
//                                                }
//                                            }
//
//                                        }
//                                        if(datas.size == 0) {
//                                            cl_calendar_empty.visibility = View.VISIBLE
//                                            rv_calendarlog.visibility = View.GONE
//                                            haveCalendarData = false
//                                        }
//
//                                        else {
//                                            cl_calendar_empty.visibility = View.GONE
//                                            rv_calendarlog.visibility = View.VISIBLE
//                                            haveCalendarData = true
//                                        }
//                                        calendarLogAdapter = CalendarLogAdapter(getActivity()!!.getApplicationContext(), datas)
//                                        calendarLogAdapter.notifyDataSetChanged()
//                                        rv_calendarlog.adapter = calendarLogAdapter
//
//                                    } else {
//                                        Log.d("1 특정 실패", "${response.message()}")
//
//                                    }
//                                }
//                            })
//                            // 여기 있으면 그 날짜 클릭하고 바로 회색 표시가 사라짐
//                            //materialCalendarView.clearSelection()
//                        }

                    }
                    true
                }
                popup.show() //showing popup menu
            }
        })


        // 플로팅 버튼 누르면 일정 추가
        floatingActionButton.setOnClickListener {
            val intent = Intent(activity, ScheduleAddActivity::class.java)
            startActivity(intent)
        }

        Log.d("처음시작","전체출력")
        calAlldata()
        Log.d("처음시작","클릭출력")
        clickAlldata()

    }

    private fun calAlldata(){
        //오늘기준 수업표시
        // 서버 연결
        val datas: MutableList<CalendarData> = mutableListOf<CalendarData>()
        calendarLogAdapter= CalendarLogAdapter(view!!.context, datas)
        Log.d("여기출력","해라")


        //val calendarlogRequestToServer = CalendarLogRequestToServer
        val mydate=LocalDate.now()
        val formatter = DateTimeFormatter.ISO_DATE
        val formatted = mydate.format(formatter)
        var redday:Array<String> = arrayOf()

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

                        // 데이터 전체 한번에 받아와서 날짜 같으면 그 날짜 데이터 추가(오늘날짜)
                        var i: Int = 0
                        var c: Int = 0
                        for (i in 0 until response.body()!!.data.size) {
                            // 점 찍을 날짜 배열에 추가
                            if (response.body()!!.data[i].color == "purple"){
//                                redday[c] = response.body()!!.data[i].classDate
//                                c += 1
//                                Log.d("보라날", redday[c])
                            }

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
        Log.d("여기출력","해라2")
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
            // 여기 있으면 그 날짜 클릭하고 바로 회색 표시가 사라짐
            //materialCalendarView.clearSelection()
        }
    }
}