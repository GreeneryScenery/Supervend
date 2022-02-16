package com.example.supervend

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.WindowManager
import android.widget.ListView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.supervend.MainActivity.Companion.reviews
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ReviewsActivity : AppCompatActivity() {

    private var reviewsList = ArrayList<Review?>()

    @SuppressLint("ResourceType", "Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getWindow().setFlags(
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
                    setPositiveButton("Add",
                        DialogInterface.OnClickListener { dialog, id ->
                            // User clicked OK button
                            if (commentView.text.isNotEmpty() && !commentView.text.contains(" ")) {
                                reviewsList.add(
                                    Review(
                                        R.drawable.anonymous,
                                        "Anonymous (You)",
                                        ratingBar.rating,
                                        commentView.text.toString()
                                    )
                                )
                            }
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
        val name = intent.getStringExtra("name")
        val itemNames = resources.getStringArray(R.array.item_names)

        when (name) {
            itemNames[0] -> reviewsList = reviews[0]
            itemNames[1] -> reviewsList = reviews[1]
            itemNames[2] -> reviewsList = reviews[2]
            itemNames[3] -> reviewsList = reviews[3]
            itemNames[4] -> reviewsList = reviews[4]
        }

        val adapter = ReviewsAdapter(this, reviewsList)
        listView.adapter = adapter
    }
}
