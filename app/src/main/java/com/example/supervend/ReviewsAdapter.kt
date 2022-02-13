package com.example.supervend

import android.widget.ArrayAdapter
import android.R
import android.content.Context

import android.widget.TextView

import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.ListAdapter

import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel


class ReviewsAdapter(val viewModel: ViewModel) : ListAdapter<Review, ReviewsAdapter.ViewHolder>(){
    override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
        var convertView: View = convertView
        val review: Review? = getItem(position)
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }
        val imageView: ImageView = convertView.findViewById(R.id.profile_pic)
        val userName: TextView = convertView.findViewById(R.id.personName)
        val lastMsg: TextView = convertView.findViewById(R.id.lastMessage)
        val time: TextView = convertView.findViewById(R.id.msgtime)
        imageView.setImageResource(user.imageId)
        userName.setText(user.name)
        lastMsg.setText(user.lastMessage)
        time.setText(user.lastMsgTime)
        return convertView
    }
}