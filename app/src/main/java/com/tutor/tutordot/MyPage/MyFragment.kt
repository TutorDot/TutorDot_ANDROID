package com.tutor.tutordot.MyPage
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.tutor.tutordot.Calendar.CalendarLogRecyclerView.haveCalendarData
import com.tutor.tutordot.CalenderActivity
import com.tutor.tutordot.MyPage.MypageRecylerView.MypageAdapter
import com.tutor.tutordot.MyPage.MypageRecylerView.MypageData
import com.tutor.tutordot.MyPage.MypageRecylerView.haveMyData
import com.tutor.tutordot.MyPage.Server.ClassListResponse
import com.tutor.tutordot.MyPage.Server.MyInfoResponse
import com.tutor.tutordot.MyPage.Server.MyPageRequestToServer
import com.tutor.tutordot.MyPage.Server.UserRequestToServer
import com.tutor.tutordot.R
import com.tutor.tutordot.Startpage.*
import com.tutor.tutordot.Startpage.AutoLogin.MySharedPreferences
import com.tutor.tutordot.extention.customEnqueue
import com.tutor.tutordot.extention.showToast
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
var userinfopicture1 : String? = ""
var myjwt2: String= myjwt.toString()

class MyFragment : Fragment() {

    lateinit var mypageAdapter: MypageAdapter
    var datas= mutableListOf<MypageData>()
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
        datas= mutableListOf<MypageData>()

        mypageAdapter= MypageAdapter(view.context)
        recyclerView_my.adapter=mypageAdapter
        loadDatas()

        one_sentense.setText(newIntro)

        //myinfo 서버연결(user)



        userRequestToServer.service.myInfoRequest(
            "${myjwt}"
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
                        if(role=="tutor"){
                            if(!response. body()!!.data!!.intro.isNullOrBlank()){
                            userinfointro = response. body()!!.data!!.intro
                            }
                        }

                        userinfopicture = response. body()!!.data!!.profileUrl

                        textView.setText(userinfoname)
                        textView2.setText(userinforole)
                        one_sentense.setText(userinfointro)
                        Glide.with(this@MyFragment).load(userinfopicture).into(my_img_profile)

                        if(datas.size > 0  && userinfointro.length > 0)
                        {
                            one_sentense.setText(userinfointro)
                        }
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
            if (looking== true){
                Toast.makeText(activity, "둘러보기 계정은 정보수정이 불가능합니다.", Toast.LENGTH_SHORT).show()
            }
            else {
                val profileintent = Intent(activity, OnesentenseActivity::class.java)
                startActivity(profileintent)
            }

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
                val intent4=Intent(activity, LoginFor1stActivity::class.java)
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
                if (looking== true){
                    Toast.makeText(activity, "둘러보기 계정은 탈퇴가 불가능합니다.", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                else{
                MyPageRequestToServer.service.memberDeleteRequest(
                    "${myjwt}"
                ).customEnqueue(
                    onError = { Log.d("올바르지 못한 요청입니다", "올바르지 못한 요청입니다") },
                    onSuccess = {
                        if (it.success) {
                            MySharedPreferences.islogin = false
                            Log.d("삭제 완료", "삭제 완료")
                        } else {
                            Log.d("삭제 실패", "삭제 실패")
                        }
                    }
                )
                val intent7=Intent(activity, SignUpActivity::class.java)
                activity?.finish()
                startActivity(intent7)
                }
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
        var classlistprofile1: String?
        var classlistprofile2: String?


        mypageRequestToServer.service.classListRequest(
            "${myjwt}"
        ).enqueue(object: Callback<ClassListResponse>{
            override fun onFailure(call: Call<ClassListResponse>, t: Throwable) {
                Log.d("통신 실패", "classlist통신 실패${t}")
                haveMyData = false
                recyclerView_my.visibility = View.GONE
                cl_my.visibility =View.VISIBLE
            }
            override fun onResponse(
                call: Call<ClassListResponse>,
                response: Response<ClassListResponse>
            ) {
                if (response.isSuccessful){
                    if(response.body()!!.success) {
                        Log.d("성공", "classlist성공"+response.body())
                        Log.d(response.body()!!.data.toString(),response.body()!!.data.toString())

                        if (response.body()!!.data.size>0){
                            if(response.body()!!.data[0]!!.profileUrls.size>0){
                                userinfopicture1 = response.body()!!.data[0]!!.profileUrls[0]!!.profileUrl
                            }

                        }


                        //데이터가 없을 경우 haveData를 false로 바꿔줌
                        if(response.body()!!.data.size == 0)
                        {
                            recyclerView_my.visibility = View.GONE
                            cl_my.visibility = View.VISIBLE
                            haveMyData = false
                        }
                        else {
                            cl_my.visibility = View.GONE
                            recyclerView_my.visibility = View.VISIBLE
                            haveMyData = true
                        }
//                            userinfopicture1 = response.body()!!.data[0]!!.profileUrls[0]!!.profileUrl}

                        for (i in 0 until response.body()!!.data.size){
                        classlistColor=response.body()!!.data[i]!!.color.toString()
                        classlistLectureName=response.body()!!.data[i]!!.lectureName.toString()
                            if (response.body()!!.data[i]!!.profileUrls.size==1){
                                classlistprofile2=null
                                classlistprofile1=response.body()!!.data[i]!!.profileUrls[0].profileUrl
                            } else if (response.body()!!.data[i]!!.profileUrls.size==2){
                                classlistprofile1=response.body()!!.data[i]!!.profileUrls[0].profileUrl
                                classlistprofile2=response.body()!!.data[i]!!.profileUrls[1].profileUrl}else{
                                classlistprofile2=null
                                classlistprofile1=null
                            }


                        datas.apply{
                            add(
                                MypageData(
                                    color = classlistColor,
                                    content= classlistLectureName,
                                    profileUrl1 = classlistprofile1,
                                    profileUrl2 = classlistprofile2,
                                    lectureId = response.body()!!.data[i].lectureId
                                ))}
                        mypageAdapter.datas = datas
                        mypageAdapter.notifyDataSetChanged()
                            }
                    }else{
                        Log.d("실패", "classlist실패"+response.headers())


                        haveMyData = false
                        recyclerView_my.visibility = View.GONE
                        cl_my.visibility =View.VISIBLE
                    }
                }
            }

        })

    }



}



