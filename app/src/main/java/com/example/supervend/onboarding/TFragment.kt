package com.example.onboarding.onboardingFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.supervend.MainActivity
import com.example.supervend.R

class TFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.third_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()
        val button = requireView().findViewById<Button>(R.id.button)
        button.setOnClickListener{
            val intent = Intent(this.context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}