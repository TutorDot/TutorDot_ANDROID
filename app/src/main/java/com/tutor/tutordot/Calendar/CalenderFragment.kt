
package com.tutor.tutordot.Calendar

import android.content.Intent
import android.database.Cursor
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
import com.tutor.tutordot.Calendar.Server.CalendarLogRequestToServer
import com.tutor.tutordot.Calendar.Server.CalendarLogResponseData
import com.tutor.tutordot.ClassLog.LogdateRecyclerView.haveData
import com.tutor.tutordot.ClassLog.Server.LogRequestToServer
import com.tutor.tutordot.ClassLog.Server.ProgressResponse
import com.tutor.tutordot.R
import kotlinx.android.synthetic.main.fragment_calender.*
import kotlinx.android.synthetic.main.fragment_calender.rv_calendarlog
import kotlinx.android.synthetic.main.fragment_class_log.*
import kotlinx.android.synthetic.main.item_calendarlog_all.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.concurrent.Executors
import kotlinx.android.synthetic.main.fragment_calender.calendarlog_all_date as calendarlog_all_date1
import kotlinx.android.synthetic.main.fragment_calender.calendarlog_all_month as calendarlog_all_month1


class CalenderFragment : Fragment() {

    //서버 연결
    //val calendarlogRequestToServer = CalendarLogRequestToServer

    lateinit var calendarLogAdapter: CalendarLogAdapter
    val datas: MutableList<CalendarLogData> = mutableListOf<CalendarLogData>()

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
        calendarlog_all_date.text = "15"
        calendarlog_all_month.text = "7" + "월"
        materialCalendarView.addDecorators(
            oneDayDecorator, CurrentDayDecorator(activity, CalendarDay.today())
        )


//        var i : Int = 0
//        while (i <= data.indices) {
//            if (shot_Day == data[i].classDate) {
//                datas.apply {
//                    add(
//                        CalendarLogData(
//                            starttime = "${data[i].startTime}",
//                            endtime = "${data[i].endTime}",
//                            img_color = "${data[i].color}",
//                            times = "${data[i].times}".toInt(),
//                            title = "${data[i].lectureName}",
//                            studytime = "${data[i].hour}".toInt(),
//                            location = "${data[i].location}"
//                        )
//                    )
//                }
//            }
//        }

        val result =
            arrayOf("2020,07,11", "2020,07,15", "2020,07,15", "2020,07,17", "2020,07,18")
        ApiSimulator(result).executeOnExecutor(Executors.newSingleThreadExecutor())
        materialCalendarView.setOnDateChangedListener { widget, date, selected ->
            val Year = date.year
            var Month = (date.month + 1).toString()
            var Day = (date.day).toString()
            calendarlog_all_date.text = "$Day"
            calendarlog_all_month.text = "$Month" + "월"
//            Log.i("Year test", Year.toString() + "")
//            Log.i("Month test", Month.toString() + "")
//            Log.i("Day test", Day.toString() + "")

            // 날짜 포맷 통일
            if (Month.toInt() < 10) {
                Month = "0$Month"
            }
            if (Day.toInt() < 10) {
                Day = "0$Day"
            }

            val shot_Day = "$Year-$Month-$Day"
            Log.i("shot_Day test", shot_Day + "")
            materialCalendarView.clearSelection()
            Toast.makeText(
                requireContext(),
                shot_Day,
                Toast.LENGTH_SHORT
            ).show()
        }


//        calendarLogAdapter = CalendarLogAdapter(view!!.context)
//        rv_calendarlog.adapter = calendarLogAdapter //리사이클러뷰의 어댑터를 지정해줌
        loadDatas() //데이터를 어댑터에 전달


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


//                    // 선택 시 서버에서 정보 받아옴
//                    calendarlogRequestToServer.service.calendarlogRequest(
//                    ).enqueue(object : Callback<CalendarLogResponseData> {
//                        override fun onFailure(call: Call<CalendarLogResponseData>, t: Throwable) {
//                            Log.d("통신 실패", "통신 실패")
//                        }
//
//                        override fun onResponse(
//                            call: Call<CalendarLogResponseData>,
//                            response: Response<CalendarLogResponseData>
//                        ) {
//                            if(response.isSuccessful){
//                                if(response.body()!!.success){
//                                    Log.d("성공", "성공")
//                                    Log.d(response.body()!!.data.toString(),response.body()!!.data.toString())
//                                    progressDate = response.body()!!.data[5].classDate
//                                    progressCycle = response.body()!!.data[5].depositCycle
//                                    progressTimes = response.body()!!.data[5].times
//                                    progressHour = response.body()!!.data[5].hour
//                                    tv_progress_times.setText(progressTimes.toString() + "회차 " + progressHour.toString() + "시간")
//                                    tv_progress_alltime.setText(progressCycle.toString() + "시간")
//                                    progressStatus = 100*progressHour/progressCycle
//                                    pb_class.progress = progressStatus
//                                    tv_percent.setText(progressStatus.toString() + "%")
//
//                                    //Log.d(progressCycle.toString(), progressCycle.toString())
//                                    //Log.d(progressTimes.toString(), progressTimes.toString())
//                                    //Log.d(progressHour.toString(), progressHour.toString())
//                                    //Log.d(progressStatus.toString(), progressStatus.toString())
//                                }else{
//                                    Log.d("실패", "실패")
//                                }
//                            }
//                        }
//
//                    })

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

        //데이터 없을 때 나오는 화면
        if (haveCalendarData == true) {
            cl_calendar_empty.visibility = View.GONE
            rv_calendarlog.visibility = View.VISIBLE
        } else {
            rv_calendarlog.visibility = View.GONE
            cl_calendar_empty.visibility = View.VISIBLE

        }


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        calendarLogAdapter = CalendarLogAdapter(view!!.context)
//        rv_calendarlog.adapter = calendarLogAdapter //리사이클러뷰의 어댑터를 지정해줌
//        loadDatas() //데이터를 어댑터에 전달
//
//
//        //상단 수업 선택 메뉴
//        constarintlayout.setOnClickListener(object :View.OnClickListener {
//            override fun onClick(v: View?) {
//                val popup =
//                    PopupMenu(context, calendar_select)
//                //Inflating the Popup using xml file
//                popup.menuInflater
//                    .inflate(R.menu.popup_menu, popup.menu)
//
//                //registering popup with OnMenuItemClickListener
//                popup.setOnMenuItemClickListener { item ->
//                    tv_calendar_title.text = item.title
//
//                    true
//                }
//                popup.show() //showing popup menu
//            }
//        })
////
////        /* 팝업 메뉴 아이템 추가할 때 사용할 코드
////        val menu = PopupMenu(context, view)
////
////        menu.menu.add("One")
////        menu.menu.add("Two")
////        menu.menu.add("Three")
////
////        menu.show()
////        */
//
//        // 플로팅 버튼 누르면 일정 추가
//        floatingActionButton.setOnClickListener {
//            val intent = Intent(activity, ScheduleAddActivity::class.java)
//            startActivity(intent)
//        }
//
//        //데이터 없을 때 나오는 화면
//        if (haveCalendarData == true) {
//            cl_calendar_empty.visibility = View.GONE
//            rv_calendarlog.visibility = View.VISIBLE
//        } else {
//            rv_calendarlog.visibility = View.GONE
//            cl_calendar_empty.visibility = View.VISIBLE
//
//        }
    }

    private fun loadDatas() {
        val calendarlogRequestToServer = CalendarLogRequestToServer   // 도서 싱글톤 가져옴
        // 서버 요청
        calendarlogRequestToServer.service.calendarlogRequest(
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
                        Log.d("성공", "성공")
                        Log.d(response.body()!!.data.toString(), response.body()!!.data.toString())
//                        progressDate = response.body()!!.data[5].classDate
//                        progressCycle = response.body()!!.data[5].depositCycle
//                        progressTimes = response.body()!!.data[5].times
//                        progressHour = response.body()!!.data[5].hour

//                        var i = 0
//                        while (i<10){
//                            var myHour[i] = response.body()!!.data[i].hour
//                        }

                        calendarLogAdapter = CalendarLogAdapter(getActivity()!!.getApplicationContext(), response!!.body()!!.data)
                        calendarLogAdapter.notifyDataSetChanged()
                        rv_calendarlog.adapter = calendarLogAdapter

                        //Log.d(progressCycle.toString(), progressCycle.toString())
                        //Log.d(progressTimes.toString(), progressTimes.toString())
                        //Log.d(progressHour.toString(), progressHour.toString())
                        //Log.d(progressStatus.toString(), progressStatus.toString())
                    } else {
                        Log.d("실패", "${response.body()}")
                    }
                }
            }

        })

//        datas.apply {
//            add(
//                CalendarLogData(
//                    starttime = "2:00PM",
//                    endtime = "4:00PM",
//                    img_color = "yellow",
//                    times = 1,
//                    title = "김회진 튜티 수학 수업",
//                    studytime = 1,
//                    location = "원당역 할리스"
//                )
//            )
//            add(
//                CalendarLogData(
//                    starttime = "1:00PM",
//                    endtime = "5:00PM",
//                    img_color = "green",
//                    times = 1,
//                    title = "신연상 튜티 수학 수업",
//                    studytime = 2,
//                    location = "강남구청역 스타벅스"
//                )
//            )
//            add(
//                CalendarLogData(
//                    starttime = "5:00PM",
//                    endtime = "6:00PM",
//                    img_color = "yellow",
//                    times = 2,
//                    title = "김회진 튜티 물리 수업",
//                    studytime = 5,
//                    location = "수유역 할리스"
//                )
//            )
//        }
//        calendarLogAdapter.datas = datas
//        calendarLogAdapter.notifyDataSetChanged()
//    }
    }
}


