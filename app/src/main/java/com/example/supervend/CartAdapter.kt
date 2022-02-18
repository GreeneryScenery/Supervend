package com.example.supervend

import android.opengl.Visibility
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.view.ViewGroup
import android.widget.ImageView
import android.view.LayoutInflater
import com.google.android.material.snackbar.Snackbar

class CartAdapter(val cartList: ArrayList<CartItem>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val v : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_layout,parent,false)
        return CartViewHolder(v, cartList)

    }
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bindItems(cartList[position])
    }
    override fun getItemCount() = cartList.size

    // The class holding the list view
    class CartViewHolder(cartView: View, cartList: ArrayList<CartItem>) : RecyclerView.ViewHolder(cartView) {
        var cartImage: ImageView
        var check: ImageView
        var cartName: TextView
        var cartAmount: TextView

        init {
            cartImage = cartView.findViewById(R.id.cartView)
            check = cartView.findViewById(R.id.checkView)
            cartName = cartView.findViewById(R.id.cartName)
            cartAmount = cartView.findViewById(R.id.cartAmount)

            check.visibility = View.GONE

            cartView.setOnClickListener{ view ->
                val pos = adapterPosition +1

                if (cartList[pos].selected) {
                    check.visibility = View.GONE
                }
                else {
                    check.visibility = View.VISIBLE
                    check.setImageResource(R.drawable.check)
                }
            }

            cartView.setOnLongClickListener {
                val pos = adapterPosition +1
                check.visibility = View.VISIBLE
                check.setImageResource(R.drawable.check)
                cartList[pos].selected = true
                true
            }
        }

        fun bindItems(cartItem: CartItem){
            cartName.text = cartItem.name
            cartAmount.text = cartItem.amount.toString()
            cartImage.setImageResource(cartItem.image)
        }
    }
}
