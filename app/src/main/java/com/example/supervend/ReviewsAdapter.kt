package com.example.supervend

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RatingBar
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView
import java.util.ArrayList

class ReviewsAdapter(context: Context, userArrayList: ArrayList<Review?>) :
    ArrayAdapter<Review?>(context, R.layout.list_review_item, userArrayList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val review: Review? = getItem(position)
        var convertView = convertView
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_review_item, parent, false)
        }
        val imageView = convertView!!.findViewById<CircleImageView>(R.id.profile_pic)
        val userName = convertView.findViewById<TextView>(R.id.nameTXT)
        val ratingBar = convertView.findViewById<RatingBar>(R.id.reviewRatingBar)
        val reviewMsg = convertView.findViewById<TextView>(R.id.reviewTXT)
        if (review != null) {
            imageView.setImageResource(review.image)
            userName.text = review.name
            ratingBar.rating = review.rating
            reviewMsg.text = review.review
        }
        return convertView
    }
}