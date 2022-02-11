package com.example.recyclerviewcardview

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.view.ViewGroup
import android.widget.ImageView
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import com.example.lab4.tabs.DetailsFragment
import com.example.supervend.Item
import com.example.supervend.ItemActivity
import com.example.supervend.MainActivity
import com.example.supervend.R

class RecyclerAdapter(val itemList: ArrayList<Item>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private lateinit var descArray : Array<String>
    private lateinit var brandArray : Array<String>
    private lateinit var images : Array<Int>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_layout,parent,false)

        descArray = v.resources.getStringArray(R.array.item_descriptions)
        brandArray = v.resources.getStringArray(R.array.item_brands)
        images = MainActivity.images
        return ViewHolder(v, descArray, brandArray, images)

    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(itemList[position])
    }
    override fun getItemCount() = itemList.size

    // The class holding the list view
    class ViewHolder(itemView: View, descArray: Array<String>, brandArray: Array<String>, images: Array<Int>) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView = itemView.findViewById(R.id.item_image)
        var itemName: TextView = itemView.findViewById(R.id.item_name)
        var itemPrice: TextView = itemView.findViewById(R.id.item_price)
        var itemWeight: TextView = itemView.findViewById(R.id.item_weight)

        init {
            itemView.setOnClickListener{ view ->
                val intent = Intent(view.context, ItemActivity::class.java)
                intent.putExtra("image",images[adapterPosition])
                intent.putExtra("name", itemName.text)
                intent.putExtra("price", itemPrice.text.toString().replace("$","").toFloat())
                intent.putExtra("weight", itemWeight.text.toString().removeSuffix("g").toInt())
                intent.putExtra("brand", brandArray[adapterPosition])
                intent.putExtra("description", descArray[adapterPosition])

                ContextCompat.startActivity(view.context, intent, null)
            }
        }

        @SuppressLint("SetTextI18n")
        fun bindItems(item : Item){
            item.images?.let { itemImage.setImageResource(it) }
            itemName.text = item.name
            itemPrice.text = "$%.2f".format(item.price)
            itemWeight.text = "%dg".format(item.weight)
        }
    }
}
