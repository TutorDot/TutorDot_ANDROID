package com.tutor.tutordot.Calendar

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.fragment.app.Fragment
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan
import com.tutor.tutordot.R


///**
// * Decorate several days with a dot
// */
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
//        view.addSpan(DotSpan(5F, color)) // 날자밑에 점
//    }
//
//    init {
//        drawable = context.resources.getDrawable(R.drawable.calender_blank_img_topyellow)
//        this.color = color
//        this.dates = HashSet(dates)
//    }
//}


//class EventDecorator(private val color: Int, dates: Collection<CalendarDay>) :
//    DayViewDecorator {
//    private val dates: HashSet<CalendarDay>
//    override fun shouldDecorate(day: CalendarDay): Boolean {
//        return dates.contains(day)
//    }
//
//    override fun decorate(view: DayViewFacade) {
//        view.addSpan(DotSpan(6F, color))
//    }
//
//    init {
//        this.dates = HashSet(dates)
//    }
//}


//class EventDecorator (context: Context, stringProductColor : Array<String>, dates : Collection<CalendarDay>) : DayViewDecorator{
//
//    private lateinit var colors: IntArray
//        private val dates: HashSet<CalendarDay>
//
//        init {
//        this.dates = HashSet(dates)
//    }
//
//    override fun shouldDecorate(day: CalendarDay): Boolean {
//        return dates.contains(day)
//    }
//
//    override fun decorate(view: DayViewFacade){
//        colors = IntArray(stringProductColor.size)
//        for(i in stringProductColor.indices){
//            colors[i] = Color.parseColor(stringProductColor[i])
//        }
//        view.addSpan(CustomMultipleDotSpan(5f, colors))
//    }
//}