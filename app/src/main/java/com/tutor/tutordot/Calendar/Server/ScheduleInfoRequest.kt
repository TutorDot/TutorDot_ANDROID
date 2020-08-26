package com.tutor.tutordot.Calendar.Server

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ScheduleInfoRequest {
    var retrofit = Retrofit.Builder()
        .baseUrl("http://3.22.173.242:3000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service: CalendarLogResponseInterface = retrofit.create(CalendarLogResponseInterface::class.java)
}