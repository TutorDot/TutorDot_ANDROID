package com.tutor.tutordot.Notice.NoticeRecyclerView

data class NoticeData(
    val month : Int,
    val day : Int,
    val color_class: String,
    val notice_type: Int,
    val notice_msg: String,
    var first2: Boolean
)