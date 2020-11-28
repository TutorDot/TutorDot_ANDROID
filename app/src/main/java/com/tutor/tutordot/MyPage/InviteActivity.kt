package com.tutor.tutordot.MyPage

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tutor.tutordot.ClassLog.Server.LogRequestToServer
import com.tutor.tutordot.ClassLog.Server.LogResponse
import com.tutor.tutordot.MyPage.Server.InviteResponse
import com.tutor.tutordot.MyPage.Server.MyPageRequestToServer
import com.tutor.tutordot.R
import com.tutor.tutordot.Startpage.myjwt
import com.tutor.tutordot.extention.progressOFF
import com.tutor.tutordot.extention.progressON
import kotlinx.android.synthetic.main.activity_invite.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


var codeInvite : String = ""

class InviteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invite)
        progressON(this)

        var mylid:Int = intent.getIntExtra("mylid", 1)
        Log.d("lid는", "${mylid}")
        var copytextshow = copytext

        //서버 연결
        val mypageRequestToServer = MyPageRequestToServer

        schedule_class_math_btn_back.setOnClickListener{
            val intentback= Intent(this, MyinfoActivity::class.java)
            //startActivity(intentback)
            finish()
        }

        //클립보드 복사부분
        val textView = findViewById(R.id.copytext) as TextView //텍스트뷰
        val copybutton = findViewById<ImageButton>(R.id.imagecopy)
        var copytext = textView.text.toString() // 텍스트뷰 글자 가져옴

        //초대코드 서버에서 받아옴
        mypageRequestToServer.service.inviteRequest(
            "${myjwt}", "${mylid}"
        ).enqueue(object : Callback<InviteResponse> {
            override fun onFailure(call: Call<InviteResponse>, t: Throwable) {
                Log.d("초대코드 통신 실패", "${t}")
            }

            override fun onResponse(
                call: Call<InviteResponse>,
                response: Response<InviteResponse>
            ) {
                // 통신 성공
                if (response.isSuccessful) {   // statusCode가 200-300 사이일 때, 응답 body 이용 가능
                    if (response.body()!!.success) {
                        Log.d("초대코드 받아오기 성공", "성공")
                        Log.d("데이터", response.body()!!.data.toString())
                        copytext = response.body()!!.data!!.code
                        codeInvite = copytext
                        copytextshow.text = copytext
                        progressOFF()

                    } else {
                        Log.d("초대코드 받아오기 실패", "${response.body()}")
                        progressOFF()
                    }
                }
            }

        })

        copybutton.setOnTouchListener(object : View.OnTouchListener {
            //터치 이벤트 리스너 등록(누를때)
            override fun onTouch(v: View?, event: MotionEvent): Boolean {
                // TODO Auto-generated method stub
                if (event.action == MotionEvent.ACTION_DOWN) { //눌렀을 때 동작

                    //클립보드 사용 코드
                    val clipboardManager =
                        getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clipData =
                        ClipData.newPlainText("Code", copytext) //클립보드에 ID라는 이름표로 id 값을 복사하여 저장
                    clipboardManager.setPrimaryClip(clipData)

                    //복사가 되었다면 토스트메시지 노출
                    Toast.makeText(this@InviteActivity, "초대코드가 복사되었습니다.", Toast.LENGTH_SHORT).show()
                }
                return true
            }
        })

    }
}