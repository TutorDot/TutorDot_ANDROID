package com.tutor.tutordot.MyPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.tutor.tutordot.MyPage.MyclassEditRecylerView.MyclassEditAdapter
import com.tutor.tutordot.MyPage.MyclassEditRecylerView.MyclassEditData
import com.tutor.tutordot.R
import kotlinx.android.synthetic.main.activity_myclass_edit.*

class MyclassEdit : AppCompatActivity() {

   // lateinit var myclassEditAdapter: MyclassEditAdapter
    val datas= mutableListOf<MyclassEditData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myclass_edit)

        var mylid:Int=0
        mylid = intent.getIntExtra("mylid",1)
        var mycname = intent.getStringExtra("mycname")
        var mycolor = intent.getStringExtra("mycolor")
        var mytime = intent.getStringExtra("mytime")
        var mymoney = intent.getStringExtra("mymoney")
        var mybank = intent.getStringExtra("mybank")
        var myaccount = intent.getStringExtra("myaccount")
        var myclasstime = intent.getStringExtra("myclasstime")
        var myplace = intent.getStringExtra("myplace")

        editText.setText(mycname)
        et_mytime.setText(mytime)
        editText2.setText(mymoney)
        et_mybank.setText(mybank)
        editText4.setText(myaccount)
        et_myplace.setText(myplace)

        if (mycolor=="yellow"){
            my_class_tap_img_yellow.setImageResource(R.drawable.my_class_tap_edit_img_select_yellow)
        }else if (mycolor=="red"){
            my_class_tap_edit_img_red.setImageResource(R.drawable.my_class_tap_edit_img_select_red)
        }else if (mycolor=="green"){
            my_class_tap_edit_img_green.setImageResource(R.drawable.my_class_tap_edit_img_select_green)
        }else if (mycolor=="blue"){
            my_class_tap_edit_img_red.setImageResource(R.drawable.my_class_tap_edit_img_select_blue)
        }else if (mycolor=="purple"){
            my_class_tap_edit_img_red.setImageResource(R.drawable.my_class_tap_edit_img_select_purple)
        }








        //myclassEditAdapter= MyclassEditAdapter(this)
        //recyclerView2.adapter=myclassEditAdapter
        val myLayoutManager = LinearLayoutManager(this)
        recyclerView2.layoutManager= myLayoutManager



        //취소 버튼
        btn_cancle_myedit.setOnClickListener{
            val intent2= Intent(this, MyinfoActivity::class.java)
            startActivity(intent2)
            finish()
        }

        //+버튼
        btn_plus.setOnClickListener{
            datas.add(
                MyclassEditData(
                    weekday="",
                    starttime="00:00am",
                    endtime = "00:00pm"
                )
            )
        }

    }

}