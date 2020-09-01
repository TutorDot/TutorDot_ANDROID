package com.tutor.tutordot.ClassLog.Server

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LogRequestToServer {
    var retrofit = Retrofit.Builder()
        //.baseUrl("http://3.22.173.242:3000")
        .baseUrl("http://15.165.186.228:3000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service: LogResponseInterface = retrofit.create(LogResponseInterface::class.java)
}