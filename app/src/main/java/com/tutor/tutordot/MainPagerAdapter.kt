package com.tutor.tutordot

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.tutor.tutordot.ClassLog.ClassLogFragment

class MainPagerAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> CalenderFragment()
            1 -> ClassLogFragment()
            2 -> NoticeFragment()
            else -> MyFragment()
        }
    }

    override fun getCount() = 4
}