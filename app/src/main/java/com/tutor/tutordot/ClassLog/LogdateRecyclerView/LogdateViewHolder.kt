package com.tutor.tutordot.ClassLog.LogdateRecyclerView

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.tutor.tutordot.ClassLog.ClassLogModificationActivity
import com.tutor.tutordot.ClassLog.Server.LogRequestToServer
import com.tutor.tutordot.ClassLog.Server.LogResponse
import com.tutor.tutordot.ClassLog.Server.LogSomeData
import com.tutor.tutordot.ClassLog.complete
import com.tutor.tutordot.ClassLog.dd
import com.tutor.tutordot.ClassLog.mm
import com.tutor.tutordot.R
import com.tutor.tutordot.Startpage.myjwt
import com.tutor.tutordot.Startpage.role
import com.tutor.tutordot.extention.moveActi
import kotlinx.android.synthetic.main.fragment_class_log.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
var completeTmp : Int = 0

//나중에 레트로핏 보면서 수정
var ser_progress : String = "서버에서 받는 진도 데이터"
var ser_hw : String = "서버에서 받는 숙제 데이터"
var ser_date_times : Int = 1
var ser_date_studytime : Int = 1
var ser_date_alltime : Int = 15
var ser_color : String = "yellow"

var modi_check : Boolean = false


class LogdateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tv_date : TextView = itemView.findViewById<TextView>(R.id.tv_date)
    val date_layout: ConstraintLayout =itemView.findViewById<ConstraintLayout>(R.id.date_layout)

    //새로생긴거
    val tv_times = itemView.findViewById<TextView>(R.id.tv_times)
    val tv_progress = itemView.findViewById<TextView>(R.id.tv_progress)
    val tv_homework = itemView.findViewById<TextView>(R.id.tv_homework)

    val btn_circle = itemView.findViewById<ImageButton>(R.id.btn_circle)
    val btn_triangle = itemView.findViewById<ImageButton>(R.id.btn_triangle)
    val btn_x = itemView.findViewById<ImageButton>(R.id.btn_x)

    val iv_color = itemView.findViewById<ImageView>(R.id.iv_color)

    //버튼 늘림 표시
    var circle : Boolean = false
    var triangel : Boolean = false
    var x : Boolean = false

    //일지 아이템 수정
    val cl_log_item = itemView.findViewById<ConstraintLayout>(R.id.cl_log_item)


    //val rv_log : RecyclerView = itemView.findViewById<RecyclerView>(R.id.rv_log)

    //lateinit var logAdapter: LogAdapter
    //var datas : MutableList<LogData> = mutableListOf<LogData>()



    var mymon:String=""
    var myday:String=""
    var mytext:String=""
    var find = mutableSetOf("")
    //서버 연결
    fun bind(logdateSomeData : LogdateData){
        //Log.d("mytext","${mytext}")

        tv_date.text = logdateSomeData.month.toString() + "월 " + logdateSomeData.day.toString() + "일"
        mytext = tv_date.text.toString()
        Log.d("tv_date","${tv_date.text}")
        //if (!find.contains(mytext)){
        if(logdateSomeData.first==true){
            Log.d("mytext","다름")
            //mytext = tv_date.text.toString()
           // find.add(mytext)
            date_layout.visibility= ConstraintLayout.VISIBLE
        }else{
            Log.d("mytext","동일")
            date_layout.visibility= ConstraintLayout.GONE
        }



        tv_times.text = logdateSomeData.times.toString() + "회차 " + logdateSomeData.times.toString() + "시간 / " + logdateSomeData.alltime.toString() + "시간"
        tv_progress.text = "진도 : " + logdateSomeData.progress
        tv_homework.text = "숙제 : " + logdateSomeData.homework




        if (logdateSomeData.complete == 0) {
            btn_circle.setBackgroundResource(R.drawable.class_log_btn_circle_unpick)
            btn_triangle.setBackgroundResource(R.drawable.class_log_btn_triangle_unpick)
            btn_x.setBackgroundResource(R.drawable.class_log_btn_x_unpick)
        }
        if (logdateSomeData.complete == 1) {
            btn_circle.setBackgroundResource(R.drawable.class_log_btn_circle_pick)
            btn_triangle.setBackgroundResource(R.drawable.class_log_btn_triangle_unpick)
            btn_x.setBackgroundResource(R.drawable.class_log_btn_x_unpick)
        }
        if(logdateSomeData.complete == 2) {
            btn_circle.setBackgroundResource(R.drawable.class_log_btn_circle_unpick)
            btn_triangle.setBackgroundResource(R.drawable.class_log_btn_triangle_pick)
            btn_x.setBackgroundResource(R.drawable.class_log_btn_x_unpick)
        }
        if(logdateSomeData.complete == 3) {
            btn_circle.setBackgroundResource(R.drawable.class_log_btn_circle_unpick)
            btn_triangle.setBackgroundResource(R.drawable.class_log_btn_triangle_unpick)
            btn_x.setBackgroundResource(R.drawable.class_log_btn_x_pick)
        }

        if(logdateSomeData.color == "yellow")
            iv_color.setImageResource(R.drawable.notice_color_img_yellow)
        if(logdateSomeData.color == "green")
            iv_color.setImageResource(R.drawable.notice_color_img_green)
        if(logdateSomeData.color == "blue")
            iv_color.setImageResource(R.drawable.notice_color_img_blue)
        if(logdateSomeData.color == "purple")
            iv_color.setImageResource(R.drawable.notice_color_img_purple)
        if(logdateSomeData.color == "red")
            iv_color.setImageResource(R.drawable.notice_color_img_red)

        //일지 아이템 버튼 클릭 이벤트
        //튜터일때 수정 가능
        if(role == "tutor") {
            cl_log_item.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    //val context: Context = v!!.context
                    val nextIntent = Intent(v!!.context, ClassLogModificationActivity::class.java)
                    nextIntent.putExtra("diaryId", logdateSomeData.diaryId)
                    nextIntent.putExtra("mycolor", logdateSomeData.color)
                    nextIntent.putExtra("mytimes", tv_times.text.toString())
                    nextIntent.putExtra("myprogress", logdateSomeData.progress)
                    nextIntent.putExtra("myhomework", logdateSomeData.homework)
                    nextIntent.putExtra("mycomplete", logdateSomeData.complete)
                    //context.startActivity(nextIntent)
                    moveActi(nextIntent, v)

                }
            })
        }

        com.tutor.tutordot.ClassLog.LogdateRecyclerView.completeTmp = complete


     //   logAdapter = LogAdapter(itemView.context,response!!.body()!!.data.toMutableList())
     //   rv_log.adapter = logAdapter //리사이클러뷰의 어댑터를 지정해줌
    //    loadDatas() //데이터를 어댑터에 전달
    }





/*
    fun bind(logdateData : LogdateData){
        tv_date.text = logdateData.month.toString() + "월 " + logdateData.day.toString() + "일"

        logAdapter =
            LogAdapter(itemView.context)
        rv_log.adapter = logAdapter //리사이클러뷰의 어댑터를 지정해줌
        loadDatas() //데이터를 어댑터에 전달
    }
*/

    //서버 연동
    /*
    private fun loadDatas(){
        // 서버 요청
        logRequestToServer.service.logRequest("${myjwt}"
        ).enqueue(object : Callback<LogResponse> {
            override fun onFailure(call: Call<LogResponse>, t: Throwable) {
                Log.d("통신 실패", "${t}")
            }

            override fun onResponse(
                call: Call<LogResponse>,
                response: Response<LogResponse>
            ) {
                // 통신 성공
                if (response.isSuccessful) {   // statusCode가 200-300 사이일 때, 응답 body 이용 가능
                    if (response.body()!!.success) {  // 참고 코드에서 없는 부분
                        Log.d("성공", "성공")
                        Log.d(response.body()!!.data.toString(), response.body()!!.data.toString())
                        ser_date_times = response.body()!!.data[1]!!.times
                        ser_date_times = response.body()!!.data[1]!!.hour
                        ser_date_times = response.body()!!.data[1]!!.depositCycle
                        ser_progress = response.body()!!.data[1]!!.classProgress
                        ser_hw = response.body()!!.data[1]!!.homework

                        val context: Context = itemView!!.context
                        logAdapter = LogAdapter(context, response!!.body()!!.data.toMutableList())
                        logAdapter.notifyDataSetChanged()
                        rv_log.adapter = logAdapter //리사이클러뷰의 어댑터를 지정해줌

                        //데이터가 없을 경우 haveData를 false로 바꿔줌
                        if(response.body()!!.data.size == 0)
                            haveData = false
                        else
                            haveData = true

                    } else {
                        Log.d("실패", "${response.body()}")
                    }
                }
            }

        })
   }

     */
/*
    private fun loadDatas(){
        datas.apply {
            add(
                LogData(
                    color = ser_color,
                    times = ser_date_times,
                    studytime = ser_date_studytime,
                    alltime = ser_date_alltime,
                    progress = ser_progress,
                    homework = ser_hw,
                    complete = complete
                )
            )
            add(
                LogData(
                    color = "red",
                    times = 2,
                    studytime = 2,
                    alltime = 15,
                    progress = "태권도 품새 1장",
                    homework = "태극권 예습해오기",
                    complete = 2
                )
            )
            add(
                LogData(
                    color = "blue",
                    times = 3,
                    studytime = 3,
                    alltime = 20,
                    progress = "태권도 태극권 2장",
                    homework = "태극권 2장 복습하고 유튜브 영상찍기",
                    complete = 3
                )
            )
        }
        logAdapter.datas = datas
        logAdapter.notifyDataSetChanged()
    }

 */

}