package com.example.supervend

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.view.ViewGroup
import android.view.LayoutInflater

class Details(val itemList: ArrayList<Item>) : RecyclerView.Adapter<Details.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.details,parent,false)
        return ViewHolder(v)

    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(itemList[position])
    }
    override fun getItemCount() = itemList.size

    // The class holding the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView : TextView = itemView.findViewById(R.id.detailsView)

        fun bindItems(item : Item){
            textView.text = "Name: ${item.name}\nPrice: $${item.price}\nWeight: ${item.weight}g\nBrand: ${item.brand}\nDetails: ${item.description}"
        }
    }
}
