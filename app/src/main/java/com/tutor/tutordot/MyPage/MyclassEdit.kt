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

    lateinit var myclassEditAdapter: MyclassEditAdapter
    val datas= mutableListOf<MyclassEditData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myclass_edit)

        myclassEditAdapter= MyclassEditAdapter(this)
        recyclerView2.adapter=myclassEditAdapter
        val myLayoutManager = LinearLayoutManager(this)
        recyclerView2.layoutManager= myLayoutManager

        loadDatas()

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
            myclassEditAdapter.notifyDataSetChanged()
        }

    }



    private fun loadDatas(){
        datas.apply {
            add(
                MyclassEditData(
                    weekday = "월",
                    starttime = "2:00pm",
                    endtime="3:00pm"

                )
            )
            add(
                MyclassEditData(
                    weekday = "화",
                    starttime = "2:00pm",
                    endtime="3:00pm"

                )
            )
            add(
                MyclassEditData(
                    weekday = "수",
                    starttime = "2:00pm",
                    endtime="3:00pm"

                )
            )
        }
        myclassEditAdapter.datas=datas
        myclassEditAdapter.notifyDataSetChanged()
    }
}