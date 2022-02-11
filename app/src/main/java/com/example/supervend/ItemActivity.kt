package com.example.supervend

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.lab4.adapter.ViewPagerAdapter
import com.example.lab4.tabs.DetailsFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ItemActivity : AppCompatActivity() {

    private val tabs = arrayOf("Details", "Reviews")
    private val icons = arrayOf(R.drawable.info,R.drawable.reviews)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        val intent = intent
        val name = intent.getStringExtra("name")
        val price = intent.getFloatExtra("price",0f)
        val weight = intent.getIntExtra("weight", 0)
        val brand = intent.getStringExtra("brand")
        val detail = intent.getStringExtra("detail")
        val image = intent.getIntExtra("image",R.drawable.supervend)
        val imageView = findViewById<ImageView>(R.id.backView)
        imageView.setImageDrawable(ContextCompat.getDrawable(this, image))

        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)

        val itemAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = itemAdapter

        val fragment = name?.let {
            if (brand != null && detail != null) {
                DetailsFragment.newInstance(it,price,weight,brand,detail,image)
            }
        }

        TabLayoutMediator(tabLayout, viewPager){ tab, position ->
            tab.text = tabs[position]
            tab.setIcon(icons[position])
        }.attach()
    }
}