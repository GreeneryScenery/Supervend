package com.example.supervend.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.supervend.tabs.DetailsFragment
import com.example.supervend.tabs.ReviewsFragment

private const val NUM_TABS = 2

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, name: String , price: Float, weight: Int, brand: String, description: String, image: Int) :
        FragmentStateAdapter(fragmentManager, lifecycle) {

    private val name = name
    private val price = price
    private val weight = weight
    private val brand = brand
    private val description = description
    private val image = image

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return DetailsFragment(name, price, weight, brand, description, image)
        }
        return ReviewsFragment()
    }
}