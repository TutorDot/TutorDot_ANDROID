package com.tutor.tutordot.Calendar

import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan


///**
// * Decorate several days with a dot
// */
//class EventDecorator(
//    color: Int,
//    dates: Collection<CalendarDay?>?,
//    context: Activity
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
//        view.addSpan(new DotSpan(5, color)) // 날자밑에 점
//    }
//
//    init {
//        drawable = context.resources.getDrawable(R.drawable.more)
//        this.color = color
//        this.dates = HashSet(dates)
//    }
//}


//class EventDecorator(private val color: Int, dates: Collection<CalendarDay?>?) :
//    DayViewDecorator {
//    private val dates: HashSet<CalendarDay>
//    override fun shouldDecorate(day: CalendarDay): Boolean {
//        return dates.contains(day)
//    }
//
//    override fun decorate(view: DayViewFacade) {
//        view.addSpan(DotSpan(5F, color))
//    }
//
//    init {
//        this.dates = HashSet(dates)
//    }
//}


//class EventDecorator (private val context Context,
//                      private val stringProductColor Array<String>, private val dates CalendarDay) : DayViewDecorator{
//
//    private lateinit var colors: IntArray
//
//    override fun shouldDecorate(day: CalendarDay): Boolean = dates ==day
//
//    override fun decorate(view: DayViewFacade){
//        colors = IntArray(stringProductColor2.size)
//        for(i in stringProductColor2.indices){
//            colors[i] = Color.parseColor(stringProductColor2[i])
//        }
//        view.addSpan(CustomMultipleDotSpan(5f, colors))
//    }
//}