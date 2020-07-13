package com.tutor.tutordot.MyPage

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tutor.tutordot.R
import kotlinx.android.synthetic.main.activity_invite.*


class InviteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invite)

        schedule_class_math_btn_back.setOnClickListener{
            val intentback= Intent(this, MyinfoActivity::class.java)
            startActivity(intentback)
            finish()
        }

        //클립보드 복사부분
        val textView = findViewById(R.id.copytext) as TextView //텍스트뷰
        val copybutton = findViewById<ImageButton>(R.id.imagecopy)
        val copytext = textView.text.toString() // 텍스트뷰 글자 가져옴

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