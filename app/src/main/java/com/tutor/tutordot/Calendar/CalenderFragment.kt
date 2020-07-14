
package com.tutor.tutordot.Calendar

import android.content.Intent
import android.database.Cursor
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
import com.tutor.tutordot.Calendar.CalendarLogAllRecyclerView.CalendarLogAllAdapter
import com.tutor.tutordot.Calendar.CalendarLogAllRecyclerView.CalendarLogAllData
import com.tutor.tutordot.R
import kotlinx.android.synthetic.main.fragment_calender.*
import java.util.*
import java.util.concurrent.Executors


class CalenderFragment : Fragment() {
    lateinit var calendarLogAllAdapter: CalendarLogAllAdapter
    val dateDatas: MutableList<CalendarLogAllData> = mutableListOf<CalendarLogAllData>()

    //현재 달 구하기
    val curDate = Calendar.getInstance()
    val month = curDate.get(Calendar.MONTH) + 1

    var time: String? = null
    var kcal:kotlin.String? = null
    var menu:kotlin.String? = null
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
                val day = CalendarDay.from(calendar)
                val time =
                    Time_Result[i].split(",".toRegex()).toTypedArray()
                val year = time[0].toInt()
                val month = time[1].toInt()
                val dayy = time[2].toInt()
                dates.add(day)
                calendar[year, month - 1] = dayy
            }
            return dates
        }

        override fun onPostExecute(calendarDays: List<CalendarDay>) {
                super.onPostExecute(calendarDays)
//                if (isFinishing()) {
//                    return
//                }
                materialCalendarView.addDecorator(
                    EventDecorator(
                        R.color.yellow,
                        calendarDays
                        //this@CalenderFragment
                    )
                )
        }


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calender,null)
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
        materialCalendarView.addDecorators(
//            oneDayDecorator, CurrentDayDecorator(activity, CalendarDay.today()), CustomMultipleDotSpan()
            oneDayDecorator, CurrentDayDecorator(activity, CalendarDay.today())

        )

        val result =
            arrayOf("2020,07,11", "2020,07,11", "2020,07,14", "2020,07,18")
        ApiSimulator(result).executeOnExecutor(Executors.newSingleThreadExecutor())
        materialCalendarView.setOnDateChangedListener { widget, date, selected ->
            val Year = date.year
            val Month = date.month + 1
            val Day = date.day
            Log.i("Year test", Year.toString() + "")
            Log.i("Month test", Month.toString() + "")
            Log.i("Day test", Day.toString() + "")
            val shot_Day = "$Year,$Month,$Day"
            Log.i("shot_Day test", shot_Day + "")
            materialCalendarView.clearSelection()
            Toast.makeText(
                requireContext(),
                shot_Day,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        calendarLogAllAdapter = CalendarLogAllAdapter(view!!.context)
        rv_calendarlog_all.adapter = calendarLogAllAdapter //리사이클러뷰의 어댑터를 지정해줌
        loaddateDatas() //데이터를 어댑터에 전달


        //상단 수업 선택 메뉴
        constarintlayout.setOnClickListener(object :View.OnClickListener {
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
    }

    private fun loaddateDatas() {
        dateDatas.apply {
            add(
                CalendarLogAllData(
                    month = 7,
                    day = 13
                )
            )
            add(
                CalendarLogAllData(
                    month = 7,
                    day = 14
                )
            )
            add(
                CalendarLogAllData(
                    month = 7,
                    day = 15
                )
            )
        }
        calendarLogAllAdapter.datas = dateDatas
        calendarLogAllAdapter.notifyDataSetChanged()
    }
}



