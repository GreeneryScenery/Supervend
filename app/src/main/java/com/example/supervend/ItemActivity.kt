package com.example.supervend

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.supervend.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ItemActivity : AppCompatActivity() {

    private val tabs = arrayOf("Details", "Reviews")
    private val icons = arrayOf(R.drawable.info,R.drawable.reviews)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item2)

        val intent = intent
        val name = intent.getStringExtra("name")
        val price = intent.getFloatExtra("price",0f)
        val weight = intent.getIntExtra("weight", 0)
        val brand = intent.getStringExtra("brand")
        val description = intent.getStringExtra("description")
        val image = intent.getIntExtra("image",R.drawable.supervend)

        val imageView = findViewById<ImageView>(R.id.backView)
        val nameTXT = findViewById<TextView>(R.id.nameTXT)
        val priceTXT = findViewById<TextView>(R.id.priceTXT)
        val weightTXT = findViewById<TextView>(R.id.weightTXT)
        val brandTXT = findViewById<TextView>(R.id.brandTXT)
        val descriptionTXT = findViewById<TextView>(R.id.descriptionTXT)

        imageView.setImageDrawable(ContextCompat.getDrawable(this, image))
        nameTXT.text = getString(R.string.item_name, name)
        priceTXT.text = getString(R.string.item_price, price)
        weightTXT.text = getString(R.string.item_weight, weight)
        brandTXT.text = getString(R.string.item_brand, brand)
        descriptionTXT.text = getString(R.string.item_description, description)

        /*val fragment = name?.let {
            if (brand != null && detail != null) {
                DetailsFragment.newInstance(name,price,weight,brand,detail,image)
            }
        }*/
        /*val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = DetailsFragment()

        val bundle = Bundle()
        bundle.putString("name",name)
        bundle.putFloat("price", price)
        bundle.putInt("weight", weight)
        bundle.putString("brand", brand)
        bundle.putString("description", description)
        bundle.putInt("image",image)
        fragment.arguments = bundle*/
        //fragmentTransaction.add(R., fragment).commit()


        // activity_item1.xml
//        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
//        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
//
//        if (name!=null&&brand!=null&&description!=null) {
//            val itemAdapter = ViewPagerAdapter(
//                supportFragmentManager,
//                lifecycle,
//                name,
//                price,
//                weight,
//                brand,
//                description,
//                image
//            )
//            viewPager.adapter = itemAdapter
//        }
//
//        TabLayoutMediator(tabLayout, viewPager){ tab, position ->
//            tab.text = tabs[position]
//            tab.setIcon(icons[position])
//        }.attach()


    }
}