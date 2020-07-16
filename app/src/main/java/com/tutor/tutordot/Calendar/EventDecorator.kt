package com.tutor.tutordot.Calendar

import android.content.Context
import android.graphics.Color
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade


/**
 * Decorate several days with a dot
 */
//class EventDecorator(
//    color: Int,
//    dates: Collection<CalendarDay>,
//    context: Fragment
//) :
//    DayViewDecorator {
//    private val drawable: Drawable
//    private val color: Int
//    private val dates: HashSet<CalendarDay>
//    override fun shouldDecorate(day: CalendarDay): Boolean {
//        return dates.contains(day)
//    }
//
//    override fun decorate(view: DayViewFacade) {
//        view.setSelectionDrawable(drawable)
//        view.addSpan(DotSpan(8F, color)) // 날자밑에 점
//    }
//
//    init {
//        drawable = context.resources.getDrawable(R.drawable.calender_img_today)
//        this.color = color
//        this.dates = HashSet(dates)
//    }
//}


//class EventDecorator(private val color: Int, dates: Collection<CalendarDay>) :
//    DayViewDecorator {
//    private val dates: HashSet<CalendarDay>
//
//    // 캘린더의 모든 날짜를 띄울 때 decoration이 필요한지 판단하여 출력
//    override fun shouldDecorate(day: CalendarDay): Boolean {
//        return dates.contains(day)
//    }
//
//    override fun decorate(view: DayViewFacade) {
//        view.addSpan(DotSpan(8F, color))
//    }
//
//    init {
//        this.dates = HashSet(dates)
//    }
//}


class EventDecorator (context: Context,  dates : Collection<CalendarDay>) : DayViewDecorator{

        private lateinit var colors: IntArray
        private var dates: HashSet<CalendarDay>

        init {
        this.dates = HashSet(dates)


    }



//    fun EventDecorator(filteredEvents: List<MainActivity.Filter>) {
//        val oneEventDays: MutableCollection<CalendarDay> = ArrayList()
//        val oneColors = IntArray(1)
//        val twoEventDays: MutableCollection<CalendarDay> = ArrayList()
//        val twoColors = IntArray(2)
//        val threeEventDays: MutableCollection<CalendarDay> = ArrayList()
//        val threeColors = IntArray(3)
//        val fourEventDays: MutableCollection<CalendarDay> = ArrayList()
//        val fourColors = IntArray(4)
//        val fiveEventDays: MutableCollection<CalendarDay> = ArrayList()
//        val fiveColors = IntArray(5)
//
//
//        for (i in 1 until filteredEvents.size) {
//            for (day in 0 until filteredEvents[i].calDayArr.size()) {
//                if (filteredEvents[i].filterName.equals("Term Times")) {
//                    boldTermDates.add(filteredEvents[i].calDayArr.get(day))
//                } else {
//                    allCalendarDayEvents.add(filteredEvents[i].calDayArr.get(day))
//                }
//            }
//            if (filteredEvents[i].calDayArr != null) {
//                for (calDay in filteredEvents[i].calDayArr) {
//                    if (!oneEventDays.contains(calDay)) {
//                        oneEventDays.add(calDay)
//                        if (oneColors[0] == 0) oneColors[0] = filteredEvents[i].color
//                    } else if (!twoEventDays.contains(calDay) && oneEventDays.contains(calDay)) {
//                        twoEventDays.add(calDay)
//                        System.arraycopy(oneColors, 0, twoColors, 0, oneColors.size)
//                        if (twoColors[1] == 0) twoColors[1] = filteredEvents[i].color
//                    } else if (!threeEventDays.contains(calDay) && oneEventDays.contains(calDay) && twoEventDays.contains(
//                            calDay
//                        )
//                    ) {
//                        threeEventDays.add(calDay)
//                        System.arraycopy(twoColors, 0, threeColors, 0, twoColors.size)
//                        if (threeColors[2] == 0) threeColors[2] =
//                            filteredEvents[i].color
//                    } else if (!fourEventDays.contains(calDay) && threeEventDays.contains(calDay) && twoEventDays.contains(
//                            calDay
//                        ) && oneEventDays.contains(calDay)
//                    ) {
//                        fourEventDays.add(calDay)
//                        System.arraycopy(threeColors, 0, fourColors, 0, threeColors.size)
//                        if (fourColors[3] == 0) fourColors[3] = filteredEvents[i].color
//                    } else if (!fiveEventDays.contains(calDay) && fourEventDays.contains(calDay) && threeEventDays.contains(
//                            calDay
//                        ) && twoEventDays.contains(calDay) && oneEventDays.contains(calDay)
//                    ) {
//                        fiveEventDays.add(calDay)
//                        System.arraycopy(fourColors, 0, fiveColors, 0, fourColors.size)
//                        if (fiveColors[4] == 0) fiveColors[4] = filteredEvents[i].color
//                    }
//                }
//            }
//        }
//
//        //this.color = color;
//        dates = HashSet(filteredEvents[0].calDayArr)
//        val colors = IntArray(1)
//        colors[0] = filteredEvents[0].color
//        this.colors = colors
//    }


//    /* I added this method so that I can add dates after object creation!*/
//    fun addDate(day: CalendarDay): Boolean {
//        return dates.add(day)
//    }

    override fun shouldDecorate(day: CalendarDay): Boolean {
        return dates.contains(day)
    }

    override fun decorate(view: DayViewFacade){
//        colors = IntArray(stringProductColor.size)
//        for(i in stringProductColor.indices){
//            colors[i] = Color.parseColor(stringProductColor[i])
//        }
        val eventcolors = intArrayOf(
            //R.color.yellow, R.color.green, R.color.red, R.color.blue, R.color.purple
             Color.parseColor("#ffe966"), Color.parseColor("#86d5e3"), Color.parseColor("#b88de3")
        )

        view.addSpan(CustomMultipleDotSpan(8f, eventcolors))
    }

}