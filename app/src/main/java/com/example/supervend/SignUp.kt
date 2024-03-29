package com.example.supervend

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.supervend.databinding.ActivityMainBinding

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)

        val usernameField = findViewById<TextView>(R.id.usernameField)
        val passwordField = findViewById<TextView>(R.id.passwordField)
        val confirmField = findViewById<TextView>(R.id.confirmField)
        val signUpBtn = findViewById<Button>(R.id.signUpBtn)

        signUpBtn.setOnClickListener {
            if (usernameField.text.isNotEmpty()) {
                if (!usernameField.text.contains(" ")) {
                    if (passwordField.text.matches(Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+\$).{8,40}\$"))) {
                        if (passwordField.text.trim().toString() == confirmField.text.trim().toString()) {

                            val builder = AlertDialog.Builder(this)
                            val diagText = "Sign up successful!"
                            builder.setTitle("Information")
                            builder.setMessage(diagText)
                            builder.setIcon(android.R.drawable.ic_dialog_info)
                            // Performing positive action
                            builder.setPositiveButton("OK"){_, _ ->
                                onBackPressed()
                            }
                            val alertDialog: AlertDialog = builder.create()
                            alertDialog.setCancelable(false)
                            alertDialog.show()
                        }
                        else {
                            val builder = AlertDialog.Builder(this)
                            val diagText = "Passwords do not match."
                            builder.setTitle(R.string.alertTitle)
                            builder.setMessage(diagText)
                            builder.setIcon(android.R.drawable.ic_dialog_alert)
                            // Performing positive action
                            builder.setPositiveButton("OK"){_, _ ->
                            }
                            val alertDialog: AlertDialog = builder.create()
                            alertDialog.setCancelable(false)
                            alertDialog.show()
                        }
                    } else {
                        val builder = AlertDialog.Builder(this)
                        val diagText = "Please check your password."
                        builder.setTitle(R.string.alertTitle)
                        builder.setMessage(diagText)
                        builder.setIcon(android.R.drawable.ic_dialog_alert)
                        // Performing positive action
                        builder.setPositiveButton("OK") { _, _ ->
                        }
                        val alertDialog: AlertDialog = builder.create()
                        alertDialog.setCancelable(false)
                        alertDialog.show()
                    }
                }
                else {
                    val builder = AlertDialog.Builder(this)
                    val diagText = "Username cannot contain spaces."
                    builder.setTitle(R.string.alertTitle)
                    builder.setMessage(diagText)
                    builder.setIcon(android.R.drawable.ic_dialog_alert)
                    // Performing positive action
                    builder.setPositiveButton("OK"){_, _ ->
                    }
                    val alertDialog: AlertDialog = builder.create()
                    alertDialog.setCancelable(false)
                    alertDialog.show()
                }
            }
            else {
                val builder = AlertDialog.Builder(this)
                val diagText = "Please fill in all fields."
                builder.setTitle(R.string.alertTitle)
                builder.setMessage(diagText)
                builder.setIcon(android.R.drawable.ic_dialog_alert)
                // Performing positive action
                builder.setPositiveButton("OK"){_, _ ->
                }
                val alertDialog: AlertDialog = builder.create()
                alertDialog.setCancelable(false)
                alertDialog.show()
            }
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val view: View? = currentFocus
        if (view != null && (ev.action == MotionEvent.ACTION_UP || ev.action == MotionEvent.ACTION_MOVE) && view is EditText && !view.javaClass
                .getName().startsWith("android.webkit.")
        ) {
            val scrcoords = IntArray(2)
            view.getLocationOnScreen(scrcoords)
            val x: Float = ev.rawX + view.getLeft() - scrcoords[0]
            val y: Float = ev.rawY + view.getTop() - scrcoords[1]
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom()) (this.getSystemService(
                INPUT_METHOD_SERVICE
            ) as InputMethodManager).hideSoftInputFromWindow(
                this.window.decorView.applicationWindowToken, 0
            )
        }
        return super.dispatchTouchEvent(ev)
    }
}