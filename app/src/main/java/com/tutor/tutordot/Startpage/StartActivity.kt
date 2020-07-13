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

        start_viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {
                bn_start.menu.getItem(position).isChecked = true
            }
        })

        bn_start.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.start_first -> start_viewPager.currentItem = 0

                R.id.start_second -> start_viewPager.currentItem = 1

                R.id.start_third -> start_viewPager.currentItem = 2

                R.id.start_four -> start_viewPager.currentItem = 3
            }
            true
        }

        val bottomNavigation2 =
            findViewById(R.id.bn_start) as BottomNavigationView

        val menuView =
            bottomNavigation2.getChildAt(0) as BottomNavigationMenuView

        for (i in 0 until menuView.childCount) {
            val iconView: View =
                menuView.getChildAt(i).findViewById(com.google.android.material.R.id.icon)
            val layoutParams: ViewGroup.LayoutParams = iconView.getLayoutParams()
            val displayMetrics = resources.displayMetrics
            layoutParams.height =
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 9f, displayMetrics).toInt()
            layoutParams.width =
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 9f, displayMetrics).toInt()
            iconView.setLayoutParams(layoutParams)
        }
    }
}