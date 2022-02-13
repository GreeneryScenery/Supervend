package com.example.supervend

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ReviewsActivity : AppCompatActivity() {

    private val reviewsList = ArrayList<Review?>()

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_reviews)
        val listView = findViewById<ListView>(R.id.listView)
        val name = intent.getStringExtra("name")
        val itemNames = resources.getStringArray(R.array.item_names)

        val itemReview = when (name) {
            itemNames[0] -> resources.obtainTypedArray(R.array.milo_instant_mix)
            itemNames[1] -> resources.obtainTypedArray(R.array.instant_noodles)
            itemNames[2] -> resources.obtainTypedArray(R.array.ice_cream)
            itemNames[3] -> resources.obtainTypedArray(R.array.beef_cubes)
            itemNames[4] -> resources.obtainTypedArray(R.array.fuji_apples)
            else -> null
        }

        val userNames = resources.getStringArray(itemReview!!.getResourceId(0, -1))
        val userReviews = resources.getStringArray(itemReview.getResourceId(1, -1))
        val userRatings = resources.getStringArray(itemReview.getResourceId(2, -1))

        for (i in userNames.indices){
            reviewsList.add(Review(R.drawable.anon_profile_pic, userNames[i], userRatings[i].toFloat(), userReviews[i]))
            Toast.makeText(applicationContext, "$i", Toast.LENGTH_SHORT).show()
        }

        val adapter = ReviewsAdapter(applicationContext, reviewsList)
        listView.adapter = adapter
    }
}
