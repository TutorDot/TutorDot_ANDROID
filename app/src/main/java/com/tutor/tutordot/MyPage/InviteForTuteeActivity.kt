package com.tutor.tutordot.MyPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tutor.tutordot.R
import kotlinx.android.synthetic.main.activity_invite_for_tutee.*

class InviteForTuteeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invite_for_tutee)


        schedule_class_math_btn_back.setOnClickListener{
            finish()
        }

    }
}