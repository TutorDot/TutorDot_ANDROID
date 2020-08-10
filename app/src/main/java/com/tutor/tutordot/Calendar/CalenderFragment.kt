package com.tutor.tutordot.Calendar

import android.content.Intent
import android.database.Cursor
import android.graphics.Insets.add
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
import com.tutor.tutordot.Calendar.Server.CalendarData
import com.tutor.tutordot.Calendar.Server.CalendarLogRequestToServer
import com.tutor.tutordot.Calendar.Server.CalendarLogResponseData
import com.tutor.tutordot.Calendar.Server.ScheduleAddRequest
import com.tutor.tutordot.CalenderActivity
import com.tutor.tutordot.ClassLog.LogdateRecyclerView.haveData
import com.tutor.tutordot.ClassLog.Server.LogResponse
import com.tutor.tutordot.R
import com.tutor.tutordot.StartServer.RequestLogin
import com.tutor.tutordot.StartServer.RequestToServer
import com.tutor.tutordot.Startpage.AutoLogin.MySharedPreferences
import com.tutor.tutordot.Startpage.myjwt
import com.tutor.tutordot.extention.customEnqueue
import com.tutor.tutordot.extention.showToast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_calender.*
import kotlinx.android.synthetic.main.item_calendarlog_all.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.concurrent.Executors
import kotlinx.android.synthetic.main.fragment_calender.calendarlog_all_date as calendarlog_all_date1
import kotlinx.android.synthetic.main.item_calendarlog_all.calendarlog_all_month as calendarlog_all_month1

class CalenderFragment : Fragment() {

    // 일정 추가 서버 연결
    val requestToServer = RequestToServer

    //캘린더 일정 목록 서버 연결
    //val calendarlogRequestToServer = CalendarLogRequestToServer

    // 캘린더 일정 더미
     val datas: MutableList<CalendarLogData> = mutableListOf<CalendarLogData>()
    lateinit var calendarLogAdapter: CalendarLogAdapter

    //현재 달 구하기
    val curDate = Calendar.getInstance()
    val month = curDate.get(Calendar.MONTH) + 1

    var time: String? = null
    var kcal: kotlin.String? = null
    var menu: kotlin.String? = null
    private val oneDayDecorator = OneDayDecorator()
    var cursor: Cursor? = null

    lateinit var materialCalendarView: MaterialCalendarView


    inner class ApiSimulator internal constructor(var Time_Result: Array<String>) :
        AsyncTask<Void, Void, List<CalendarDay>>() {
        override fun doInBackground(vararg voids: Void): List<CalendarDay> {
            try {
                Thread.sleep(500)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            val calendar = Calendar.getInstance()
            val dates: ArrayList<CalendarDay> = ArrayList()

            /*특정날짜 달력에 점표시해주는곳*/
            /*월은 0이 1월 년,일은 그대로*/
            //string 문자열인 Time_Result 을 받아와서 ,를 기준으로 자르고 string을 int 로 변환
            for (i in Time_Result.indices) {
                //val day = CalendarDay.from(calendar)
                val time = Time_Result[i].split(",".toRegex()).toTypedArray()
                val year = time[0].toInt()
                val month = time[1].toInt()
                val dayy = time[2].toInt()
                //dates.add(day)
                //calendar[year, month - 1] = dayy

                // 날짜 마지막 안 찍히고 오늘 날짜에도 찍히는 것 해결한 부분
                calendar[year, month - 1] = dayy
                val day = CalendarDay.from(calendar)
                dates.add(day)
            }
            return dates
        }

        override fun onPostExecute(calendarDays: List<CalendarDay>) {
            super.onPostExecute(calendarDays)
//                if (isFinishing()) {
//                    return
//                }

//                materialCalendarView.addDecorator(
//                    EventDecorator(
//                        // 원하는 색으로 점 찍기
//                        Color.parseColor("#f28d8d"), calendarDays,
//                        this@CalenderFragment
//                    )
//                )

            materialCalendarView.addDecorator(
                context?.let { EventDecorator(it, calendarDays) }
            )

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

            // 일정 개수에 맞게 점 찍기
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
            .setMinimumDate(CalendarDay.from(2018, 0, 1)) // 달력의 시작
            .setMaximumDate(CalendarDay.from(2030, 11, 31)) // 달력의 끝
            .setCalendarDisplayMode(CalendarMode.MONTHS)
            .commit()
        // materialCalendarView.isDynamicHeightEnabled = true   // 월에 따라 캘린더 크기 동적변화
        calendarlog_all_date.text = "18"
        calendarlog_all_month.text = "7" + "월"
        materialCalendarView.addDecorators(
            oneDayDecorator, CurrentDayDecorator(activity, CalendarDay.today())
        )


        val result =
            arrayOf("2020,06,30", "2020,07,12", "2020,07,13", "2020,07,17", "2020,07,18", "2020,07,24", "2020,07,25", "2020,07,31", "2020,08,14")
//        for (i in 0..4) {
//            val eventCount = 3
//            materialCalendarView.addAnEvent(arr.get(i), eventCount, getEventDataList(eventCount))
//        }

        ApiSimulator(result).executeOnExecutor(Executors.newSingleThreadExecutor())


        //상단 수업 선택 메뉴
        constarintlayout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val popup =
                    PopupMenu(context, calendar_select)
                //Inflating the Popup using xml file
                popup.menuInflater
                    .inflate(R.menu.popup_menu, popup.menu)

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener { item ->
                    tv_calendar_title.text = item.title
                    true
                }
                popup.show() //showing popup menu
            }
        })
//
//        /* 팝업 메뉴 아이템 추가할 때 사용할 코드
//        val menu = PopupMenu(context, view)
//
//        menu.menu.add("One")
//        menu.menu.add("Two")
//        menu.menu.add("Three")
//
//        menu.show()
//        */

        // 플로팅 버튼 누르면 일정 추가
        floatingActionButton.setOnClickListener {
            val intent = Intent(activity, ScheduleAddActivity::class.java)
            startActivity(intent)
        }

//        //데이터 없을 때 나오는 화면
//        if (haveCalendarData == true) {
//            cl_calendar_empty.visibility = View.VISIBLE
//            rv_calendarlog.visibility = View.GONE
//        } else {
//            rv_calendarlog.visibility = View.GONE
//            cl_calendar_empty.visibility = View.VISIBLE
//        }


        // 캘린더 날짜 클릭 변경
        materialCalendarView.setOnDateChangedListener { widget, date, selected ->
            val Year = date.year
            var Month = (date.month + 1).toString()
            var Day = (date.day).toString()
            val datas: MutableList<CalendarData> = mutableListOf<CalendarData>()
            calendarlog_all_date.text = "${Day}"
            calendarlog_all_month.text = "$Month" + "월"
//            Log.i("Year test", Year.toString() + "")
//            Log.i("Month test", Month.toString() + "")
//            Log.i("Day test", Day.toString() + "")
//            Log.i("rvdate test", "${calendarlog_all_date.text}")
//            Log.i("rvmonth test", "${calendarlog_all_month.text}")


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
            calendarLogAdapter= CalendarLogAdapter(view.context, datas)

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


                            // 로그 띄우는거 앞부분 수정했더니 갑자기 된다...확인 결과 fatal 오류 로그 때문이었음
                            Log.d("받아온 데이터 ", response.body()!!.data.toString())


//                            val Year = date.year
//                            Log.d("연도: ", "${Year}")
//                            var Month = (date.month + 1).toString()
//                            Log.d("월: ", "${Month}")
//                            var Day = (date.day).toString()
//                            Log.d("일: ", "${Day}")
//
//                            calendarlog_all_date.text = "$Day"
//                            calendarlog_all_month.text = "$Month" + "월"
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
//                            Log.i("shot_Day short_Day", shot_Day + "")
//                            materialCalendarView.clearSelection()

//                            Toast.makeText(
//                                requireContext(),
//                                shot_Day,
//                                Toast.LENGTH_SHORT
//                            ).show()

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
                        Log.d("실패", "${response.message()}")

                        }
                }
            })

            // 이부분 수정함(통신 안에 있는 오늘 날짜 설정 주석 + 선택 날짜 초기화는 setonClickListener 안에서 -> 클릭은 되고 튕김)
            materialCalendarView.clearSelection()

        }
    }


//    private fun allloadDatas() {
//        val calendarlogRequestToServer = CalendarLogRequestToServer
//        // 서버 요청
//        calendarlogRequestToServer.service.calendarlogRequest(
//        ).enqueue(object : Callback<CalendarLogResponseData> {
//            override fun onFailure(call: Call<CalendarLogResponseData>, t: Throwable) {
//                Log.d("통신 실패", "${t}")
//            }
//
//            override fun onResponse(
//                call: Call<CalendarLogResponseData>,
//                response: Response<CalendarLogResponseData>
//            ) {
//                // 통신 성공
//                if (response.isSuccessful) {   // statusCode가 200-300 사이일 때, 응답 body 이용 가능
//                    if (response.body()!!.success) {  // 참고 코드에서 없는 부분
//                        Log.d("성공", "성공")
//                        Log.d(response.body()!!.data.toString(), response.body()!!.data.toString())
//
//                        calendarLogAdapter = CalendarLogAdapter(
//                            getActivity()!!.getApplicationContext(), response!!.body()!!.data
//                        )
//                        calendarLogAdapter.notifyDataSetChanged()
//                        rv_calendarlog.adapter = calendarLogAdapter
//
//                    } else {
//                        Log.d("실패", "${response.body()}")
//                    }
//                }
//            }
//
//        })
//    }
}
