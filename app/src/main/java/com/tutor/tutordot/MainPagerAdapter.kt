package com.tutor.tutordot

import android.util.Log
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.tutor.tutordot.Calendar.CalenderFragment
import com.tutor.tutordot.ClassLog.ClassLogFragment
import com.tutor.tutordot.MyPage.MyFragment
import com.tutor.tutordot.Notice.NoticeFragment
import java.util.*

class MainPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {

        return when(position){
            0 -> CalenderFragment()
            1 -> ClassLogFragment()
            2 -> NoticeFragment()
            else -> MyFragment()
        }
    }

    override fun getCount() = 4

    // 뷰페이저 프래그먼트 갱신
    override fun getItemPosition(`object`: Any): Int {

        return PagerAdapter.POSITION_UNCHANGED
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
       //super.destroyItem(container, position, `object`)
    }

}