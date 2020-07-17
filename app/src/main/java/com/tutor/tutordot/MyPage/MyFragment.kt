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
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.tutor.tutordot.Calendar.CalendarLogRecyclerView.haveCalendarData
import com.tutor.tutordot.Startpage.LoginActivity
import com.tutor.tutordot.MyPage.MypageRecylerView.MypageAdapter
import com.tutor.tutordot.MyPage.MypageRecylerView.MypageData
import com.tutor.tutordot.MyPage.MypageRecylerView.haveMyData
import com.tutor.tutordot.MyPage.Server.ClassListResponse
import com.tutor.tutordot.MyPage.Server.MyInfoResponse
import com.tutor.tutordot.MyPage.Server.MyPageRequestToServer
import com.tutor.tutordot.MyPage.Server.UserRequestToServer
import com.tutor.tutordot.R
import com.tutor.tutordot.Startpage.AutoLogin.MySharedPreferences
import com.tutor.tutordot.Startpage.SignUpActivity
import com.tutor.tutordot.Startpage.role
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_class_log.*
import kotlinx.android.synthetic.main.fragment_my.*
import kotlinx.android.synthetic.main.item_mypage.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//간단정보
var userinfoname:String = ""
var userinforole: String = ""
var userinfointro: String = ""
var userinfopicture: String? = ""
var userinfopicture1 : String = ""
var classlistprofile1: String = "https://sopt-26-usy.s3.ap-northeast-2.amazonaws.com/1594963465636.jpg"

class MyFragment : Fragment() {

    lateinit var mypageAdapter: MypageAdapter
    val datas= mutableListOf<MypageData>()
    //서버 연결
    val mypageRequestToServer = MyPageRequestToServer
    val userRequestToServer = UserRequestToServer




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

        one_sentense.setText(newIntro)

        //myinfo 서버연결(user)



        userRequestToServer.service.myInfoRequest(
        ).enqueue(object: Callback<MyInfoResponse>{
            override fun onFailure(call: Call<MyInfoResponse>, t: Throwable) {
                Log.d("통신 실패", "myinfo통신 실패${t}")
            }
            override fun onResponse(
                call: Call<MyInfoResponse>,
                response: Response<MyInfoResponse>
            ) {
                if (response.isSuccessful){
                    if(response.body()!!.success) {
                        Log.d("성공", "myinfo성공")
                        Log.d(response.body()!!.data.toString(),response.body()!!.data.toString())
                        userinfoname = response.body()!!.data!!.userName
                        userinforole = response.body()!!.data!!.role
                        userinfointro = response. body()!!.data!!.intro
                        userinfopicture = response. body()!!.data!!.profilUrl

                        textView.setText(userinfoname)
                        textView2.setText(userinforole)
                        one_sentense.setText(userinfointro)
                        //Glide.with(this@MyFragment).load(userinfopicture).into(my_img_profile)

                        role = userinforole

                    }else{
                        Log.d("실패", "myinfo실패")
                    }
                }
            }
        })




        //화면이동
        imageButton2.setOnClickListener{
            if(role == "tutor"){
                val intent = Intent(activity, AddclassActivity::class.java)
                startActivity(intent)
            }
            else if(role == "tutee"){
                val intent = Intent(activity, InviteForTuteeActivity::class.java)
                startActivity(intent)
            }
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
                MySharedPreferences.islogin = false
                activity?.finish()
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
        //classlist 서버연결(mypage)
        var classlistLectureId: Int
        var classlistLectureName: String
        var classlistProfile1 : String
        var classlistColor: String
        var classlistprofile2: String


        mypageRequestToServer.service.classListRequest(
        ).enqueue(object: Callback<ClassListResponse>{
            override fun onFailure(call: Call<ClassListResponse>, t: Throwable) {
                Log.d("통신 실패", "classlist통신 실패${t}")
                Log.d("통신 실패", "classlist통신 실패${t}")
            }
            override fun onResponse(
                call: Call<ClassListResponse>,
                response: Response<ClassListResponse>
            ) {
                if (response.isSuccessful){
                    if(response.body()!!.success) {
                        Log.d("성공", "classlist성공")
                        Log.d(response.body()!!.data.toString(),response.body()!!.data.toString())
                        userinfopicture1 = response.body()!!.data[0]!!.profileUrls[0]!!.profileUrl
                        Glide.with(this@MyFragment).load(userinfopicture1).into(my_img_profile)
                        classlistprofile1 = response.body()!!.data[0]!!.profileUrls[1]!!.profileUrl

                        //데이터가 없을 경우 haveData를 false로 바꿔줌
                        if(response.body()!!.data.size == 0)
                            haveMyData = false
                        else
                            haveMyData = true

                    }else{
                        Log.d("실패", "classlist실패")
                    }
                }
            }

        })

        datas.apply{
//            add(
//                MypageData(
 //                   color = "@drawable/notice_color_img_red",
//                    content= "나와라ㅏㅏㅏ"
 //               ))
 //           add(
 //               MypageData(
 //                   color = "@drawable/notice_color_img_green",
//                    content= "나와라ㅏㅏㅏ2"
//                ))

        }
        mypageAdapter.datas = datas
        mypageAdapter.notifyDataSetChanged()
    }



}



