package com.tutor.tutordot.StartServer

import com.tutor.tutordot.StartServer.RequestLogin
import com.tutor.tutordot.StartServer.ResponseLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RequestInterface{
    @POST("/user/signin")
    fun requestLogin(@Body body : RequestLogin) : Call<ResponseLogin>


}