package com.tutor.tutordot

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import com.bumptech.glide.Glide

class LoadingDialog
constructor(context: Context) : Dialog(context){

    init {
        setCanceledOnTouchOutside(false)

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //val loadingGif: ImageView = findViewById(R.id.iv_frame_loading) as ImageView
        //val gifImage = GlideDrawableImageViewTarget(splashGif)
        //Glide.with(context).load(R.raw.loading).into(loadingGif)


        setContentView(R.layout.dialog_layout)
    }
}