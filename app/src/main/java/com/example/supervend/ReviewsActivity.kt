package com.example.supervend

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class ReviewsActivity : AppCompatActivity() {

    private val reviewsList = ArrayList<Review>()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_reviews)
        val name =  intent.getStringExtra("name")

        // dumb hard coding :vomit:
        val 
    }
}