package com.tutor.tutordot.Calendar

import android.R
import android.app.Activity
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import androidx.core.content.ContextCompat
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

class CurrentDayDecorator(context: Activity?, currentDay: CalendarDay) : DayViewDecorator {
//    private val drawable: Drawable?
    var myDay = currentDay


    override fun shouldDecorate(day: CalendarDay): Boolean {
        return day == myDay
    }

    override fun decorate(view: DayViewFacade) {
//        view.setSelectionDrawable(drawable!!)
        view.addSpan(StyleSpan(Typeface.BOLD))
        view.addSpan(object: ForegroundColorSpan(Color.parseColor("#6875dd")){})
    }

    init {
        // You can set background for Decorator via drawable here
//        drawable = ContextCompat.getDrawable(context!!, R.drawable.editbox_background)

        // 오늘 날짜 원이 들어가긴 하는데 크기 조절 필요
//        drawable = context?.resources?.getDrawable(com.tutor.tutordot.R.drawable.calender_img_today)

    }
}