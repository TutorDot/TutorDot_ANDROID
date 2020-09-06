package com.tutor.tutordot.Calendar

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import androidx.core.content.ContextCompat
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.tutor.tutordot.R
import java.security.AccessController.getContext
import java.util.*


class OneDayDecorator : DayViewDecorator {
    private var date: CalendarDay?

    fun OneDayDecorator() {
        date = CalendarDay.today()
    }

    override fun shouldDecorate(day: CalendarDay): Boolean {
        return date != null && day == date
    }

    @SuppressLint("ResourceAsColor")
    override fun decorate(view: DayViewFacade) {
        view.addSpan(StyleSpan(Typeface.BOLD))
        view.addSpan(RelativeSizeSpan(1.0f))
        view.addSpan(ForegroundColorSpan(Color.BLACK))
//        mPaintBackgroundToday.setColor(ContextCompat.getColor(getContext(), R.color.blue))

        val eventcolors = intArrayOf(
            //R.color.blue, R.color.purple
            Color.parseColor("#6875dd"), Color.parseColor("#b88de3")
        )

//        view.addSpan(CurrentDayDecorator(8f, eventcolors))
    }

    /**
     * We're changing the internals, so make sure to call [MaterialCalendarView.invalidateDecorators]
     */
    fun setDate(date: Date?) {
        this.date = CalendarDay.from(date)
    }

    init {
        date = CalendarDay.today()
    }
}
