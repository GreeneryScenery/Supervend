package com.example.lab4.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ReviewsFragment : Fragment() {
    //private var _binding: FragmentChaptersBinding? = null
    //private val binding get() = _binding!!
    //private val chpsList = ArrayList<Chapters>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        /*_binding = FragmentChaptersBinding.inflate(inflater, container, false)
        val view: View = inflater.inflate(R.layout.fragment_chapters, null)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(activity,2)
        chpsList.add(Chapters("Chapter One", "Introduction to Android App Development", R.drawable.android_image_1))
        chpsList.add(Chapters("Chapter Two", "Android Layouts, Views and View Groups", R.drawable.android_image_2))
        chpsList.add(Chapters("Chapter Three", "Android App Building and Event Handling", R.drawable.android_image_3))
        chpsList.add(Chapters("Chapter Four", "AndroidAction Bars; Scrollable and Tabbed Layouts", R.drawable.android_image_4))
        chpsList.add(Chapters("Chapter Five", "Elements of UX in App Design", R.drawable.android_image_5))
        chpsList.add(Chapters("Chapter Six", "Intents on Fragments and Activities", R.drawable.android_image_6))
        chpsList.add(Chapters("Chapter Seven", "Android Internal System and APIs", R.drawable.android_image_7))
        chpsList.add(Chapters("Chapter Eight", "PiP, Transitions and Animations", R.drawable.android_image_8))
        val recyclerAdapter = RecyclerAdapter(chpsList)
        recyclerView.adapter = recyclerAdapter*/

        return view
    }
}