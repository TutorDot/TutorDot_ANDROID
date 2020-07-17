package com.tutor.tutordot.Calendar.Server

import com.tutor.tutordot.ClassLog.Server.LogModiRequest
import com.tutor.tutordot.ClassLog.Server.LogModiResponse
import retrofit2.Call
import retrofit2.http.*

interface CalendarLogResponseInterface{
    @Headers("jwt: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjMsIm5hbWUiOiJTZW95b3VuZyIsImlhdCI6MTU5NDgzMzkwMywiZXhwIjoxNTk2MDQzNTAzLCJpc3MiOiJvdXItc29wdCJ9.E4RNGNpeJENsAOZ5v8W_9tXpZjqKdXypbBZFYOSpZMI")
    @GET("/calander")
    fun calendarlogRequest() : Call<CalendarLogResponseData>

    @PUT("/diary/hw/2")
    fun scheduleEditRequest(@Body body : ScheduleEditRequest) : Call<ScheduleEditResponse>

    @Headers("jwt: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjIsIm5hbWUiOiJZb295b3VuZyIsImlhdCI6MTU5NDgwNTU5NywiZXhwIjoxNTk2MDE1MTk3LCJpc3MiOiJvdXItc29wdCJ9.MQyTLpYtQ52IkS6TjpVXeXzq_x7Bd1Iz7p34m8DbVXw")
    @PUT("/calander/class")
    fun scheduleAddRequest(@Body body : ScheduleAddRequest) : Call<ScheduleAddResponse>

//    fun calendarlogRequest(
//        @Header("jwt: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjMsIm5hbWUiOiJTZW95b3VuZyIsImlhdCI6MTU5NDgzMzkwMywiZXhwIjoxNTk2MDQzNTAzLCJpc3MiOiJvdXItc29wdCJ9.E4RNGNpeJENsAOZ5v8W_9tXpZjqKdXypbBZFYOSpZMI")  token : String,
//    ) : Call<CalendarLogResponseData>

//    @GET("/calander/1")
//    fun calendarlogRequest() : Call<CalendarLogResponseData>
}