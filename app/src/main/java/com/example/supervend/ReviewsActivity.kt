package com.example.supervend

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ReviewsActivity : AppCompatActivity() {

    private val reviewsList = ArrayList<Review?>()

    @SuppressLint("ResourceType", "Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reviews)

        val addFAB = findViewById<FloatingActionButton>(R.id.addFAB)

        addFAB.setOnClickListener { view ->
            val alertDialog: AlertDialog? = this.let {
                val builder = AlertDialog.Builder(it)
                val inflater = this.layoutInflater
                val dialogView = inflater.inflate(R.layout.add_review, null)
                builder.setView(dialogView)
                builder.apply {
                    setPositiveButton("Add",
                        DialogInterface.OnClickListener { dialog, id ->
                            // User clicked OK button
                        })
                    setNegativeButton(R.string.cancel,
                                    DialogInterface.OnClickListener { dialog, id ->
                                        // User cancelled the dialog
                                    })
                }
                builder.setMessage("Add a review:")
                    ?.setTitle("Add Review")
                builder.create()
            }
            alertDialog?.show()
            true
        }

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
            Log.i("reviewTAG", userNames[i] + ", " + userRatings[i] + ", " + userReviews[i])
            reviewsList.add(Review(R.drawable.anon_profile_pic, userNames[i], userRatings[i].toFloat(), userReviews[i]))
        }

        val adapter = ReviewsAdapter(this, reviewsList)
        listView.adapter = adapter
    }
}
