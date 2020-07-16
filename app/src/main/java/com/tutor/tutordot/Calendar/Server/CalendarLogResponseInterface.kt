package com.tutor.tutordot.Calendar.Server

import retrofit2.Call
import retrofit2.http.*

interface CalendarLogResponseInterface{
    @Headers("jwt: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjMsIm5hbWUiOiJTZW95b3VuZyIsImlhdCI6MTU5NDgzMzkwMywiZXhwIjoxNTk2MDQzNTAzLCJpc3MiOiJvdXItc29wdCJ9.E4RNGNpeJENsAOZ5v8W_9tXpZjqKdXypbBZFYOSpZMI")

    @GET("/calander")
    fun calendarlogRequest() : Call<CalendarLogResponseData>

//    fun calendarlogRequest(
//        @Header("jwt: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjMsIm5hbWUiOiJTZW95b3VuZyIsImlhdCI6MTU5NDgzMzkwMywiZXhwIjoxNTk2MDQzNTAzLCJpc3MiOiJvdXItc29wdCJ9.E4RNGNpeJENsAOZ5v8W_9tXpZjqKdXypbBZFYOSpZMI")  token : String,
//    ) : Call<CalendarLogResponseData>

//    @GET("/calander/1")
//    fun calendarlogRequest() : Call<CalendarLogResponseData>
}