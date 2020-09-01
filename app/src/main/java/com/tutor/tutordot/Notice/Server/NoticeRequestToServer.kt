package com.tutor.tutordot.Notice.Server

import com.tutor.tutordot.ClassLog.Server.LogResponseInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NoticeRequestToServer {
    var retrofit = Retrofit.Builder()
        .baseUrl("http://15.165.186.228:3000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service: NoticeInterface = retrofit.create(NoticeInterface::class.java)
}