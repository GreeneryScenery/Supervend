package com.example.supervend

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class ItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        val intent = intent
        val image = intent.getIntExtra("image",R.drawable.supervend)
        val imageView = findViewById<ImageView>(R.id.backView)
        imageView.setImageDrawable(ContextCompat.getDrawable(this, image));
    }
}