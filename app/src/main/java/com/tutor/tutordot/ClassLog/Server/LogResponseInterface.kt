package com.tutor.tutordot.ClassLog.Server

import retrofit2.Call
import retrofit2.http.*

interface LogResponseInterface{

    @Headers("jwt: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjMsIm5hbWUiOiJTZW95b3VuZyIsImlhdCI6MTU5NDgzMzkwMywiZXhwIjoxNTk2MDQzNTAzLCJpc3MiOiJvdXItc29wdCJ9.E4RNGNpeJENsAOZ5v8W_9tXpZjqKdXypbBZFYOSpZMI")
    @GET("/diary")
    fun logRequest() : Call<LogResponse>

    @Headers("jwt: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjMsIm5hbWUiOiJTZW95b3VuZyIsImlhdCI6MTU5NDgzMzkwMywiZXhwIjoxNTk2MDQzNTAzLCJpc3MiOiJvdXItc29wdCJ9.E4RNGNpeJENsAOZ5v8W_9tXpZjqKdXypbBZFYOSpZMI")
    @GET("/diary/bar/1")
    fun progressRequest() : Call<ProgressResponse>

    @Headers("jwt: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjIsIm5hbWUiOiJZb295b3VuZyIsImlhdCI6MTU5NDgwNTU5NywiZXhwIjoxNTk2MDE1MTk3LCJpc3MiOiJvdXItc29wdCJ9.MQyTLpYtQ52IkS6TjpVXeXzq_x7Bd1Iz7p34m8DbVXw")
    @GET("/diary/bar/2")
    fun progressRequest2() : Call<ProgressResponse2>

    @Headers("jwt: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjMsIm5hbWUiOiJTZW95b3VuZyIsImlhdCI6MTU5NDgzMzkwMywiZXhwIjoxNTk2MDQzNTAzLCJpc3MiOiJvdXItc29wdCJ9.E4RNGNpeJENsAOZ5v8W_9tXpZjqKdXypbBZFYOSpZMI")
    @PUT("/diary/hw/2")
    fun logModiRequest(@Body body : LogModiRequest) : Call<LogModiResponse>
}