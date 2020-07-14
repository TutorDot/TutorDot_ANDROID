package com.tutor.tutordot.extention

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun<ResponseType> Call<ResponseType>.customEnqueue(
    onFail : () -> Unit = { Log.d("network", "통신에 실패했습니다.")},
    onSuccess : (ResponseType) -> Unit,
    onError : () -> Unit
){
    this.enqueue(object : Callback<ResponseType> {
        override fun onFailure(call: Call<ResponseType>, t: Throwable) {
            onFail()
        }

        override fun onResponse(call: Call<ResponseType>, response: Response<ResponseType>) {
            response.body()?.let {  //body가 있다면 statusCode가 200-300 사이입니다!
                onSuccess(it)       //통신 결과를 전달해줍니다.
            } ?: onError()      //response.body()가 null값 -> statueCode가 200-300이 아닌 경우 onError()를 호출합니다.
        }
    })
}