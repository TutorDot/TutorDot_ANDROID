package com.tutor.tutordot.MyPage

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tutor.tutordot.LoginActivity
import com.tutor.tutordot.MyPage.MypageRecylerView.MypageAdapter
import com.tutor.tutordot.MyPage.MypageRecylerView.MypageData
import com.tutor.tutordot.R
import kotlinx.android.synthetic.main.fragment_my.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyFragment : Fragment() {

    lateinit var mypageAdapter: MypageAdapter
    val datas= mutableListOf<MypageData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mypageAdapter= MypageAdapter(view.context)
        recyclerView_my.adapter=mypageAdapter
        loadDatas()

        imageButton2.setOnClickListener{
            val intent = Intent(activity, AddclassActivity::class.java)
            startActivity(intent)
        }
        my_img_profile.setOnClickListener{
            val profileintent=Intent(activity, OnesentenseActivity::class.java)
            startActivity(profileintent)

        }




    }
    private fun loadDatas(){
        datas.apply{
            add(
                MypageData(
                    color = "@drawable/notice_color_img_red",
                    content= "나와라ㅏㅏㅏ"
                ))
            add(
                MypageData(
                    color = "@drawable/notice_color_img_green",
                    content= "나와라ㅏㅏㅏ2"
                ))

        }
        mypageAdapter.datas = datas
        mypageAdapter.notifyDataSetChanged()
    }



}

