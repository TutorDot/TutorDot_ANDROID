package com.tutor.tutordot.StartServer

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SignupRequestToServer {
    var retrofit = Retrofit.Builder()
        .baseUrl("http://15.165.186.228:3000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service: RequestSignupInterface = retrofit.create(RequestSignupInterface::class.java)
}