package com.tutor.tutordot.MyPage.MypageRecylerView

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tutor.tutordot.MyPage.MyinfoActivity
import com.tutor.tutordot.R
import android.widget.Toast.makeText as makeText1



class MypageViewHolder (itemView: View) :RecyclerView.ViewHolder(itemView){
    val rv_colorcircle= itemView.findViewById<ImageView>(R.id.rv_colorcircle)
    val rv_class_name= itemView.findViewById<TextView>(R.id.rv_class_name)

    fun bind(mypageData: MypageData){
        Glide.with(itemView).load(mypageData.color).into(rv_colorcircle)
        rv_class_name.text= mypageData.content



        rv_class_name.setOnClickListener(object :View.OnClickListener {
            override fun onClick(mypageview: View?) {
                val pos= adapterPosition
                val context: Context = mypageview!!.context
                val Intent = Intent(mypageview!!.context, MyinfoActivity::class.java)
                context.startActivity(Intent)
                (context as Activity).finish()


            }
        })
    }




}