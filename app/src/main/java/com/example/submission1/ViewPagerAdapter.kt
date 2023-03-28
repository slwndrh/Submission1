package com.example.submission1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity){
    override fun getItemCount(): Int {
        return 2
    }

    var username: String = ""
    override fun createFragment(position: Int): Fragment {
        val fragment = FollowFragment()
            fragment.arguments = Bundle().apply {
                putInt(FollowFragment.ARG_POSITION, position+1)
                putString(FollowFragment.ARG_USERNAME, username)
            }
        return fragment
    }
}