package com.tutor.tutordot.Calendar

import android.graphics.Color
import android.text.style.ForegroundColorSpan
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.tutor.tutordot.R
import java.util.*

class SundayDecorator: DayViewDecorator {
    private val calendar = Calendar.getInstance()

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        day?.copyTo(calendar)
        val weekDay = calendar.get(Calendar.DAY_OF_WEEK)
        return weekDay == Calendar.SUNDAY
    }

    // 일요일 빨간색으로 설정
    override fun decorate(view: DayViewFacade?) {
        view?.addSpan(object: ForegroundColorSpan(Color.parseColor("#d16565")){})
    }
}