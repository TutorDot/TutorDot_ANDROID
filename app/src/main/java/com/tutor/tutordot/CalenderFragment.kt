package com.tutor.tutordot

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.tutor.tutordot.R
import kotlinx.android.synthetic.main.fragment_calender.*

/**
 * A simple [Fragment] subclass.
 * Use the [CalenderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CalenderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        button.setOnClickListener {
//            val intent = Intent(getActivity(), ScheduleAddActivity::class.java)
//            startActivity(intent)
//        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calender, container, false)
    }
}