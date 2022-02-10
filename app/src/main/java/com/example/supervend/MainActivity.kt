package com.example.supervend

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.NumberPicker
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewcardview.RecyclerAdapter
import com.example.supervend.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val itemList = ArrayList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        itemList.add(Item("Milo Instant Mix", 5.2f, 250, "Nestle",
            "Milo with ACTIV-GO gives you the energy to go further. Contains b vitamins which contribute to the reduction of tiredness and fatigue.", R.drawable.milo))
        itemList.add(Item("Instant Noodles", 2.4f, 500, "Myojo",
            "Specially mixed flour for premium noodles is used to bring a chewy texture to the noodles.", R.drawable.instant_noodle))
        itemList.add(Item("Ice Cream", 25.9f, 200, "Haagen Dazs",
            "Fudgy chunks of brownie goodness mixed into dark and rich chocolate ice cream. Sounds like a dream.", R.drawable.ice_cream))
        itemList.add(Item("Beef Cubes", 4.1f, 300, "Chef Delights",
            "Made from quality meat Beef Cubes is a 100-Percent Halal product that is suitable for all consumer groups.", R.drawable.beef_cubes))
        itemList.add(Item("Fuji Apples", 5.5f, 100, "Pasar",
            "One of the sweetest apples with 16-18% sugar with a higher glucose/fructose content than most apples.\n", R.drawable.apples))
        val adapter = RecyclerAdapter(itemList)
        recyclerView.adapter = adapter

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.sign_up -> {
                val intent = Intent(this, SignUp::class.java)
                startActivity(intent)
                true
            }
            R.id.contact_information -> {
                val alertDialog: AlertDialog? = this.let {
                    val builder = AlertDialog.Builder(it)
                    val inflater = this.layoutInflater
                    val dialogView = inflater.inflate(R.layout.contact_information, null)
                    builder.setView(dialogView)
                    builder.apply {
                        setPositiveButton(R.string.ok,
                            DialogInterface.OnClickListener { dialog, id ->
                                // User clicked OK button
                            })
                        /*setNegativeButton(R.string.cancel,
                                        DialogInterface.OnClickListener { dialog, id ->
                                            // User cancelled the dialog
                                        })*/
                    }
                    // Set other dialog properties
                    builder.setMessage(R.string.contact_message)
                        ?.setTitle(R.string.contact_information)

                    // Create the AlertDialog
                    builder.create()
                }
                alertDialog?.show()
                true
            }
            R.id.description -> {
                val alertDialog: AlertDialog? = this.let {
                    val builder = AlertDialog.Builder(it)
                    val inflater = this.layoutInflater
                    val dialogView = inflater.inflate(R.layout.description, null)
                    builder.setView(dialogView)
                    builder.apply {
                        setPositiveButton(R.string.ok,
                            DialogInterface.OnClickListener { dialog, id ->
                                // User clicked OK button
                            })
                        /*setNegativeButton(R.string.cancel,
                                        DialogInterface.OnClickListener { dialog, id ->
                                            // User cancelled the dialog
                                        })*/
                    }
                    // Set other dialog properties
                    builder.setMessage(R.string.description_message)
                        ?.setTitle(R.string.description)

                    // Create the AlertDialog
                    builder.create()
                }
                alertDialog?.show()
                true
            }
            R.id.columns -> {
                val alertDialog: AlertDialog? = this?.let {
                    val builder = AlertDialog.Builder(it)
                    val inflater = this.layoutInflater
                    val dialogView = inflater.inflate(R.layout.columns, null)
                    builder.setView(dialogView)
                    val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
                    val numberPicker = dialogView.findViewById<NumberPicker>(R.id.numberPicker)
                    if (recyclerView.adapter!!.itemCount > 4) {
                        numberPicker.maxValue = 4
                    }
                    else {
                        numberPicker.maxValue = recyclerView.adapter!!.itemCount
                    }
                    numberPicker.minValue = 1
                    numberPicker.wrapSelectorWheel = false
                    builder.apply {
                        setPositiveButton(R.string.ok,
                            DialogInterface.OnClickListener { dialog, id ->
                                // User clicked OK button
                                recyclerView.layoutManager = GridLayoutManager(this.context,numberPicker.value)
                            })
                        setNegativeButton(R.string.cancel,
                            DialogInterface.OnClickListener { dialog, id ->
                                // User cancelled the dialog
                            })
                    }
                    // Set other dialog properties
                    builder?.setMessage("Select number of columns.")
                        ?.setTitle("Columns")

                    // Create the AlertDialog
                    builder.create()
                }
                alertDialog?.show()
                true
            }
            R.id.mode -> {
                val nightModeFlags: Int = this.getResources().getConfiguration().uiMode and  Configuration.UI_MODE_NIGHT_MASK
                when (nightModeFlags) {
                    Configuration.UI_MODE_NIGHT_YES -> AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO)
                    Configuration.UI_MODE_NIGHT_NO -> AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}