package com.tutor.tutordot.ClassLog.Server

data class ProgressResponse2(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : List<ProgressSomeData2>
)

data class ProgressSomeData2(
    val times : Int,
    val hour : Int,
    val depositCycle : Int,
    val classDate : String
)