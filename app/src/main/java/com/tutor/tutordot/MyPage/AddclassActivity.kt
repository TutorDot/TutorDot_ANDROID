package com.tutor.tutordot.MyPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.tutor.tutordot.MyPage.MyclassEditRecylerView.MyclassEditAdapter
import com.tutor.tutordot.MyPage.MyclassEditRecylerView.MyclassEditData
import com.tutor.tutordot.R
import kotlinx.android.synthetic.main.activity_myclass_edit.*
import kotlinx.android.synthetic.main.activity_mypage_addclass.*
import kotlinx.android.synthetic.main.activity_mypage_addclass.btn_plus
import kotlinx.android.synthetic.main.activity_onesentense.*

class AddclassActivity : AppCompatActivity() {
    lateinit var myclassEditAdapter_add: MyclassEditAdapter
    val adata= mutableListOf<MyclassEditData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage_addclass)

        myclassEditAdapter_add= MyclassEditAdapter(this)
        rv_new_classtime.adapter=myclassEditAdapter_add
        val myLayoutManager = LinearLayoutManager(this)
        rv_new_classtime.layoutManager= myLayoutManager

        loadDatas()

        //취소버튼
        btn_cancel_my_add.setOnClickListener{
            finish()
        }
        //저장버튼
        btn_save_my_add.setOnClickListener{
            //서버에 데이터 PUT
            finish()
        }

        var yellow : Boolean = false
        var green : Boolean = false
        var red : Boolean = false
        var blue : Boolean = false
        var purple : Boolean = false

        //컬러팔레트
        my_class_tap_img_yellow2.setOnClickListener {
            if(yellow)
                my_class_tap_img_yellow2.setImageResource(R.drawable.my_class_tap_edit_img_yellow)
            else {
                my_class_tap_img_yellow2.setImageResource(R.drawable.my_class_tap_edit_img_select_yellow)
                my_class_tap_edit_img_red2.setImageResource(R.drawable.my_class_tap_edit_img_red)
                my_class_tap_edit_img_green2.setImageResource(R.drawable.my_class_tap_edit_img_green)
                my_class_tap_edit_img_blue2.setImageResource(R.drawable.my_class_tap_edit_img_blue)
                my_class_tap_edit_img_purple2.setImageResource(R.drawable.my_class_tap_edit_img_purple)
            }
        }
        my_class_tap_edit_img_red2.setOnClickListener {
            if(red)
                my_class_tap_edit_img_red2.setImageResource(R.drawable.my_class_tap_edit_img_red)
            else {
                my_class_tap_img_yellow2.setImageResource(R.drawable.my_class_tap_edit_img_yellow)
                my_class_tap_edit_img_red2.setImageResource(R.drawable.my_class_tap_edit_img_select_red)
                my_class_tap_edit_img_green2.setImageResource(R.drawable.my_class_tap_edit_img_green)
                my_class_tap_edit_img_blue2.setImageResource(R.drawable.my_class_tap_edit_img_blue)
                my_class_tap_edit_img_purple2.setImageResource(R.drawable.my_class_tap_edit_img_purple)
            }
        }
        my_class_tap_edit_img_green2.setOnClickListener {
            if(green)
                my_class_tap_edit_img_green2.setImageResource(R.drawable.my_class_tap_edit_img_green)
            else {
                my_class_tap_img_yellow2.setImageResource(R.drawable.my_class_tap_edit_img_yellow)
                my_class_tap_edit_img_red2.setImageResource(R.drawable.my_class_tap_edit_img_red)
                my_class_tap_edit_img_green2.setImageResource(R.drawable.my_class_tap_edit_img_select_green)
                my_class_tap_edit_img_blue2.setImageResource(R.drawable.my_class_tap_edit_img_blue)
                my_class_tap_edit_img_purple2.setImageResource(R.drawable.my_class_tap_edit_img_purple)
            }
        }
        my_class_tap_edit_img_blue2.setOnClickListener {
            if(blue)
                my_class_tap_edit_img_blue2.setImageResource(R.drawable.my_class_tap_edit_img_blue)
            else {
                my_class_tap_img_yellow2.setImageResource(R.drawable.my_class_tap_edit_img_yellow)
                my_class_tap_edit_img_red2.setImageResource(R.drawable.my_class_tap_edit_img_red)
                my_class_tap_edit_img_green2.setImageResource(R.drawable.my_class_tap_edit_img_green)
                my_class_tap_edit_img_blue2.setImageResource(R.drawable.my_class_tap_edit_img_select_blue)
                my_class_tap_edit_img_purple2.setImageResource(R.drawable.my_class_tap_edit_img_purple)
            }
        }
        my_class_tap_edit_img_purple2.setOnClickListener {
            if(purple)
                my_class_tap_edit_img_purple2.setImageResource(R.drawable.my_class_tap_edit_img_purple)
            else {
                my_class_tap_img_yellow2.setImageResource(R.drawable.my_class_tap_edit_img_yellow)
                my_class_tap_edit_img_red2.setImageResource(R.drawable.my_class_tap_edit_img_red)
                my_class_tap_edit_img_green2.setImageResource(R.drawable.my_class_tap_edit_img_green)
                my_class_tap_edit_img_blue2.setImageResource(R.drawable.my_class_tap_edit_img_blue)
                my_class_tap_edit_img_purple2.setImageResource(R.drawable.my_class_tap_edit_img_select_purple)
            }
        }

        //수업시간 추가
        btn_plus.setOnClickListener{
            adata.add(
                MyclassEditData(
                    weekday="월",
                    starttime="00:00 AM",
                    endtime = "00:00 AM"
                )
            )
            myclassEditAdapter_add.notifyDataSetChanged()

        }
    }
    private fun loadDatas(){
        adata.apply {
            add(
                MyclassEditData(
                    weekday = "월",
                    starttime = "00:00am",
                    endtime="00:00am"

                )
            )

        }
        myclassEditAdapter_add.datas=adata
       // myclassEditAdapter_add.notifyDataSetChanged()
    }
}