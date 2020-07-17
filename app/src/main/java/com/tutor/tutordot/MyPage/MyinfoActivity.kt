package com.tutor.tutordot.MyPage

import android.app.AlertDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.tutor.tutordot.CalenderActivity
import com.tutor.tutordot.R
import kotlinx.android.synthetic.main.activity_myinfo.*
import kotlinx.android.synthetic.main.activity_onesentense.*


class MyinfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myinfo)

        Glide.with(this@MyinfoActivity).load(userinfopicture1).into(my_class_tap_img_profile)
        profile_name.setText(userinfoname)
        profile_introduce.setText(userinfointro)

        //버튼
        my_class_tap_btn_back.setOnClickListener(object : View.OnClickListener {
            override fun onClick(arg0: View?) {
                finish()
            }
        })
        my_class_tap_btn_edit.setOnClickListener{
            val intent2= Intent(this, MyclassEdit::class.java)
            startActivity(intent2)
            finish()
        }
        my_class_tap_btn_invite.setOnClickListener{
            var invIntent = Intent(this, InviteActivity::class.java)
            startActivity(invIntent)
        }

        //팝업창
        my_class_tap_btn_unconnect.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val dialog = builder.create()
            val dialogView = layoutInflater.inflate(R.layout.activity_popup_disconnect, null)
            val yes= dialogView.findViewById<ImageButton>(R.id.my_disconnect_btn_yes)
            val no= dialogView.findViewById<ImageButton>(R.id.my_disconnect_disconnect_btn_no)
            yes.setOnClickListener{
                dialog.dismiss()
            }
            no.setOnClickListener{
                val intent9=Intent(this, AddclassActivity::class.java)
                startActivity(intent9)

            }

            dialog.setView(dialogView)
            dialog.show()

        }

        //클립보드 복사부분
        val textView = findViewById(R.id.account_number) as TextView //텍스트뷰
        val copybutton = findViewById<Button>(R.id.my_class_tap_btn_copy)
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
                        ClipData.newPlainText("Account", copytext) //클립보드에 Account라는 이름표로 계좌번호를 복사하여 저장
                    clipboardManager.setPrimaryClip(clipData)

                    //복사가 되었다면 토스트메시지 노출
                    Toast.makeText(this@MyinfoActivity, "계좌번호가 복사되었습니다.", Toast.LENGTH_SHORT).show()
                }
                return true
            }
        })

    }



}


