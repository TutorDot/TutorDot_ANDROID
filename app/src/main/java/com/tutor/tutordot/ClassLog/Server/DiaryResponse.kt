package com.tutor.tutordot.ClassLog.Server

data class DiaryResponse (
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : List<DiarySomeData>
)

data class DiarySomeData(
    val diaryId : Int,
    val profileUrl : String,
    val lectureName : String,
    val classDate : String,
    val color : String,
    val times : Int,
    val hour : Int,
    val depositCycle : Int,
    val classProgress : String,
    val homework : String,
    val hwPerformance : Int
)