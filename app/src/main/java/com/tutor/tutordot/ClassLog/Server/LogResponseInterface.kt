package com.tutor.tutordot.ClassLog.Server

import com.tutor.tutordot.StartServer.RequestSignup
import com.tutor.tutordot.StartServer.ResponseSignup
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header

interface LogResponseInterface{
    @GET("/diary")
    fun LogRequest() : Call<LogResponse>
}