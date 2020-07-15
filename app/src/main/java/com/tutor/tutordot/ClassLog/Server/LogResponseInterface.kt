package com.tutor.tutordot.ClassLog.Server

import retrofit2.Call
import retrofit2.http.*

interface LogResponseInterface{
    @GET("/diary")
    fun logRequest() : Call<LogResponse>

    @Headers("jwt: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjMsIm5hbWUiOiJTZW95b3VuZyIsImlhdCI6MTU5NDgzMzkwMywiZXhwIjoxNTk2MDQzNTAzLCJpc3MiOiJvdXItc29wdCJ9.E4RNGNpeJENsAOZ5v8W_9tXpZjqKdXypbBZFYOSpZMI")
    @GET("/lecture")
    fun progressRequest() : Call<ProgressResponse>

    @PUT("/diary/hw/:did")
    fun logModiRequest(@Body body : LogModiRequest) : Call<LogModiResponse>
}