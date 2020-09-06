package com.tutor.tutordot.Startpage

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.tutor.tutordot.CalenderActivity
import com.tutor.tutordot.R
import com.tutor.tutordot.Startpage.AutoLogin.MySharedPreferences
import com.tutor.tutordot.extention.showToast

class SplashActivity : Activity() {

    val SPLASH_VIEW_TIME: Long = 4000 //4초간 스플래시 화면을 보여줌 (ms)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val splashGif: ImageView = findViewById(R.id.gif_image) as ImageView
        //val gifImage = GlideDrawableImageViewTarget(splashGif)
        Glide.with(this).load(R.raw.splash_anim).into(splashGif)


        Handler().postDelayed({ //delay를 위한 handler
            /*
            if (MySharedPreferences.islogin) {
                myjwt=MySharedPreferences.tmpjwt
                role=MySharedPreferences.tmprole
                Log.d("롤2","롤2${role}")
                showToast("자동로그인 되었습니다")
                // MySharedPreferences.islogin = false     // 실험용
                val intent = Intent(this, CalenderActivity::class.java)
                startActivity(intent)
            }
            else {
                startActivity(Intent(this, StartActivity::class.java))
            }

             */
            startActivity(Intent(this, StartActivity::class.java))
            finish()
        }, SPLASH_VIEW_TIME)


    }
}