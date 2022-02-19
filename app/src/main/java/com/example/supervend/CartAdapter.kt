package com.example.supervend

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.SharedPreferences
import android.opengl.Visibility
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.view.ViewGroup
import android.widget.ImageView
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.example.reorderrecyclerview.utils.ItemTouchHelperAdapter
import com.google.android.material.snackbar.Snackbar

class CartAdapter(private val cartList: ArrayList<CartItem>, private var showMenuDelete:(Boolean) -> Unit, val onSwiped : () -> Unit) : RecyclerView.Adapter<CartAdapter.CartViewHolder>(),
    ItemTouchHelperAdapter {
    companion object {
        var isEnabled = false
        private lateinit var itemSelectedList:ArrayList<Int>
    }

    init {
        isEnabled = false
        itemSelectedList = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val v : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_layout,parent,false)
        return CartViewHolder(v, cartList, showMenuDelete)

    }
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bindItems(cartList[position])
    }
    override fun getItemCount() = cartList.size

    // The class holding the list view
    class CartViewHolder(cartView: View, cartList: ArrayList<CartItem>, var showMenuDelete: (Boolean) -> Unit) : RecyclerView.ViewHolder(cartView) {
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
                        showMenuDelete(false)
                        isEnabled = false
                    }
                }
                else if (isEnabled) {
                    isEnabled = true
                    itemSelectedList.add(pos)
                    check.visibility = View.VISIBLE
                    check.setImageResource(R.drawable.check)
                    cartList[pos].selected = true
                    showMenuDelete(true)
                }
            }

            cartView.setOnLongClickListener {
                val pos = adapterPosition

                isEnabled = true
                itemSelectedList.add(pos)
                check.visibility = View.VISIBLE
                check.setImageResource(R.drawable.check)
                cartList[pos].selected = true
                showMenuDelete(true)
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

    fun delete(recyclerView: RecyclerView, sp:SharedPreferences, itemNames: Array<String>) {
        if (itemSelectedList.isNotEmpty()) {
            cartList.removeAll{item ->
                for (i in itemNames.indices) {
                    if (item.name == itemNames[i] && item.selected) {
                        val myEdit = sp.edit()
                        myEdit.putInt("${item.name}|amount", 0)
                        myEdit.apply()
                    }
                }
                item.selected
            }
            isEnabled = false
            itemSelectedList.clear()
            for (i in cartList.indices) {
                val cartView = recyclerView[i]
                val check: ImageView = cartView.findViewById(R.id.checkView)
                check.visibility = View.GONE
            }
        }
        notifyDataSetChanged()
    }

    private fun extractItems(sp:SharedPreferences, itemNames: Array<String>){
        cartList.clear()
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

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        return false
    }

    override fun onItemRemove(position: Int, sp:SharedPreferences, itemNames: Array<String>) {
        val item = cartList[position]
        for (i in itemNames.indices) {
            if (item.name == itemNames[i]) {
                val myEdit = sp.edit()
                val oldAmount = sp.getInt("${item.name}|amount", 1)
                myEdit.putInt("${item.name}|amount", oldAmount - 1)
                myEdit.apply()
            }
        }
        extractItems(sp,itemNames)
        notifyDataSetChanged()
        onSwiped()
    }

    override fun onItemAdd(position: Int, sp:SharedPreferences, itemNames: Array<String>) {
        val item = cartList[position]
        for (i in itemNames.indices) {
            if (item.name == itemNames[i]) {
                val myEdit = sp.edit()
                val oldAmount = sp.getInt("${item.name}|amount", -1)
                myEdit.putInt("${item.name}|amount", oldAmount + 1)
                myEdit.apply()
            }
        }
        extractItems(sp, itemNames)
        notifyDataSetChanged()
        onSwiped()
    }
}
