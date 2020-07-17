package com.tutor.tutordot.MyPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tutor.tutordot.MyPage.Server.ConnectRequest
import com.tutor.tutordot.MyPage.Server.ConnectResponse
import com.tutor.tutordot.MyPage.Server.InviteResponse
import com.tutor.tutordot.MyPage.Server.MyPageRequestToServer
import com.tutor.tutordot.R
import com.tutor.tutordot.extention.showToast
import kotlinx.android.synthetic.main.activity_invite_for_tutee.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InviteForTuteeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invite_for_tutee)
        //서버 연결
        val mypageRequestToServer = MyPageRequestToServer

        schedule_class_math_btn_back.setOnClickListener{
            finish()
        }

        btn_connect.setOnClickListener {
            //초대코드 연결
            mypageRequestToServer.service.requestConnect(
                ConnectRequest(
                    code = codeInvite
                )
            ).enqueue(object : Callback<ConnectResponse> {
                override fun onFailure(call: Call<ConnectResponse>, t: Throwable) {
                    Log.d("통신 실패", "${t}")
                }

                override fun onResponse(
                    call: Call<ConnectResponse>,
                    response: Response<ConnectResponse>
                ) {
                    // 통신 성공
                    if (response.isSuccessful) {   // statusCode가 200-300 사이일 때, 응답 body 이용 가능
                        if (response.body()!!.success) {
                            Log.d("튜터 연결 성공", "성공")
                            showToast("튜터와 연결되었습니다.")
                            finish()

                        } else {
                            Log.d("튜터와 연결에실패했습니다.", "${response.body()}")
                        }
                    }
                }

            })
        }
    }
}