package com.tutor.tutordot.MyPage

import android.app.AlertDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.tutor.tutordot.CalenderActivity
import com.tutor.tutordot.MyPage.Server.ClassInfoResponse
import com.tutor.tutordot.MyPage.Server.MyAddRequest
import com.tutor.tutordot.MyPage.Server.MyPageRequestToServer
import com.tutor.tutordot.MyPage.Server.ScheduleData
import com.tutor.tutordot.R
import com.tutor.tutordot.Startpage.myjwt
import com.tutor.tutordot.Startpage.role
import com.tutor.tutordot.extention.customEnqueue
import com.tutor.tutordot.extention.progressOFF
import com.tutor.tutordot.extention.progressON
import com.tutor.tutordot.extention.showToast
import kotlinx.android.synthetic.main.activity_calender.*
import kotlinx.android.synthetic.main.activity_calender.view.*
import kotlinx.android.synthetic.main.activity_class_log_modification.*
import kotlinx.android.synthetic.main.activity_myinfo.*
import kotlinx.android.synthetic.main.activity_onesentense.*
import kotlinx.android.synthetic.main.fragment_my.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyinfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myinfo)


        Glide.with(this@MyinfoActivity).load(userinfopicture1).into(my_class_tap_img_profile)
        profile_name.setText(userinfoname)
        profile_introduce.setText(userinfointro)

        val myPageRequestToServer = MyPageRequestToServer
        var tmpcolor:String=""
        var mycname:String=""
        var mytime:String=""
        var mybank:String=""
        var mymoney:String=""
        var myaccount:String=""
        var myclasstime: String=""
        var myplace:String=""

        var classtimeforedit: String=""
        progressON(this)



        var mylid:Int=0
        mylid = intent.getIntExtra("mylid",1)
        Log.d("나의 아이디는","${mylid}")

        //서버연결
        myPageRequestToServer.service.classInfoRequest(
            "${myjwt}", "${mylid}"
        ).enqueue(object: Callback<ClassInfoResponse>{
            override fun onFailure(call: Call<ClassInfoResponse>, t: Throwable) {
                Log.d("통신 실패", "${t}")
                progressOFF()
            }

            override fun onResponse(
                call: Call<ClassInfoResponse>,
                response: Response<ClassInfoResponse>
            ) {
                if(response.isSuccessful){
                    if(response.body()!!.success){
                        Log.d("성공", "myinfoactivity성공")
                        Log.d(response.body()!!.data.toString(),response.body()!!.data.toString())
                        tmpcolor = response.body()!!.data!!.color
                        if(tmpcolor == "yellow")
                            my_class_tap_img_yellow.setImageResource(R.drawable.notice_color_img_yellow)
                        if(tmpcolor == "green")
                            my_class_tap_img_yellow.setImageResource(R.drawable.notice_color_img_green)
                        if(tmpcolor == "blue")
                            my_class_tap_img_yellow.setImageResource(R.drawable.notice_color_img_blue)
                        if(tmpcolor == "purple")
                            my_class_tap_img_yellow.setImageResource(R.drawable.notice_color_img_purple)
                        if(tmpcolor == "red")
                            my_class_tap_img_yellow.setImageResource(R.drawable.notice_color_img_red)

                        class_info_title.setText(response.body()!!.data!!.lectureName)
                        mycname=response.body()!!.data!!.lectureName
                        profile_name.setText(response.body()!!.data!!.userName)
                        Glide.with(this@MyinfoActivity).load(response.body()!!.data!!.profileUrl).into(my_class_tap_img_profile)
                        profile_introduce.setText(response.body()!!.data!!.intro)
                        var tmpprice:String= response.body()!!.data!!.depositCycle.toString()+"시간 / "+response.body()!!.data!!.price.toString()+"만원"
                        price.setText(tmpprice)
                        mytime=response.body()!!.data!!.depositCycle.toString()
                        mymoney=response.body()!!.data!!.price.toString()
                        var tmpaccount: String=response.body()!!.data!!.bank+" "+response.body()!!.data!!.accountNo
                        mybank=response.body()!!.data!!.bank
                        myaccount=response.body()!!.data!!.accountNo
                        account_number.setText(tmpaccount)
                        var tmpclasstime: String=""

                        var i=0
                        var schlen=response.body()!!.data!!.schedules.size
                        for (i in 0 until schlen){
                            tmpclasstime += response.body()!!.data!!.schedules[i].day + " " + response.body()!!.data!!.schedules[i].orgStartTime+" ~ "+response.body()!!.data!!.schedules[i].orgEndTime
                            classtimeforedit += response.body()!!.data!!.schedules[i].day + "*" + response.body()!!.data!!.schedules[i].orgStartTime+"~"+response.body()!!.data!!.schedules[i].orgEndTime
                            if (i != (schlen-1)){
                                tmpclasstime += "\n"
                                classtimeforedit += "-"
                            }
                        }
                        time1.setText(tmpclasstime)
                        myclasstime=tmpclasstime
                        location_info.setText(response.body()!!.data!!.orgLocation)
                        myplace=response.body()!!.data!!.orgLocation
                        progressOFF()


                    }
                    else{
                        Log.d("실패", "실패")
                        progressOFF()
                    }
                }
            }

        })



        //버튼
        my_class_tap_btn_back.setOnClickListener(object : View.OnClickListener {
            override fun onClick(arg0: View?) {
                finish()
            }
        })
        my_class_tap_btn_edit.setOnClickListener{
            if(role == "tutor"){
                val intent2= Intent(this, MyclassEdit::class.java)
                intent2.putExtra("mylid2", "${mylid.toString()}")
                intent2.putExtra("mycname", "${mycname}")
                intent2.putExtra("mycolor", "${tmpcolor}")
                intent2.putExtra("mytime", "${mytime}")
                intent2.putExtra("mymoney", "${mymoney}")
                intent2.putExtra("mybank", "${mybank}")
                intent2.putExtra("myaccount", "${myaccount}")
                intent2.putExtra("myclasstime", "${classtimeforedit}")
                intent2.putExtra("myplace", "${myplace}")
                Log.d("가는내용","가는내용${mylid}, ${mycname}, ${tmpcolor}")

                startActivity(intent2)
                finish()

            }
            else if(role == "tutee"){
                showToast("튜터만 가능한 기능입니다.")
            }
        }

        // 과외 초대
        my_class_tap_btn_invite.setOnClickListener{
            if(role == "tutor"){
                var invIntent = Intent(this, InviteActivity::class.java)
                var tmplid:Int=mylid.toInt()
                invIntent.putExtra("mylid", tmplid)
                startActivity(invIntent)
            }
            else if(role == "tutee"){
                showToast("튜터만 가능한 기능입니다.")
            }
        }

        //팝업창
        my_class_tap_btn_unconnect.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val dialog = builder.create()
            val dialogView = layoutInflater.inflate(R.layout.activity_popup_disconnect, null)
            val yes= dialogView.findViewById<ImageButton>(R.id.my_disconnect_btn_yes)
            val no= dialogView.findViewById<ImageButton>(R.id.my_disconnect_disconnect_btn_no)
            yes.setOnClickListener{
                dialog.dismiss()
            }
            no.setOnClickListener{
                myPageRequestToServer.service.classDeleteRequest(
                    "${myjwt}", "${mylid}"
                ).customEnqueue(
                    onError = { Log.d("올바르지 못한 요청입니다", "올바르지 못한 요청입니다") },
                    onSuccess = {
                        if (it.success) {
                            Log.d("삭제 완료", "삭제 완료")
                            showToast("삭제가 완료되었습니다.")
                            finish()


                        } else {
                            Log.d("삭제 실패", "삭제 실패")
                        }
                    }
                )


                //delete기능구현필요

            }

            dialog.setView(dialogView)
            dialog.show()

        }

        //클립보드 복사부분
        val textView = findViewById(R.id.account_number) as TextView //텍스트뷰
        val copybutton = findViewById<Button>(R.id.my_class_tap_btn_copy)
        val copytext = textView.text.toString() // 텍스트뷰 글자 가져옴

        copybutton.setOnTouchListener(object : View.OnTouchListener {
            //터치 이벤트 리스너 등록(누를때)
            override fun onTouch(v: View?, event: MotionEvent): Boolean {
                if (event.action == MotionEvent.ACTION_DOWN) { //눌렀을 때 동작

                    //클립보드 사용 코드
                    val clipboardManager =
                        getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clipData =
                        ClipData.newPlainText("Account", copytext) //클립보드에 Account라는 이름표로 계좌번호를 복사하여 저장
                    clipboardManager.setPrimaryClip(clipData)

                    //복사가 되었다면 토스트메시지 노출
                    Toast.makeText(this@MyinfoActivity, "계좌번호가 복사되었습니다.", Toast.LENGTH_SHORT).show()
                }
                return true
            }
        })


    }



}


