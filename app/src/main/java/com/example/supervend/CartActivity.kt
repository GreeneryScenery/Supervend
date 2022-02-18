package com.example.supervend

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class CartActivity  : AppCompatActivity() {

    private var cartList = ArrayList<CartItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_cart)

        val cartView = findViewById<RecyclerView>(R.id.cartView)

        extractItems()

        cartList.add(CartItem("a",1,1,false))

        val adapter = CartAdapter(cartList)
        cartView.adapter = adapter
    }

    private fun extractItems(){
        val sp = getSharedPreferences("cart", MODE_PRIVATE)
        val numCart = sp.getInt("numCart", -1)
        for (i in 0 until numCart){
            cartList.add(CartItem(sp.getString("${i}cartName", "null"),
                sp.getInt("${i}cartAmount", -1),
                sp.getInt("${i}cartImage", -1), false))
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