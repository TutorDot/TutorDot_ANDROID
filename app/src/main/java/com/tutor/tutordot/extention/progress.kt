package com.tutor.tutordot.extention

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDialog
import com.tutor.tutordot.R

private lateinit var progressDialog: AppCompatDialog

fun progressON(context: Context){

    progressDialog = AppCompatDialog(context)
    progressDialog.setCancelable(false)
    progressDialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    progressDialog.setContentView(R.layout.dialog_layout)
    progressDialog.show()
    //var img_loading_framge = progressDialog.findViewById<ImageView>(R.id.iv_frame_loading)
    //var frameAnimation = img_loading_framge?.getBackground() as AnimationDrawable
    //img_loading_framge?.post(object : Runnable{
    //    override fun run() {
    //        frameAnimation.start()
    //    }

    //})
}
fun progressOFF(){
    if(progressDialog != null && progressDialog.isShowing()){
        progressDialog.dismiss()
    }
}