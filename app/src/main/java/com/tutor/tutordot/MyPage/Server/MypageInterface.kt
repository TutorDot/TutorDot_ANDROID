package com.tutor.tutordot.MyPage.Server

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PUT

interface MypageInterface {
        @Headers("jwt: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjIsIm5hbWUiOiJZb295b3VuZyIsImlhdCI6MTU5NDgwNTU5NywiZXhwIjoxNTk2MDE1MTk3LCJpc3MiOiJvdXItc29wdCJ9.MQyTLpYtQ52IkS6TjpVXeXzq_x7Bd1Iz7p34m8DbVXw")
        @GET("/lecture")
        fun classListRequest() : Call<ClassListResponse>

        @Headers("jwt: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjIsIm5hbWUiOiJZb295b3VuZyIsImlhdCI6MTU5NDgwNTU5NywiZXhwIjoxNTk2MDE1MTk3LCJpc3MiOiJvdXItc29wdCJ9.MQyTLpYtQ52IkS6TjpVXeXzq_x7Bd1Iz7p34m8DbVXw")
        @PUT("/user/profile")
        fun profileEditRequest(@Body body : ProfileEditRequest) : Call<ProfileEditResponse>

        @Headers("jwt: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjIsIm5hbWUiOiJZb295b3VuZyIsImlhdCI6MTU5NDgwNTU5NywiZXhwIjoxNTk2MDE1MTk3LCJpc3MiOiJvdXItc29wdCJ9.MQyTLpYtQ52IkS6TjpVXeXzq_x7Bd1Iz7p34m8DbVXw")
        @PUT("/lecture")
        fun myAddRequest(@Body body : MyAddRequest) : Call<MyAddResponse>

//        @GET("/lecture")
//        fun progressRequest() : Call<ProgressResponse>
//
//        @PUT("/diary/hw/:did")
//        fun logModiRequest(@Body body : LogModiRequest) : Call<LogModiResponse>
}