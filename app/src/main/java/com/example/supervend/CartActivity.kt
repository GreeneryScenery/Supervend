package com.example.supervend

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.supervend.swipeToDismiss.SwipeHelperCallback

class CartActivity  : AppCompatActivity() {

    private var menu: Menu? = null
    private var cartList = ArrayList<CartItem>()
    private lateinit var cartView:RecyclerView
    private lateinit var adapter:CartAdapter

    private var mItemTouchHelper: ItemTouchHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_cart)

        extractItems()

        val sp = getSharedPreferences("cart", MODE_PRIVATE)
        val itemNames = resources.getStringArray(R.array.item_names)

        val empty = findViewById<TextView>(R.id.empty)

        cartView = findViewById(R.id.cartRecycle)
        val layoutManager = GridLayoutManager(this, 1)
        adapter = CartAdapter(cartView,cartList,{show -> showDeleteMenu(show)},{},empty)
        val callback: ItemTouchHelper.Callback = SwipeHelperCallback(adapter, sp, itemNames)
        mItemTouchHelper = ItemTouchHelper(callback)
        mItemTouchHelper?.attachToRecyclerView(findViewById(R.id.cartRecycle))
        cartView.layoutManager = layoutManager
        cartView.adapter = adapter

        if (cartList.isNotEmpty()) {
            empty.visibility = View.GONE
        }
        else {
            empty.visibility = View.VISIBLE
        }
    }

    private fun extractItems(){
        val sp = getSharedPreferences("cart", MODE_PRIVATE)
        val itemNames = resources.getStringArray(R.array.item_names)
        for (i in itemNames){
            val amount = sp.getInt("${i}|amount", 0)
            val image = sp.getInt("${i}|image", -1)
            Log.i("CartItem", "${i}, ${amount}")
            if (amount==0){
                continue
            }
            cartList.add(CartItem(i, amount, image, false))
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

    fun showDeleteMenu(show: Boolean) {
        menu?.findItem(R.id.delete)?.isVisible = show
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu
        menuInflater.inflate(R.menu.cart_menu, menu)
        showDeleteMenu(false)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val sp = getSharedPreferences("cart", MODE_PRIVATE)
        val itemNames = resources.getStringArray(R.array.item_names)
        return when (item.itemId) {
            R.id.delete -> {
                adapter.delete(cartView,sp, itemNames)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}