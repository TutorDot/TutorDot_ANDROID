package com.tutor.tutordot.MyPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.tutor.tutordot.MyPage.MyclassEditRecylerView.MyclassEditAdapter
import com.tutor.tutordot.MyPage.MyclassEditRecylerView.MyclassEditData
import com.tutor.tutordot.MyPage.Server.*
import com.tutor.tutordot.R
import com.tutor.tutordot.Startpage.myjwt
import kotlinx.android.synthetic.main.activity_myclass_edit.*
import kotlinx.android.synthetic.main.activity_mypage_addclass.*
import kotlinx.android.synthetic.main.activity_mypage_addclass.btn_plus
import kotlinx.android.synthetic.main.activity_onesentense.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddclassActivity : AppCompatActivity() {
    lateinit var myclassEditAdapter_add: MyclassEditAdapter
    val adata= mutableListOf<MyclassEditData>()

    var color : String = "yellow"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage_addclass)

        val mypageRequestToServer = MyPageRequestToServer
        val newclassname = editTextname.text.toString()


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
            mypageRequestToServer.service.myAddRequest(
                "$myjwt",
                MyAddRequest(
                    lectureName = newclassname,
                    color = color,
                    schedules = listOf(ScehduleData(day = "월", orgStartTime="01:00pm", orgEndTime="03:00pm")),
                    orgLocation = "합정역 할리스커피",
                    bank = et_bank.text.toString(),
                    accountNumber = editText3.text.toString(),
                    totalHours = 10,
                    price = 80
                )
            ).enqueue(object : Callback<MyAddResponse> {
                override fun onFailure(call: Call<MyAddResponse>, t: Throwable) {
                    Log.d("통신 실패", "수업 추가 통신 실패${t}")
                }

                override fun onResponse(
                    call: Call<MyAddResponse>,
                    response: Response<MyAddResponse>
                ) {
                    if (response.isSuccessful) {
                        if (response.body()!!.success) {
                            Log.d("성공", "수업 추가 성공")
                        } else {
                            Log.d("실패", "수업 추가 실패")
                        }
                    }
                }
            })
            finish()
        }

        //컬러팔레트
        my_class_tap_img_yellow2.setOnClickListener {
            if(color.equals("yellow")) {
                my_class_tap_img_yellow2.setImageResource(R.drawable.my_class_tap_edit_img_yellow)
                color = ""
            }
            else {
                color = "yellow"
                my_class_tap_img_yellow2.setImageResource(R.drawable.my_class_tap_edit_img_select_yellow)
                my_class_tap_edit_img_red2.setImageResource(R.drawable.my_class_tap_edit_img_red)
                my_class_tap_edit_img_green2.setImageResource(R.drawable.my_class_tap_edit_img_green)
                my_class_tap_edit_img_blue2.setImageResource(R.drawable.my_class_tap_edit_img_blue)
                my_class_tap_edit_img_purple2.setImageResource(R.drawable.my_class_tap_edit_img_purple)
            }
        }
        my_class_tap_edit_img_red2.setOnClickListener {
            if(color.equals("red")) {
                my_class_tap_edit_img_red2.setImageResource(R.drawable.my_class_tap_edit_img_red)
                color = ""
            }
            else {
                color = "red"
                my_class_tap_img_yellow2.setImageResource(R.drawable.my_class_tap_edit_img_yellow)
                my_class_tap_edit_img_red2.setImageResource(R.drawable.my_class_tap_edit_img_select_red)
                my_class_tap_edit_img_green2.setImageResource(R.drawable.my_class_tap_edit_img_green)
                my_class_tap_edit_img_blue2.setImageResource(R.drawable.my_class_tap_edit_img_blue)
                my_class_tap_edit_img_purple2.setImageResource(R.drawable.my_class_tap_edit_img_purple)
            }
        }
        my_class_tap_edit_img_green2.setOnClickListener {
            if(color.equals("green")) {
                my_class_tap_edit_img_green2.setImageResource(R.drawable.my_class_tap_edit_img_green)
                color = ""
            }
            else {
                color = "green"
                my_class_tap_img_yellow2.setImageResource(R.drawable.my_class_tap_edit_img_yellow)
                my_class_tap_edit_img_red2.setImageResource(R.drawable.my_class_tap_edit_img_red)
                my_class_tap_edit_img_green2.setImageResource(R.drawable.my_class_tap_edit_img_select_green)
                my_class_tap_edit_img_blue2.setImageResource(R.drawable.my_class_tap_edit_img_blue)
                my_class_tap_edit_img_purple2.setImageResource(R.drawable.my_class_tap_edit_img_purple)
            }
        }
        my_class_tap_edit_img_blue2.setOnClickListener {
            if(color.equals("blue")) {
                my_class_tap_edit_img_blue2.setImageResource(R.drawable.my_class_tap_edit_img_blue)
                color = ""
            }
            else {
                color = "blue"
                my_class_tap_img_yellow2.setImageResource(R.drawable.my_class_tap_edit_img_yellow)
                my_class_tap_edit_img_red2.setImageResource(R.drawable.my_class_tap_edit_img_red)
                my_class_tap_edit_img_green2.setImageResource(R.drawable.my_class_tap_edit_img_green)
                my_class_tap_edit_img_blue2.setImageResource(R.drawable.my_class_tap_edit_img_select_blue)
                my_class_tap_edit_img_purple2.setImageResource(R.drawable.my_class_tap_edit_img_purple)
            }
        }
        my_class_tap_edit_img_purple2.setOnClickListener {
            if(color.equals("purple")) {
                my_class_tap_edit_img_purple2.setImageResource(R.drawable.my_class_tap_edit_img_purple)
                color = ""
            }
            else {
                color = "purple"
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