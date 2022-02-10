package com.example.recyclerviewcardview

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.view.ViewGroup
import android.widget.ImageView
import android.view.LayoutInflater
import com.example.supervend.Item
import com.example.supervend.R
import com.google.android.material.snackbar.Snackbar

class RecyclerAdapter(val itemList: ArrayList<Item>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_layout,parent,false)
        return ViewHolder(v)

    }
    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.bindItems(itemList[position])
    }
    override fun getItemCount() = itemList.size

    // The class holding the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView = itemView.findViewById(R.id.item_image)
        var itemTitle: TextView = itemView.findViewById(R.id.item_title)
        var itemPrice: TextView = itemView.findViewById(R.id.item_price)
        var itemWeight: TextView = itemView.findViewById(R.id.item_weight)

        init {
            itemView.setOnClickListener{ view ->
                val pos = adapterPosition +1
                Snackbar.make(view, "Click detected on item $pos", Snackbar.LENGTH_LONG)
                    .setAction("Action",null).show()
            }
        }

        @SuppressLint("SetTextI18n")
        fun bindItems(item : Item){
            itemImage.setImageResource(item.images)
            itemTitle.text = item.title
            itemPrice.text = "$%.2f".format(item.price)
            itemWeight.text = "%dg".format(item.weight)
        }
    }
}
