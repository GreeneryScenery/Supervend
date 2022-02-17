package com.example.supervend

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.WindowManager
import android.widget.ListView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ReviewsActivity : AppCompatActivity() {

    private var reviewsList = ArrayList<Review?>()
    private lateinit var itemName: String

    @SuppressLint("ResourceType", "Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemName = intent.getStringExtra("name").toString()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_reviews)

        val addFAB = findViewById<FloatingActionButton>(R.id.addFAB)

        var commentView:TextView

        addFAB.setOnClickListener { view ->
            val alertDialog: AlertDialog? = this.let {
                val builder = AlertDialog.Builder(it)
                val inflater = this.layoutInflater
                val dialogView = inflater.inflate(R.layout.add_review, null)
                builder.setView(dialogView)

                commentView = dialogView.findViewById<TextView>(R.id.commentView)
                val ratingBar = dialogView.findViewById<RatingBar>(R.id.ratingBar)

                builder.apply {
                    setPositiveButton("Add") { dialog, id ->
                        // User clicked OK button
                        if (commentView.text.isNotEmpty()) {
                            val newReview = Review(
                                R.drawable.anonymous,
                                "Anonymous (You)",
                                ratingBar.rating,
                                commentView.text.toString()
                            )
                            Log.i("revAct", commentView.text.toString())
                            reviewsList.add(newReview)
                            addReviewToSharedPref(itemName, newReview)
                        }
                    }
                    setNegativeButton(R.string.cancel) { dialog, id ->
                        // User cancelled the dialog
                    }
                }
                builder.setMessage("Add a review:")
                    ?.setTitle("Add Review")
                builder.create()
            }
            alertDialog?.show()
            alertDialog?.getButton(AlertDialog.BUTTON_POSITIVE)?.isEnabled = false
            commentView.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {

                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    alertDialog?.getButton(AlertDialog.BUTTON_POSITIVE)?.isEnabled = commentView.text.isNotEmpty() && commentView.text.toString()
                        .trim().isNotEmpty()
                }
            })
        }

        val listView = findViewById<ListView>(R.id.listView)

        extractReviews(itemName)

        val adapter = ReviewsAdapter(this, reviewsList)
        listView.adapter = adapter
    }

    private fun extractReviews(itemName: String?){
        val sp = getSharedPreferences(itemName, MODE_PRIVATE)
        val numReviews = sp.getInt("numReviews", -1)
        for (i in 0 until numReviews){
            reviewsList.add(Review(sp.getInt("${i}image", -1),
                sp.getString("${i}name", "null"),
                sp.getFloat("${i}rating", -1f),
                sp.getString("${i}review", "null")))
            Log.i("revAct", sp.getString("${i}review", "null").toString())
        }
    }

    private fun addReviewToSharedPref(itemName: String, review: Review) {
        // Storing data into SharedPreferences
        val sp = getSharedPreferences(itemName, MODE_PRIVATE)
        val reviewIndex = sp.getInt("numReviews", 0)
        // Creating an Editor object to edit(write to the file)
        val myEdit = sp.edit()

        // Storing the key and its value as the data fetched from edittext
        myEdit.putInt("${reviewIndex}image", review.image)
        myEdit.putString("${reviewIndex}name", review.name)
        myEdit.putFloat("${reviewIndex}rating", review.rating)
        myEdit.putString("${reviewIndex}review", review.review)

        // Once the changes have been made,
        // we need to commit to apply those changes made,
        // otherwise, it will throw an error
        myEdit.putInt("numReviews", reviewIndex + 1)
        myEdit.apply()
    }
}
