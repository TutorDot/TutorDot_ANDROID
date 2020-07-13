package com.tutor.tutordot

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
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

        bottomNavigationView2.itemIconTintList = null

        bottomNavigationView2.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.menu_calender -> main_viewPager.currentItem = 0

                R.id.menu_class_log -> main_viewPager.currentItem = 1

                R.id.menu_notice -> main_viewPager.currentItem = 2

                R.id.menu_my -> main_viewPager.currentItem = 3
            }
            true
        }

        val bottomNavigation =
            findViewById(R.id.bottomNavigationView2) as BottomNavigationView

        val menuView =
            bottomNavigation.getChildAt(0) as BottomNavigationMenuView

        for (i in 0 until menuView.childCount) {
            val iconView: View =
                menuView.getChildAt(i).findViewById(com.google.android.material.R.id.icon)
            val layoutParams: ViewGroup.LayoutParams = iconView.getLayoutParams()
            val displayMetrics = resources.displayMetrics
            layoutParams.height =
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40f, displayMetrics).toInt()
            layoutParams.width =
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 51f, displayMetrics).toInt()
            iconView.setLayoutParams(layoutParams)
        }
    }
}