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

        btn_cancle_myedit.setOnClickListener{
            val intent2= Intent(this, MyinfoActivity::class.java)
            startActivity(intent2)
            finish()
        }


    }



    private fun loadDatas(){
        datas.apply {
            add(
                MyclassEditData(
                    classtime = "월 1시부터 3시"
                )
            )
            add(
                MyclassEditData(
                    classtime = "월 3시부터 4시"
                )
            )
            add(
                MyclassEditData(
                    classtime = "월 4시부터 5시"
                )
            )
        }
        myclassEditAdapter.datas=datas
        myclassEditAdapter.notifyDataSetChanged()
    }
}