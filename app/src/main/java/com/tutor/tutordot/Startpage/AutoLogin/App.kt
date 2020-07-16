package com.tutor.tutordot.Startpage.AutoLogin

import android.app.*

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        MySharedPreferences.init(this)
    }
    
}