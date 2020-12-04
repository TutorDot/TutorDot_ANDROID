package com.tutor.tutordot.MyPage

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.provider.FontsContractCompat.FontRequestCallback.RESULT_OK
import androidx.recyclerview.widget.LinearLayoutManager
import com.tutor.tutordot.CalenderActivity
import com.tutor.tutordot.LoadingDialog
import com.tutor.tutordot.MyPage.MyclassEditRecylerView.MyclassEditAdapter
import com.tutor.tutordot.MyPage.MyclassEditRecylerView.MyclassEditData
import com.tutor.tutordot.MyPage.Server.*
import com.tutor.tutordot.R
import com.tutor.tutordot.Startpage.myjwt
import com.tutor.tutordot.extention.progressOFF
import com.tutor.tutordot.extention.progressON
import com.tutor.tutordot.extention.showToast
import kotlinx.android.synthetic.main.activity_myclass_edit.*
import kotlinx.android.synthetic.main.activity_mypage_addclass.*
import kotlinx.android.synthetic.main.activity_mypage_addclass.btn_plus
import kotlinx.android.synthetic.main.activity_onesentense.*
import kotlinx.android.synthetic.main.fragment_my.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import retrofit2.Call
import retrofit2.Callback
import java.util.*
import retrofit2.Response
class AddclassActivity : AppCompatActivity() {
    lateinit var myclassEditAdapter_add: MyclassEditAdapter
    val adata= mutableListOf<MyclassEditData>()
    val addata= mutableListOf<MyAddRequest>()
    private lateinit var dialog10: Dialog

    var color : String = "yellow"        //수업시간 추가


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage_addclass)

        val mypageRequestToServer = MyPageRequestToServer
        val newclassname = editTextname.text.toString()
        var allSchedule: MutableList<ScheduleData2> = mutableListOf<ScheduleData2>()
        //전체
        val constraint_no: List<ConstraintLayout> = listOf(constraint_1st, constraint_2nd, constraint_3rd, constraint_4th, constraint_5th)
        val btn_x: List<ImageButton> = listOf(btn_x1, btn_x2, btn_x3, btn_x4, btn_x5)

        //타임피커-요일
        val dayvalues = arrayOf("월", "화", "수", "목", "금", "토", "일")
        val btn_day: List<ImageButton> = listOf(btn_day1, btn_day2, btn_day3, btn_day4, btn_day5)
        val tp_day: List<NumberPicker> = listOf(tp_day1, tp_day2, tp_day3, tp_day4, tp_day5)
        var tv_weekday: List<TextView> = listOf(tp_weekday1, tp_weekday2, tp_weekday3, tp_weekday4, tp_weekday5 )

        //타임피커-시작시간
        var tmptime = mutableListOf<Int>(2, 2, 2, 2, 2)
        var tmpmin = mutableListOf<Int>(30, 30, 30, 30, 30)
        val btn_start: List<ImageButton> = listOf(btn_start1, btn_start2, btn_start3, btn_start4, btn_start5)
        val tp_myclass_time1: List<TimePicker> = listOf(tp_myclass_time_1, tp_myclass_time_2, tp_myclass_time_3, tp_myclass_time_4, tp_myclass_time_5)
        val tv_start: List<TextView> = listOf(tp_start1, tp_start2, tp_start3, tp_start4, tp_start5)

        //타임피커-끝시간
        val tv_end: List<TextView> = listOf(tp_end1, tp_end2, tp_end3, tp_end4, tp_end5)
        val tp_myclass_time2: List<NumberPicker> = listOf(tp_myclass_time2_1, tp_myclass_time2_2, tp_myclass_time2_3, tp_myclass_time2_4, tp_myclass_time2_5)
        val btn_end: List<ImageButton> =  listOf(btn_end1, btn_end2, btn_end3, btn_end4, btn_end5)
        val endtime_set: List<ConstraintLayout> = listOf(layoutthird1, layoutthird2, layoutthird3, layoutthird4, layoutthird5)
        val btn_save: List<Button> = listOf(btn_save1, btn_save2, btn_save3, btn_save4, btn_save5)



        myclassEditAdapter_add= MyclassEditAdapter(this)
        //rv_new_classtime.adapter=myclassEditAdapter_add
        val myLayoutManager = LinearLayoutManager(this)
        //rv_new_classtime.layoutManager= myLayoutManager

        //loadDatas()


        editTextname.setOnFocusChangeListener(object : View.OnFocusChangeListener {
            override fun onFocusChange(view: View, hasFocus: Boolean) {
                if (hasFocus) {
                    myScrollView.post {
                        myScrollView.fullScroll(View.FOCUS_DOWN)
                    }

                } else {
                    // 키보드 안보이게
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
                }
            }
        })

        et_bank.setOnFocusChangeListener(object : View.OnFocusChangeListener {
            override fun onFocusChange(view: View, hasFocus: Boolean) {
                if (hasFocus) {
                    myScrollView.post {
                        myScrollView.fullScroll(View.FOCUS_DOWN)
                    }

                } else {
                    // 키보드 안보이게
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
                }
            }
        })



        //취소버튼
        btn_cancel_my_add.setOnClickListener{
            finish()
        }

        //저장버튼
        //수업이름, 색상, 돈이랑 시간->NN
        btn_save_my_add.setOnClickListener{
            if (editTextname.text.toString().length>17) {
                showToast("수업명의 최대 글자수는 17글자입니다")
            }else if(editTextname.text.isNullOrBlank()){
                showToast("수업명을 설정해 주세요")
            }
            else if(et_newtime.text.isNullOrBlank() || et_newprice.text.isNullOrBlank()){
                showToast("시간별 금액을 설정해 주세요")
            }
            else{

                progressON(this)
            var i =0
            for(i in 0 until 5){
                Log.d("for문", "for문")
                if (tv_start[i].text == null) break
                if(tv_start[i].text != "00:00am"){

                    allSchedule.add(ScheduleData2(day=tv_weekday[i].text.toString(), orgStartTime = tv_start[i].text.toString(), orgEndTime = tv_end[i].text.toString()))

                }
            }
            addata.add(MyAddRequest(
                lectureName = editTextname.text.toString(),
                color = color,
                schedules = allSchedule.toList(),
                //schedules = listOf(ScheduleData2(orgStartTime = "00:00am", orgEndTime = "03:30am",day="수")),
                orgLocation = orglocation.text.toString(),
                bank = et_bank.text.toString(),
                accountNumber = editText3.text.toString(),
                totalHours = et_newtime.text.toString().toInt(),
                price = et_newprice.text.toString().toInt()
            ))
            Log.d("정보", "${addata}")






            //서버에 데이터 PUT
            mypageRequestToServer.service.myAddRequest(
                "$myjwt",addata[0]
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
                            showToast("수업 추가가 완료되었습니다.")
                            Log.d("성공", "수업 추가 성공")
                            progressOFF()
                            finish()


                        } else {
                            Log.d("실패", "수업 추가 실패")
                            progressOFF()

                        }
                    }
                }
            })

            var finishintent= Intent(this, CalenderActivity::class.java)
            //startActivity(finishintent)
            //finish()
            //finish()
            }
        }

        //컬러팔레트
        my_class_tap_img_yellow2.setOnClickListener {
            /*
            if(color.equals("yellow")) {
                my_class_tap_img_yellow2.setImageResource(R.drawable.my_class_tap_edit_img_yellow)
                color = ""
            }
            */


                color = "yellow"
                my_class_tap_img_yellow2.setImageResource(R.drawable.my_class_tap_edit_img_select_yellow)
                my_class_tap_edit_img_red2.setImageResource(R.drawable.my_class_tap_edit_img_red)
                my_class_tap_edit_img_green2.setImageResource(R.drawable.my_class_tap_edit_img_green)
                my_class_tap_edit_img_blue2.setImageResource(R.drawable.my_class_tap_edit_img_blue)
                my_class_tap_edit_img_purple2.setImageResource(R.drawable.my_class_tap_edit_img_purple)

        }
        my_class_tap_edit_img_red2.setOnClickListener {

                color = "red"
                my_class_tap_img_yellow2.setImageResource(R.drawable.my_class_tap_edit_img_yellow)
                my_class_tap_edit_img_red2.setImageResource(R.drawable.my_class_tap_edit_img_select_red)
                my_class_tap_edit_img_green2.setImageResource(R.drawable.my_class_tap_edit_img_green)
                my_class_tap_edit_img_blue2.setImageResource(R.drawable.my_class_tap_edit_img_blue)
                my_class_tap_edit_img_purple2.setImageResource(R.drawable.my_class_tap_edit_img_purple)

        }
        my_class_tap_edit_img_green2.setOnClickListener {

                color = "green"
                my_class_tap_img_yellow2.setImageResource(R.drawable.my_class_tap_edit_img_yellow)
                my_class_tap_edit_img_red2.setImageResource(R.drawable.my_class_tap_edit_img_red)
                my_class_tap_edit_img_green2.setImageResource(R.drawable.my_class_tap_edit_img_select_green)
                my_class_tap_edit_img_blue2.setImageResource(R.drawable.my_class_tap_edit_img_blue)
                my_class_tap_edit_img_purple2.setImageResource(R.drawable.my_class_tap_edit_img_purple)

        }
        my_class_tap_edit_img_blue2.setOnClickListener {

                color = "blue"
                my_class_tap_img_yellow2.setImageResource(R.drawable.my_class_tap_edit_img_yellow)
                my_class_tap_edit_img_red2.setImageResource(R.drawable.my_class_tap_edit_img_red)
                my_class_tap_edit_img_green2.setImageResource(R.drawable.my_class_tap_edit_img_green)
                my_class_tap_edit_img_blue2.setImageResource(R.drawable.my_class_tap_edit_img_select_blue)
                my_class_tap_edit_img_purple2.setImageResource(R.drawable.my_class_tap_edit_img_purple)

        }
        my_class_tap_edit_img_purple2.setOnClickListener {

                color = "purple"
                my_class_tap_img_yellow2.setImageResource(R.drawable.my_class_tap_edit_img_yellow)
                my_class_tap_edit_img_red2.setImageResource(R.drawable.my_class_tap_edit_img_red)
                my_class_tap_edit_img_green2.setImageResource(R.drawable.my_class_tap_edit_img_green)
                my_class_tap_edit_img_blue2.setImageResource(R.drawable.my_class_tap_edit_img_blue)
                my_class_tap_edit_img_purple2.setImageResource(R.drawable.my_class_tap_edit_img_select_purple)

        }




        //constraint 추가
        var cnt=1
        btn_plus.setOnClickListener{
            cnt++
            if (cnt==2){
                constraint_2nd.setVisibility(View.VISIBLE)
            }else if (cnt==3){
                constraint_3rd.setVisibility(View.VISIBLE)
            }else if (cnt==4){
                constraint_4th.setVisibility(View.VISIBLE)
            }else if (cnt==5){
                constraint_5th.setVisibility(View.VISIBLE)
            }else{
                showToast("일정의 최대개수는 5개입니다")
            }
        }
        //내부구성
        var i=0
        for(i in 0 until 5){
            //삭제버튼
            btn_x[i].setOnClickListener{constraint_no[i].setVisibility(View.GONE)}
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