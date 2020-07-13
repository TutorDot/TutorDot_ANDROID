package com.tutor.tutordot.MyPage

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.tutor.tutordot.CalenderActivity
import com.tutor.tutordot.LoginActivity
import com.tutor.tutordot.R
import kotlinx.android.synthetic.main.activity_myclass_edit.*
import kotlinx.android.synthetic.main.activity_myinfo.*
import kotlinx.android.synthetic.main.fragment_my.*


class MyinfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myinfo)

        //버튼
        my_class_tap_btn_back.setOnClickListener(object : View.OnClickListener {
            override fun onClick(arg0: View?) {
                val it1 = Intent(applicationContext, CalenderActivity::class.java)
                startActivity(it1)
            }
        })
        my_class_tap_btn_edit.setOnClickListener{
            val intent2= Intent(this, MyclassEdit::class.java)
            startActivity(intent2)
            finish()
        }
        my_class_tap_btn_invite.setOnClickListener{
            var invIntent = Intent(this, InviteActivity::class.java)
            startActivity(invIntent)
        }

        //팝업창
        my_class_tap_btn_unconnect.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.activity_popup_disconnect, null)
            val yes= dialogView.findViewById<ImageButton>(R.id.my_disconnect_btn_yes)
            val no= dialogView.findViewById<ImageButton>(R.id.my_disconnect_disconnect_btn_no)
            yes.setOnClickListener{
                val intent8=Intent(this, MyinfoActivity::class.java)
                startActivity(intent8)
            }
            no.setOnClickListener{
                val intent9=Intent(this, AddclassActivity::class.java)
                startActivity(intent9)
            }
            builder.setView(dialogView)
                .show()
        }

    }



}


