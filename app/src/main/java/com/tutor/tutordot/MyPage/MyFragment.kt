package com.tutor.tutordot.MyPage

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.tutor.tutordot.ClassLog.LogdateRecyclerView.LogdateAdapter
import com.tutor.tutordot.ClassLog.LogdateRecyclerView.LogdateData
import com.tutor.tutordot.ClassLog.Server.LogRequestToServer
import com.tutor.tutordot.Startpage.LoginActivity
import com.tutor.tutordot.MyPage.MypageRecylerView.MypageAdapter
import com.tutor.tutordot.MyPage.MypageRecylerView.MypageData
import com.tutor.tutordot.MyPage.MypageRecylerView.haveMyData
import com.tutor.tutordot.MyPage.Server.ClassList.ClassListResponse
import com.tutor.tutordot.MyPage.Server.MyPageRequestToServer
import com.tutor.tutordot.R
import com.tutor.tutordot.Startpage.SignUpActivity
import com.tutor.tutordot.extention.customEnqueue
import com.tutor.tutordot.extention.showToast
import kotlinx.android.synthetic.main.fragment_my.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Header


class MyFragment : Fragment() {

    lateinit var mypageAdapter: MypageAdapter
    val datas= mutableListOf<MypageData>()
    //서버 연결
    val mypageRequestToServer = MyPageRequestToServer


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

        //서버연결
        mypageRequestToServer.service.classListRequest(
        ).enqueue(object : Callback<ClassListResponse> {
            override fun onFailure(call: Call<ClassListResponse>, t: Throwable) {
                Log.d("통신 실패", "통신 실패")
            }

            override fun onResponse(
                call: Call<ClassListResponse>,
                response: Response<ClassListResponse>
            ) {
                if(response.isSuccessful){
                    if(response.body()!!.success){
                        Log.d("성공", "성공")
                        Log.d(response.body()!!.data.toString(),response.body()!!.data.toString())
                        //progressDate = response.body()!!.data[5].classDate
                        //progressCycle = response.body()!!.data[5].depositCycle
//                        progressTimes = response.body()!!.data[5].times
//                        progressHour = response.body()!!.data[5].hour
//                        tv_progress_times.setText(progressTimes.toString() + "회차 " + progressHour.toString() + "시간")
//                        tv_progress_alltime.setText(progressCycle.toString() + "시간")
//                        progressStatus = 100*progressHour/progressCycle
//                        pb_class.progress = progressStatus
//                        tv_percent.setText(progressStatus.toString() + "%")

                        //Log.d(progressCycle.toString(), progressCycle.toString())
                        //Log.d(progressTimes.toString(), progressTimes.toString())
                        //Log.d(progressHour.toString(), progressHour.toString())
                        //Log.d(progressStatus.toString(), progressStatus.toString())
                    }else{
                        Log.d("실패", "실패")
                    }
                }
            }

        })


        //화면이동
        imageButton2.setOnClickListener{
            val intent = Intent(activity, AddclassActivity::class.java)
            startActivity(intent)
        }

        my_img_profile.setOnClickListener{
            val profileintent=Intent(activity, OnesentenseActivity::class.java)
            startActivity(profileintent)

        }
        my_btn_developer.setOnClickListener{
            val pintent=Intent(activity, DeveloperActivity::class.java)
            startActivity(pintent)
        }


        //팝업
        my_btn_logout.setOnClickListener {
            val builder = AlertDialog.Builder(activity)
            val dialog = builder.create()
            val dialogView = layoutInflater.inflate(R.layout.activity_popup_logout, null)
            val yes= dialogView.findViewById<ImageButton>(R.id.my_logout_btn_yes)
            val no= dialogView.findViewById<ImageButton>(R.id.my_logout_btn_no)
            yes.setOnClickListener{
                val intent4=Intent(activity, LoginActivity::class.java)
                startActivity(intent4)
            }
            no.setOnClickListener{
                dialog.dismiss()
            }
            dialog.setView(dialogView)
            dialog.show()

        }
        my_btn_withdrawl.setOnClickListener {
            val builder = AlertDialog.Builder(activity)
            val dialog = builder.create()
            val dialogView = layoutInflater.inflate(R.layout.activity_popup_byeservice, null)
            val yes= dialogView.findViewById<ImageButton>(R.id.my_disconnect_btn_yes)
            val no= dialogView.findViewById<ImageButton>(R.id.my_disconnect_btn_no)
            yes.setOnClickListener{
                dialog.dismiss()
            }
            no.setOnClickListener{
                val intent7=Intent(activity, SignUpActivity::class.java)
                startActivity(intent7)
            }
            dialog.setView(dialogView)
            dialog.show()
        }

        //데이터 있을때 / 없을때
        if(haveMyData == true) {
            cl_my.visibility =View.GONE
            recyclerView_my.visibility = View.VISIBLE
        }
        else {
            recyclerView_my.visibility = View.GONE
            cl_my.visibility =View.VISIBLE
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



