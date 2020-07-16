package com.tutor.tutordot.Startpage.AutoLogin

import android.content.Context
import android.content.SharedPreferences

object MySharedPreferences{
    private const val NAME = "tutor.tutordot"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    // SharedPreferences variables
    private val IS_LOGIN = Pair("is_login", false)
    private val EMAIL = Pair("email", "")
    private val PASSWORD = Pair("password", "")

    fun init(context: Context){
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    // an inline function to put variable and save it
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    // 값 가져오기 / 변경하기. 걍 MySharedPreferences.email 이런 식으로 값 가져오고 바로 변경 가능
    var islogin: Boolean
        get() = preferences.getBoolean(IS_LOGIN.first, IS_LOGIN.second)
        set(value) = preferences.edit {
            it.putBoolean(IS_LOGIN.first, value)
        }

    var email: String
        get() = preferences.getString(EMAIL.first, EMAIL.second) ?: ""
        set(value) = preferences.edit {
            it.putString(EMAIL.first, value)
        }

    var password: String
        get() = preferences.getString(PASSWORD.first, PASSWORD.second) ?: ""
        set(value) = preferences.edit {
            it.putString(PASSWORD.first, value)
        }

}