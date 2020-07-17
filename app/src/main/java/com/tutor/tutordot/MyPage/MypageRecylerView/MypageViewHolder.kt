package com.tutor.tutordot.MyPage.MypageRecylerView

import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tutor.tutordot.MyPage.MyinfoActivity
import com.tutor.tutordot.R
import com.tutor.tutordot.extention.moveActi

var name_class: String = ""
var haveMyData: Boolean = true

class MypageViewHolder (itemView: View) :RecyclerView.ViewHolder(itemView){
    val rv_colorcircle= itemView.findViewById<ImageView>(R.id.rv_colorcircle)
    val rv_class_name= itemView.findViewById<TextView>(R.id.rv_class_name)
    val rv_grey1= itemView.findViewById<ImageView>(R.id.rv_grey1)
    val rv_grey2= itemView.findViewById<ImageView>(R.id.rv_grey2)


    fun bind(mypageData: MypageData){
        Glide.with(itemView).load(mypageData.color).into(rv_colorcircle)
        if (mypageData.color == "yellow"){
            rv_colorcircle.setBackgroundResource(R.drawable.notice_color_img_yellow)
        }else if (mypageData.color == "red"){
            rv_colorcircle.setBackgroundResource(R.drawable.notice_color_img_red)
        }else if (mypageData.color == "green"){
            rv_colorcircle.setBackgroundResource(R.drawable.notice_color_img_green)
        }else if (mypageData.color == "blue"){
            rv_colorcircle.setBackgroundResource(R.drawable.notice_color_img_blue)
        }else {
            rv_colorcircle.setBackgroundResource(R.drawable.notice_color_img_purple)
        }

        rv_class_name.text= mypageData.content
        //Glide.with(itemView).load(mypageData.profileUrl1).into(rv_grey1)
        Glide.with(itemView).load(mypageData.profileUrl1).into(rv_grey1)
        Glide.with(itemView).load(mypageData.profileUrl2).into(rv_grey2)





        rv_class_name.setOnClickListener(object :View.OnClickListener {
            override fun onClick(mypageview: View?) {
                val pos= adapterPosition
                //val context: Context = mypageview!!.context
                val intent = Intent(mypageview!!.context, MyinfoActivity::class.java)
                //context.startActivity(intent)
                moveActi(intent, mypageview)
            }
        })
    }




}
