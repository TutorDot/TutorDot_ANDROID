package com.tutor.tutordot.Startpage

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.tutor.tutordot.R

class SplashActivity : Activity() {

    val SPLASH_VIEW_TIME: Long = 4000 //2초간 스플래시 화면을 보여줌 (ms)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val splashGif: ImageView = findViewById(R.id.gif_image) as ImageView
        //val gifImage = GlideDrawableImageViewTarget(splashGif)
        Glide.with(this).load(R.raw.splash_anim).into(splashGif)

        Handler().postDelayed({ //delay를 위한 handler
            startActivity(Intent(this, StartActivity::class.java))
            finish()
        }, SPLASH_VIEW_TIME)


    }
}