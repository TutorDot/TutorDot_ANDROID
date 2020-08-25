package com.tutor.tutordot.ClassLog.LogdateRecyclerView

data class LogdateData(
    val month : Int,
    val day : Int,
    val color : String,
    val times : Int,
    val studytime : Int,
    val alltime : Int,
    var progress : String,
    var homework : String,
    var complete : Int,
    var first : Boolean,
    val diaryId : Int
)