package com.tutor.tutordot.MyPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.tutor.tutordot.CalenderActivity
import com.tutor.tutordot.MyPage.MyclassEditRecylerView.MyclassEditAdapter
import com.tutor.tutordot.MyPage.MyclassEditRecylerView.MyclassEditData
import com.tutor.tutordot.MyPage.Server.MyAddRequest
import com.tutor.tutordot.MyPage.Server.MyPageRequestToServer
import com.tutor.tutordot.MyPage.Server.ProfileEditResponse
import com.tutor.tutordot.MyPage.Server.ScheduleData2
import com.tutor.tutordot.R
import com.tutor.tutordot.Startpage.myjwt
import com.tutor.tutordot.extention.progressOFF
import com.tutor.tutordot.extention.progressON
import com.tutor.tutordot.extention.showToast
import kotlinx.android.synthetic.main.activity_myclass_edit.*
import kotlinx.android.synthetic.main.activity_myclass_edit.btn_pluse
import kotlinx.android.synthetic.main.activity_mypage_addclass.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyclassEdit : AppCompatActivity() {

   // lateinit var myclassEditAdapter: MyclassEditAdapter
    val datas= mutableListOf<MyclassEditData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myclass_edit)
        var ti=0
        val mypageRequestToServer = MyPageRequestToServer

        var mylid = intent.getStringExtra("mylid2").toInt()
        var mycname = intent.getStringExtra("mycname")
        var mycolor = intent.getStringExtra("mycolor")
        var mytime = intent.getStringExtra("mytime")
        var mymoney = intent.getStringExtra("mymoney")
        var mybank = intent.getStringExtra("mybank")
        var myaccount = intent.getStringExtra("myaccount")
        var myclasstime = intent.getStringExtra("myclasstime")
        var myplace = intent.getStringExtra("myplace")
        var allSchedule: MutableList<ScheduleData2> = mutableListOf<ScheduleData2>()
        val adata= mutableListOf<MyclassEditData>()
        val addata= mutableListOf<MyAddRequest>()


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
            my_class_tap_edit_img_blue.setImageResource(R.drawable.my_class_tap_edit_img_select_blue)
        }else if (mycolor=="purple"){
            my_class_tap_edit_img_purple.setImageResource(R.drawable.my_class_tap_edit_img_select_purple)
        }

        //전체
        val constraint_no: List<ConstraintLayout> = listOf(constraint_1ste, constraint_2nde, constraint_3rde, constraint_4the, constraint_5the)
        val btn_x: List<ImageButton> = listOf(btn_x1e, btn_x2e, btn_x3e, btn_x4e, btn_x5e)


        //타임피커-요일
        val dayvalues = arrayOf("월", "화", "수", "목", "금", "토", "일")
        val btn_day: List<ImageButton> = listOf(btn_day1e, btn_day2e, btn_day3e, btn_day4e, btn_day5e)
        val tp_day: List<NumberPicker> = listOf(tp_day1e, tp_day2e, tp_day3e, tp_day4e, tp_day5e)
        var tv_weekday: List<TextView> = listOf(tp_weekday1e, tp_weekday2e, tp_weekday3e, tp_weekday4e, tp_weekday5e )

        //타임피커-시작시간
        var tmptime = mutableListOf<Int>(2, 2, 2, 2, 2)
        var tmpmin = mutableListOf<Int>(30, 30, 30, 30, 30)
        val btn_start: List<ImageButton> = listOf(btn_start1e, btn_start2e, btn_start3e, btn_start4e, btn_start5e)
        val tp_myclass_time1: List<TimePicker> = listOf(tp_myclass_time_1e, tp_myclass_time_2e, tp_myclass_time_3e, tp_myclass_time_4e, tp_myclass_time_5e)
        val tv_start: List<TextView> = listOf(tp_start1e, tp_start2e, tp_start3e, tp_start4e, tp_start5e)

        //타임피커-끝시간
        val tv_end: List<TextView> = listOf(tp_end1e, tp_end2e, tp_end3e, tp_end4e, tp_end5e)
        val tp_myclass_time2: List<NumberPicker> = listOf(tp_myclass_time2_1e, tp_myclass_time2_2e, tp_myclass_time2_3e, tp_myclass_time2_4e, tp_myclass_time2_5e)
        val btn_end: List<ImageButton> =  listOf(btn_end1e, btn_end2e, btn_end3e, btn_end4e, btn_end5e)
        val endtime_set: List<ConstraintLayout> = listOf(layoutthird1e, layoutthird2e, layoutthird3e, layoutthird4e, layoutthird5e)
        val btn_save: List<Button> = listOf(btn_save1e, btn_save2e, btn_save3e, btn_save4e, btn_save5e)

        var i=0
        if(myclasstime != null) {
        var tmptimes=myclasstime.split("-")
        ti=tmptimes.size.toInt()

            Log.d("ti!", "${ti}")
            Log.d("ti!", "${myclasstime}")
            Log.d("ti!", "${tmptimes}")
            Log.d("ti!", "${myclasstime}")
            i = 0
            for (i in 1 until ti + 1) {
                if (i == 2) {
                    constraint_2nde.setVisibility(View.VISIBLE)
                } else if (i == 3) {
                    constraint_3rde.setVisibility(View.VISIBLE)
                } else if (i == 4) {
                    constraint_4the.setVisibility(View.VISIBLE)
                } else if (i == 5) {
                    constraint_5the.setVisibility(View.VISIBLE)
                }

                var listtmp = tmptimes[i - 1].split("*")
                var listtimp2 = listtmp[1].split("~")
                tv_weekday[i - 1].text = listtmp[0]
                tv_start[i - 1].text = listtimp2[0]
                tv_end[i - 1].text = listtimp2[1]


            }
        }



        //constraint 추가
        var cnt=ti
        btn_pluse.setOnClickListener{
            cnt++
            if (cnt==2){
                constraint_2nde.setVisibility(View.VISIBLE)
            }else if (cnt==3){
                constraint_3rde.setVisibility(View.VISIBLE)
            }else if (cnt==4){
                constraint_4the.setVisibility(View.VISIBLE)
            }else if (cnt==5){
                constraint_5the.setVisibility(View.VISIBLE)
            }else{
                showToast("일정의 최대개수는 5개입니다")
            }
        }
        //내부구성

        for(i in 0 until 5){
            //삭제버튼
            btn_x[i].setOnClickListener{
                constraint_no[i].setVisibility(View.GONE)
                tv_start[i].text="00:00am"
            }
            btn_save[i].setOnClickListener { endtime_set[i].setVisibility(View.GONE) }
            //요일피커
            tp_day[i].minValue=0
            tp_day[i].maxValue=6
            tp_day[i].displayedValues= dayvalues
            btn_day[i].setOnClickListener{
                if(tp_day[i].getVisibility() == View.GONE) {
                    endtime_set[i].setVisibility(View.GONE)
                    tp_myclass_time1[i].setVisibility(View.GONE)
                    tp_day[i].setVisibility(View.VISIBLE)
                } else {
                    tp_day[i].setVisibility(View.GONE)
                }
            }
            tp_day[i].setOnValueChangedListener{
                    numberPicker, oldValue, newValue -> tv_weekday[i].text= "${dayvalues[newValue]}"
            }

            //타임피커1
            btn_start[i].setOnClickListener{
                if(tp_myclass_time1[i].getVisibility() == View.GONE) {
                    tp_myclass_time1[i].setVisibility(View.VISIBLE)
                    endtime_set[i].setVisibility(View.GONE)
                    tp_day[i].setVisibility(View.GONE)
                } else {
                    tp_myclass_time1[i].setVisibility(View.GONE)
                }
            }
            tp_myclass_time1[i].setOnTimeChangedListener{
                    view,hourOfDay,minute->
                var zero=""
                var zero2=""
                if(minute/10==0) zero="0" else zero=""
                if(getHourAMPM(hourOfDay)/10==0) zero2="0" else zero2=""
                tv_start[i].text = zero2+"${getHourAMPM(hourOfDay)}" + ":"+zero+ "$minute${getAMPM(hourOfDay)}"
                tmptime[i]=hourOfDay
                tmpmin[i]=minute
            }

            //타임피커2
            val timevalues = arrayOf("30분", "1시간", "1시간 30분", "2시간", "2시간 30분", "3시간", "3시간 30분", "4시간", "4시간 30분", "5시간")
            tp_myclass_time2[i].minValue=0
            tp_myclass_time2[i].maxValue=9
            tp_myclass_time2[i].displayedValues= timevalues
            btn_end[i].setOnClickListener{
                if(endtime_set[i].getVisibility() == View.GONE) {
                    endtime_set[i].setVisibility(View.VISIBLE)
                    tp_myclass_time1[i].setVisibility(View.GONE)
                    tp_day[i].setVisibility(View.GONE)
                } else {
                    endtime_set[i].setVisibility(View.GONE)
                }
            }
            tp_myclass_time2[i].setOnValueChangedListener{
                    numberPicker, oldValue, newValue ->
                var howlongh: Int=(newValue+1)/2
                var howlongm: Int=((newValue+1)%2)*30
                var assign=((tmpmin[i]+howlongm) % 60)/10
                var zero=""
                var zero2=""
                if( assign== 0) zero="0" else zero=""
                if(getHourAMPM(tmptime[i]+howlongh)/10 == 0) zero2="0" else zero2=""
                tv_end[i].text = zero2+"${getHourAMPM(tmptime[i]+howlongh)}" + ":"+ zero +"${(tmpmin[i]+howlongm)%60}${getAMPM(tmptime[i]+howlongh)}"

            }


        }



        //컬러팔레트 클릭
        my_class_tap_img_yellow.setOnClickListener {
            if(mycolor.equals("yellow")) {
                my_class_tap_img_yellow.setImageResource(R.drawable.my_class_tap_edit_img_yellow)
                mycolor = ""
            }
            else {
                mycolor = "yellow"
                my_class_tap_img_yellow.setImageResource(R.drawable.my_class_tap_edit_img_select_yellow)
                my_class_tap_edit_img_red.setImageResource(R.drawable.my_class_tap_edit_img_red)
                my_class_tap_edit_img_green.setImageResource(R.drawable.my_class_tap_edit_img_green)
                my_class_tap_edit_img_blue.setImageResource(R.drawable.my_class_tap_edit_img_blue)
                my_class_tap_edit_img_purple.setImageResource(R.drawable.my_class_tap_edit_img_purple)
            }
        }
        my_class_tap_edit_img_red.setOnClickListener {
            if(mycolor.equals("red")) {
                my_class_tap_edit_img_red.setImageResource(R.drawable.my_class_tap_edit_img_red)
                mycolor = ""
            }
            else {
                mycolor = "red"
                my_class_tap_img_yellow.setImageResource(R.drawable.my_class_tap_edit_img_yellow)
                my_class_tap_edit_img_red.setImageResource(R.drawable.my_class_tap_edit_img_select_red)
                my_class_tap_edit_img_green.setImageResource(R.drawable.my_class_tap_edit_img_green)
                my_class_tap_edit_img_blue.setImageResource(R.drawable.my_class_tap_edit_img_blue)
                my_class_tap_edit_img_purple.setImageResource(R.drawable.my_class_tap_edit_img_purple)
            }
        }
        my_class_tap_edit_img_green.setOnClickListener {
            if(mycolor.equals("green")) {
                my_class_tap_edit_img_green.setImageResource(R.drawable.my_class_tap_edit_img_green)
                mycolor = ""
            }
            else {
                mycolor = "green"
                my_class_tap_img_yellow.setImageResource(R.drawable.my_class_tap_edit_img_yellow)
                my_class_tap_edit_img_red.setImageResource(R.drawable.my_class_tap_edit_img_red)
                my_class_tap_edit_img_green.setImageResource(R.drawable.my_class_tap_edit_img_select_green)
                my_class_tap_edit_img_blue.setImageResource(R.drawable.my_class_tap_edit_img_blue)
                my_class_tap_edit_img_purple.setImageResource(R.drawable.my_class_tap_edit_img_purple)
            }
        }
        my_class_tap_edit_img_blue.setOnClickListener {
            if(mycolor.equals("blue")) {
                my_class_tap_edit_img_blue.setImageResource(R.drawable.my_class_tap_edit_img_blue)
                mycolor = ""
            }
            else {
                mycolor = "blue"
                my_class_tap_img_yellow.setImageResource(R.drawable.my_class_tap_edit_img_yellow)
                my_class_tap_edit_img_red.setImageResource(R.drawable.my_class_tap_edit_img_red)
                my_class_tap_edit_img_green.setImageResource(R.drawable.my_class_tap_edit_img_green)
                my_class_tap_edit_img_blue.setImageResource(R.drawable.my_class_tap_edit_img_select_blue)
                my_class_tap_edit_img_purple.setImageResource(R.drawable.my_class_tap_edit_img_purple)
            }
        }
        my_class_tap_edit_img_purple.setOnClickListener {
            if(mycolor.equals("purple")) {
                my_class_tap_edit_img_purple.setImageResource(R.drawable.my_class_tap_edit_img_purple)
                mycolor = ""
            }
            else {
                mycolor = "purple"
                my_class_tap_img_yellow.setImageResource(R.drawable.my_class_tap_edit_img_yellow)
                my_class_tap_edit_img_red.setImageResource(R.drawable.my_class_tap_edit_img_red)
                my_class_tap_edit_img_green.setImageResource(R.drawable.my_class_tap_edit_img_green)
                my_class_tap_edit_img_blue.setImageResource(R.drawable.my_class_tap_edit_img_blue)
                my_class_tap_edit_img_purple.setImageResource(R.drawable.my_class_tap_edit_img_select_purple)
            }
        }

        //myclassEditAdapter= MyclassEditAdapter(this)
        //recyclerView2.adapter=myclassEditAdapter
        val myLayoutManager = LinearLayoutManager(this)




        //취소 버튼
        btn_cancle_myedit.setOnClickListener{
            //val intent2= Intent(this, MyinfoActivity::class.java)
            //startActivity(intent2)
            finish()
        }


        //저장버튼
        btn_save_myedit.setOnClickListener{


            if (editText.text.toString().length>17) {
                showToast("수업명의 최대 글자수는 17글자입니다")
            }else{
                progressON(this)
                i =0
                for(i in 0 until 5){
                    Log.d("for문", "for문")
                    if (tv_start[i].text == null) break
                    if(tv_start[i].text != "00:00am"){

                        allSchedule.add(ScheduleData2(day=tv_weekday[i].text.toString(), orgStartTime = tv_start[i].text.toString(), orgEndTime = tv_end[i].text.toString()))

                    }
                }
                addata.add(
                    MyAddRequest(
                    lectureName = editText.text.toString(),
                    color = mycolor,
                    schedules = allSchedule.toList(),
                    //schedules = listOf(ScheduleData2(orgStartTime = "00:00am", orgEndTime = "03:30am",day="수")),
                    orgLocation = et_myplace.text.toString(),
                    bank = et_mybank.text.toString(),
                    accountNumber = editText4.text.toString(),
                    totalHours = et_mytime.text.toString().toInt(),
                    price = editText2.text.toString().toInt()
                )
                )


                //서버에 데이터 PUT
                mypageRequestToServer.service.myEditRequest(
                    "${myjwt}",addata[0],"${mylid}"
                ).enqueue(object : Callback<ProfileEditResponse> {
                    override fun onFailure(call: Call<ProfileEditResponse>, t: Throwable) {
                        Log.d("통신 실패", "수업 추가 통신 실패${t}")
                        progressOFF()
                    }

                    override fun onResponse(
                        call: Call<ProfileEditResponse>,
                        response: Response<ProfileEditResponse>
                    ) {
                        if (response.isSuccessful) {
                            if (response.body()!!.success) {
                                showToast("수업 수정이 완료되었습니다.")
                                Log.d("성공", "수업 수정 성공")
                                progressOFF()
                                finish()
                            } else {
                                Log.d("실패", "수업 수정 실패")
                                progressOFF()
                            }
                        }
                    }
                })

                }
        }







    }
    // Custom method to get AM PM value from provided hour
    private fun getAMPM(hour:Int):String{
        return if(hour>11)"pm" else "am"
    }

    // Custom method to get hour for AM PM time format
    private fun getHourAMPM(hour:Int):Int{
        // Return the hour value for AM PM time format
        var modifiedHour = if (hour>11)hour-12 else hour
        if (modifiedHour == 0){modifiedHour = 12}
        return modifiedHour
    }

}