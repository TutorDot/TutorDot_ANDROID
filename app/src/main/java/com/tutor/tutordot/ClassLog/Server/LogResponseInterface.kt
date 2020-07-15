package com.tutor.tutordot.ClassLog.Server

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface LogResponseInterface{
    @GET("/diary")
    fun logRequest(
        @Body body: MutableList<LogSomeData>
    ) : Call<LogResponse>

    @PUT("/diary/hw/:did")
    fun logModiRequest(@Body body : LogModiRequest) : Call<LogModiResponse>
}