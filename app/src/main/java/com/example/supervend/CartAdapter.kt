package com.example.supervend

import android.annotation.SuppressLint
import android.opengl.Visibility
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.view.ViewGroup
import android.widget.ImageView
import android.view.LayoutInflater
import com.google.android.material.snackbar.Snackbar

class CartAdapter(private val cartList: ArrayList<CartItem>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    companion object {
        var isEnabled = false
        private lateinit var itemSelectedList:ArrayList<Int>
        var showMenuDelete = false
    }

    init {
        isEnabled = false
        itemSelectedList = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val v : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_layout,parent,false)
        return CartViewHolder(v, cartList)

    }
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bindItems(cartList[position])
    }
    override fun getItemCount() = cartList.size

    // The class holding the list view
    class CartViewHolder(cartView: View, cartList: ArrayList<CartItem>) : RecyclerView.ViewHolder(cartView) {
        var cartImage: ImageView = cartView.findViewById(R.id.cartView)
        private var check: ImageView = cartView.findViewById(R.id.checkView)
        private var cartName: TextView = cartView.findViewById(R.id.cartName)
        var cartAmount: TextView = cartView.findViewById(R.id.cartAmount)

        init {

            check.visibility = View.GONE

            cartView.setOnClickListener{ view ->
                val pos = adapterPosition

                if (itemSelectedList.contains(pos)) {
                    itemSelectedList.remove(pos)
                    check.visibility = View.GONE
                    cartList[pos].selected = false
                    if (itemSelectedList.isEmpty()) {
                        showMenuDelete = false
                        isEnabled = false
                    }
                }
                else if (isEnabled) {
                    isEnabled = true
                    itemSelectedList.add(pos)
                    check.visibility = View.VISIBLE
                    check.setImageResource(R.drawable.check)
                    cartList[pos].selected = true
                    showMenuDelete = true
                }
            }

            cartView.setOnLongClickListener {
                val pos = adapterPosition

                isEnabled = true
                itemSelectedList.add(pos)
                check.visibility = View.VISIBLE
                check.setImageResource(R.drawable.check)
                cartList[pos].selected = true
                showMenuDelete = true
                true
            }
        }

        @SuppressLint("SetTextI18n")
        fun bindItems(cartItem: CartItem){
            cartName.text = cartItem.name
            cartAmount.text = "Amount: ${cartItem.amount}"
            cartItem.image?.let { cartImage.setImageResource(it) }
        }
    }
}
