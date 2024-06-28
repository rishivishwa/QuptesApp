package com.example.posapplication.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.posapplication.ImageFragment

class ImagePagerAdapter(fragmentManager: FragmentManager, private val images: List<String>) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return images.size
    }

    override fun getItem(position: Int): Fragment {
        return ImageFragment.newInstance(images[position])
    }
}
