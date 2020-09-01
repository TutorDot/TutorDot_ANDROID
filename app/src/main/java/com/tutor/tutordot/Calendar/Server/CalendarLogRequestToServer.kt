package com.tutor.tutordot.Calendar.Server

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CalendarLogRequestToServer {
    var retrofit = Retrofit.Builder()
        .baseUrl("http://15.165.186.228:3000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service: CalendarLogResponseInterface = retrofit.create(CalendarLogResponseInterface::class.java)
}