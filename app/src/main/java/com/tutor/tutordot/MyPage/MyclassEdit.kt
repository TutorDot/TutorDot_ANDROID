package com.tutor.tutordot.MyPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.tutor.tutordot.MyPage.MyclassEditRecylerView.MyclassEditAdapter
import com.tutor.tutordot.MyPage.MyclassEditRecylerView.MyclassEditData
import com.tutor.tutordot.R
import kotlinx.android.synthetic.main.activity_myclass_edit.*
import kotlinx.android.synthetic.main.fragment_my.*

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