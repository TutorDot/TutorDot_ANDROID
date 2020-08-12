package com.tutor.tutordot.ClassLog.LogdateRecyclerView

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tutor.tutordot.ClassLog.LogRecyclerView.LogAdapter
import com.tutor.tutordot.ClassLog.LogRecyclerView.LogData
import com.tutor.tutordot.ClassLog.Server.LogRequestToServer
import com.tutor.tutordot.ClassLog.Server.LogResponse
import com.tutor.tutordot.ClassLog.Server.LogSomeData
import com.tutor.tutordot.ClassLog.dd
import com.tutor.tutordot.ClassLog.mm
import com.tutor.tutordot.R
import com.tutor.tutordot.Startpage.myjwt
import kotlinx.android.synthetic.main.fragment_class_log.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


//나중에 레트로핏 보면서 수정
var ser_progress : String = "서버에서 받는 진도 데이터"
var ser_hw : String = "서버에서 받는 숙제 데이터"
var ser_date_times : Int = 1
var ser_date_studytime : Int = 1
var ser_date_alltime : Int = 15
var ser_color : String = "yellow"

var modi_check : Boolean = false
var haveData : Boolean = true
class LogdateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tv_date : TextView = itemView.findViewById<TextView>(R.id.tv_date)
    val rv_log : RecyclerView = itemView.findViewById<RecyclerView>(R.id.rv_log)

    lateinit var logAdapter: LogAdapter
    var datas : MutableList<LogData> = mutableListOf<LogData>()



    //서버 연결
    val logRequestToServer = LogRequestToServer

    var mymon:String=""
    var myday:String=""
    var mytext:String=""
    //서버 연결
    fun bind(logdateSomeData : LogdateData){

        //var month : String = logdateSomeData.classDate.slice(IntRange(5,6))
        //var day : String = logdateSomeData.classDate.slice(IntRange(8,9))
            tv_date.text = logdateSomeData.month.toString() + "월 " + logdateSomeData.day.toString() + "일"
            datas = mutableListOf<LogData>()

            // 서버 요청
            logRequestToServer.service.logRequest(
                "${myjwt}"
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
                            Log.d("data확인", response.body()!!.data.toString())
                            // ser_date_times = response.body()!!.data[1]!!.times
                            // ser_date_studytime = response.body()!!.data[1]!!.hour
                            // ser_date_alltime = response.body()!!.data[1]!!.depositCycle
                            // ser_progress = response.body()!!.data[1]!!.classProgress
                            // ser_hw = response.body()!!.data[1]!!.homework


                            //if (mytext != tv_date.text.toString()) {
                            mytext = tv_date.text.toString()
                            //Log.d("mytext:","${mytext}")
                            var i = 0
                            for (i in 0 until response.body()!!.data.size) {
                                //var month : String = response.body()!!.data[i].classDate.slice(IntRange(5,6))
                                //var day : String = response.body()!!.data[i].classDate.slice(IntRange(8,8))
                                var tmp = response.body()!!.data[i].classDate.split("-")
                                var tmptext = "${tmp[1].toInt()}" + "월 " + "${tmp[2].toInt()}" + "일"

                                //Log.d("classDate:",response.body()!!.data[i].classDate)
                                Log.d("mytext:", "${mytext}")
                                Log.d("tmptext", "${tmptext}")
                                if (tmptext == mytext) {
                                    Log.d("동일", "동일")
                                    datas.apply {
                                        add(
                                            LogData(
                                                color = response.body()!!.data[i].color,
                                                times = response.body()!!.data[i].times,
                                                studytime = response.body()!!.data[i].hour,
                                                alltime = response.body()!!.data[i].depositCycle,
                                                progress = response.body()!!.data[i].classProgress,
                                                homework = response.body()!!.data[i].homework,
                                                complete = response.body()!!.data[i].hwPerformance
                                            )
                                        )
                                    }
                                }

                            }
                            //}


                            val context: Context = itemView!!.context
                            //logAdapter = LogAdapter(context, response!!.body()!!.data.toMutableList())
                            //logAdapter.notifyDataSetChanged()
                            //rv_log.adapter = logAdapter //리사이클러뷰의 어댑터를 지정해줌

                            // logdateAdapter = LogdateAdapter(getActivity()!!.getApplicationContext(), response!!.body()!!.data)
                            logAdapter = LogAdapter(context, datas)
                            rv_log.adapter = logAdapter
                            logAdapter.datas = datas
                            logAdapter.notifyDataSetChanged()
                            // rv_datelog.adapter = logdateAdapter


                        } else {
                            Log.d("실패", "${response.body()}")
                        }
                    }
                }

            })


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