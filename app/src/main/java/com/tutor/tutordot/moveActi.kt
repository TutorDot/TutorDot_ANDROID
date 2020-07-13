package com.tutor.tutordot

import android.content.Context
import android.content.Intent
import android.view.View

fun moveActi(intent: Intent, v: View){
    val context: Context = v!!.context
    context.startActivity(intent)
}