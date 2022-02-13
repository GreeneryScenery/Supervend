package com.example.supervend

import android.R.attr
import android.app.AlertDialog
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.supervend.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import android.R.attr.label
import android.content.*


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val itemList = ArrayList<Item>()

    companion object {
        @JvmStatic lateinit var images:Array<Int>
    }

    init {
        images= arrayOf(R.drawable.milo,R.drawable.instant_noodle,R.drawable.ice_cream,R.drawable.beef_cubes,R.drawable.apples)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        val nameArray = resources.getStringArray(R.array.item_names)
        val priceArray = resources.getStringArray(R.array.item_prices)
        val weightArray = resources.getStringArray(R.array.item_weights)
        val brandArray = resources.getStringArray(R.array.item_brands)
        val descArray = resources.getStringArray(R.array.item_descriptions)
        for (i in nameArray.indices) {
            itemList.add(
                Item(
                    nameArray[i],
                    priceArray[i].toFloat(),
                    weightArray[i].toInt(),
                    brandArray[i],
                    descArray[i],
                    images[i]
                )
            )
        }
        val adapter = RecyclerAdapter(itemList)
        recyclerView.adapter = adapter

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Opening Shopping Cart", Snackbar.LENGTH_LONG)
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
                val phoneView = findViewById<TextView>(R.id.phoneView)
                val emailView = findViewById<TextView>(R.id.emailView)
                phoneView.setOnClickListener {
                    val clipboard: ClipboardManager =
                        getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clip = ClipData.newPlainText("SuperVend Phone Number", phoneView.text.toString())
                    clipboard.setPrimaryClip(clip)
                }
                emailView.setOnClickListener {
                    val clipboard: ClipboardManager =
                        getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clip = ClipData.newPlainText("SuperVend Email", emailView.text.toString())
                    clipboard.setPrimaryClip(clip)
                }
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