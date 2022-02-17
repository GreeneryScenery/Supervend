package com.example.supervend

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ItemActivity : AppCompatActivity() {

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_item)

        val intent = intent
        val name = intent.getStringExtra("name")
        val price = intent.getFloatExtra("price",0f)
        val weight = intent.getIntExtra("weight", 0)
        val brand = intent.getStringExtra("brand")
        val description = intent.getStringExtra("description")
        val image = intent.getIntExtra("image",R.drawable.supervend)

        val imageView = findViewById<ImageView>(R.id.backView)
        val nameTXT = findViewById<TextView>(R.id.nameTXT)
        val priceTXT = findViewById<TextView>(R.id.priceTXT)
        val weightTXT = findViewById<TextView>(R.id.weightTXT)
        val brandTXT = findViewById<TextView>(R.id.brandTXT)
        val descriptionTXT = findViewById<TextView>(R.id.descriptionTXT)
        val addToCartBTN = findViewById<Button>(R.id.addToCartBTN)
        val reviewsFAB = findViewById<FloatingActionButton>(R.id.reviewsFAB)

        val averageRatingBar = findViewById<RatingBar>(R.id.averageRatingBar)

        imageView.setImageDrawable(ContextCompat.getDrawable(this, image))
        nameTXT.text = getString(R.string.item_name, name)
        priceTXT.text = getString(R.string.item_price, price)
        weightTXT.text = getString(R.string.item_weight, weight)
        brandTXT.text = getString(R.string.item_brand, brand)
        descriptionTXT.text = getString(R.string.item_description, description)

        addToCartBTN.setOnClickListener{ view ->
            Toast.makeText(applicationContext, "Item added to cart!", Toast.LENGTH_SHORT).show()
        }

        reviewsFAB.setOnClickListener { view ->
            val newIntent = Intent(this, ReviewsActivity::class.java)
            newIntent.putExtra("name", name)
            startActivity(newIntent)
        }

        val sp = getSharedPreferences(name, MODE_PRIVATE)
        val numReviews = sp.getInt("numReviews", -1)
        var average = 0.0f
        for (i in 0 until numReviews){
            average += sp.getFloat("${i}rating", -1f)
        }
        average /= numReviews

        averageRatingBar.rating = average
    }
}