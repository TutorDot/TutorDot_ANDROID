package com.tutor.tutordot.Startpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class StartAdapter (fm:FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> StartFirstFragment()
            1 -> StartSecondFragment()
            2 -> StartThirdFragment()
            else -> StartFourFragment()
        }
    }

    override fun getCount() = 4
}