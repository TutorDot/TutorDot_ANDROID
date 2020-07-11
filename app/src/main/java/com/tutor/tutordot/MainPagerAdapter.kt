package com.tutor.tutordot

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
<<<<<<< HEAD
import com.tutor.tutordot.Calendar.CalenderFragment
=======
<<<<<<< HEAD
import com.tutor.tutordot.ClassLog.ClassLogFragment
=======
import com.tutor.tutordot.MyPage.MyFragment
>>>>>>> 7d48cd557ae3f2a61419d7161353f979dc5cb834
>>>>>>> 8a4b2eed5dd8693ca5ba88a9c6f57edbdf9d774a

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