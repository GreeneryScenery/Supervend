package com.example.lab4.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.supervend.Item
import com.example.supervend.R
import com.example.supervend.Details
import com.example.supervend.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
    companion object {
        const val ARG_NAME = "name"
        const val ARG_PRICE = "price"
        const val ARG_WEIGHT = "weight"
        const val ARG_BRAND = "brand"
        const val ARG_DETAIL = "detail"
        const val ARG_IMAGES = "images"

        fun newInstance(name: String, price: Float, weight: Int, brand: String, detail: String, images: Int): DetailsFragment {
            val fragment = DetailsFragment()

            val bundle = Bundle().apply {
                putString(ARG_NAME, name)
                putFloat(ARG_PRICE, price)
                putInt(ARG_WEIGHT, weight)
                putString(ARG_BRAND, brand)
                putString(ARG_DETAIL, detail)
                putInt(ARG_IMAGES, images)
            }

            fragment.arguments = bundle

            return fragment
        }
    }

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val items = ArrayList<Item>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view: View = inflater.inflate(R.layout.fragment_details, null)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(activity,1)
        items.add(Item(arguments?.getString(ARG_NAME),arguments?.getFloat(ARG_PRICE),arguments?.getInt(
            ARG_WEIGHT),arguments?.getString(ARG_BRAND),arguments?.getString(ARG_DETAIL),arguments?.getInt(
            ARG_IMAGES)))
        val recyclerAdapter = Details(items)
        recyclerView.adapter = recyclerAdapter
        return view
    }
}