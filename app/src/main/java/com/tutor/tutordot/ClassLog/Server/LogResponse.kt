package com.tutor.tutordot.ClassLog.Server

data class LogResponse(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : SomeData?
)

data class SomeData(
    val diaryId : Int,
    val Classdate : String,
    val color : String,
    val times : Int,
    val hour : Int,
    val depositCycle : Int,
    val classProgress : String,
    val homework : String,
    val hwPerformance : Int
)