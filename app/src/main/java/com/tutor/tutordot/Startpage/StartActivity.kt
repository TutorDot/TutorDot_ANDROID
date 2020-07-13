package com.tutor.tutordot.Startpage

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tutor.tutordot.ClassLog.ClassLogModificationActivity
import com.tutor.tutordot.R
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        ll_start.setOnClickListener(object :View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@StartActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        })

        btn_start.setOnClickListener(object :View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@StartActivity, SignUpActivity::class.java)
                startActivity(intent)
                finish()
            }
        })

        start_viewPager.adapter = StartAdapter(supportFragmentManager)
        start_viewPager.offscreenPageLimit = 2

        tl_start.setupWithViewPager(start_viewPager, true)

        start_viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {
                //bn_start.menu.getItem(position).isChecked = true
            }
        })
    }
}