package com.tutor.tutordot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_calender.*

class CalenderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calender)

        main_viewPager.adapter = MainPagerAdapter(supportFragmentManager)
        main_viewPager.offscreenPageLimit = 2

        main_viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {
                bottomNavigationView2.menu.getItem(position).isChecked = true
            }
        })



        bottomNavigationView2.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.menu_calender -> main_viewPager.currentItem = 0

                R.id.menu_class_log -> main_viewPager.currentItem = 1

                R.id.menu_notice -> main_viewPager.currentItem = 2

                R.id.menu_my -> main_viewPager.currentItem = 3
            }
            true
        }
    }
}