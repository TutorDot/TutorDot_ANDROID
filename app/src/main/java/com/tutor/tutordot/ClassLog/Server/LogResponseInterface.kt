package com.tutor.tutordot.ClassLog.Server

import retrofit2.Call
import retrofit2.http.*

interface LogResponseInterface{
    @GET("/diary")
    fun logRequest() : Call<LogResponse>

    @Headers("jwt: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjIsIm5hbWUiOiJZb295b3VuZyIsImlhdCI6MTU5NDgwNTU5NywiZXhwIjoxNTk2MDE1MTk3LCJpc3MiOiJvdXItc29wdCJ9.MQyTLpYtQ52IkS6TjpVXeXzq_x7Bd1Iz7p34m8DbVXw")
    @GET("/lecture")
    fun progressRequest() : Call<ProgressResponse>

    @PUT("/diary/hw/:did")
    fun logModiRequest(@Body body : LogModiRequest) : Call<LogModiResponse>
}