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