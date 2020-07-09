package com.tutor.tutordot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_class_log_modification.*
import kotlinx.android.synthetic.main.fragment_my.*
import kotlinx.android.synthetic.main.item_classlog.*

class ClassLogModificationActivity : AppCompatActivity() {

    //버튼 늘림 표시
    var circle : Boolean = false
    var triangel : Boolean = false
    var x : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_log_modification)

        //EditText의 기본 텍스트는 사용자가 이전에 입력했던 값으로
        et_log_modi_progress.setText(ser_progress)
        et_log_modi_hw.setText(ser_hw)

        //취소, 저장 버튼 이벤트
        btn_log_cancel.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val backIntent = Intent(this@ClassLogModificationActivity, ClassLogFragment::class.java)
                //startActivity(backIntent)
                finish()
            }
        })


        btn_log_save.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                ser_progress = et_log_modi_progress.text.toString()
                ser_hw = et_log_modi_hw.text.toString()
                modi_check = true
                val backIntent2 = Intent(this@ClassLogModificationActivity, ClassLogFragment::class.java)
                //startActivity(backIntent2)
                finish()
            }
        })

        btn_modi_circle.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                //선택 안되어있을 경우
                if(!circle) {
                    circle = true
                    //서버에게 상태 보내는 코드 추가해야함

                    btn_modi_circle.setBackgroundResource(R.drawable.class_log_btn_circle_pick)
                    btn_modi_triangle.setBackgroundResource(R.drawable.class_log_btn_triangle_unpick)
                    btn_modi_x.setBackgroundResource(R.drawable.class_log_btn_x_unpick)
                }
                else {  //선택되어있을 경우
                    circle = false
                    //서버에게 상태 보내는 코드 추가해야함

                    btn_modi_circle.setBackgroundResource(R.drawable.class_log_btn_circle_unpick)
                    btn_modi_triangle.setBackgroundResource(R.drawable.class_log_btn_triangle_unpick)
                    btn_modi_x.setBackgroundResource(R.drawable.class_log_btn_x_unpick)
                }
            }
        })

        btn_modi_triangle.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                //선택 안되어있을 경우
                if(!triangel) {
                    triangel = true
                    //서버에게 상태 보내는 코드 추가해야함

                    btn_modi_circle.setBackgroundResource(R.drawable.class_log_btn_circle_unpick)
                    btn_modi_triangle.setBackgroundResource(R.drawable.class_log_btn_triangle_pick)
                    btn_modi_x.setBackgroundResource(R.drawable.class_log_btn_x_unpick)
                }
                else {  //선택되어있을 경우
                    triangel = false
                    //서버에게 상태 보내는 코드 추가해야함

                    btn_modi_circle.setBackgroundResource(R.drawable.class_log_btn_circle_unpick)
                    btn_modi_triangle.setBackgroundResource(R.drawable.class_log_btn_triangle_unpick)
                    btn_modi_x.setBackgroundResource(R.drawable.class_log_btn_x_unpick)
                }
            }
        })

        btn_modi_x.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                //선택 안되어있을 경우
                if(!x) {
                    x = true
                    //서버에게 상태 보내는 코드 추가해야함

                    btn_modi_circle.setBackgroundResource(R.drawable.class_log_btn_circle_unpick)
                    btn_modi_triangle.setBackgroundResource(R.drawable.class_log_btn_triangle_unpick)
                    btn_modi_x.setBackgroundResource(R.drawable.class_log_btn_x_pick)
                }
                else {  //선택되어있을 경우
                    x = false
                    //서버에게 상태 보내는 코드 추가해야함

                    btn_modi_circle.setBackgroundResource(R.drawable.class_log_btn_circle_unpick)
                    btn_modi_triangle.setBackgroundResource(R.drawable.class_log_btn_triangle_unpick)
                    btn_modi_x.setBackgroundResource(R.drawable.class_log_btn_x_unpick)
                }
            }
        })
    }
}