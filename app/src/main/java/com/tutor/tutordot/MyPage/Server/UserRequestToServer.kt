package com.tutor.tutordot.MyPage.Server

import com.tutor.tutordot.MyPage.Server.UserInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserRequestToServer {
    var retrofit = Retrofit.Builder()
        .baseUrl("http://15.165.186.228:3000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service: UserInterface = retrofit.create(UserInterface::class.java)
}