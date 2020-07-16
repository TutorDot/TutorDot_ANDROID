package com.tutor.tutordot.MyPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.tutor.tutordot.MyPage.Server.MyInfoResponse
import com.tutor.tutordot.MyPage.Server.MyPageRequestToServer
import com.tutor.tutordot.MyPage.Server.ProfileEditRequest
import com.tutor.tutordot.MyPage.Server.ProfileEditResponse
import com.tutor.tutordot.R
import com.tutor.tutordot.Startpage.role
import kotlinx.android.synthetic.main.activity_myinfo.*
import kotlinx.android.synthetic.main.activity_onesentense.*
import kotlinx.android.synthetic.main.fragment_my.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OnesentenseActivity : AppCompatActivity() {
    val mypageRequestToServer = MyPageRequestToServer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onesentense)

        btn_cancel_my_one.setOnClickListener{
            finish()
        }
        btn_save_my_one.setOnClickListener{
            finish()
        }

        Glide.with(this@OnesentenseActivity).load(userinfopicture1).into(imageButton5)

        //서버 연결
        mypageRequestToServer.service.profileEditRequest(
            ProfileEditRequest(
                userName = userinfoname,
                role = userinforole,
                intro = userinfointro,
                profileUrl = userinfopicture1
            )).enqueue(object: Callback<ProfileEditResponse> {
            override fun onFailure(call: Call<ProfileEditResponse>, t: Throwable) {
                Log.d("통신 실패", "내 프로필 수정 통신 실패${t}")
            }

            override fun onResponse(
                call: Call<ProfileEditResponse>,
                response: Response<ProfileEditResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.body()!!.success) {
                        Log.d("성공", "내 프로필 수정 성공")
                    } else {
                        Log.d("실패", "내 프로필 수정 실패")
                    }
                }
            }
        })
    }
}