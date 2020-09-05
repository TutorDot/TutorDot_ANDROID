package com.tutor.tutordot.MyPage.Server

import com.tutor.tutordot.MyPage.Server.MypageInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MyPageRequestToServer {
    var retrofit = Retrofit.Builder()
        .baseUrl("http://15.165.186.228:3000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service: MypageInterface = retrofit.create(MypageInterface::class.java)
}