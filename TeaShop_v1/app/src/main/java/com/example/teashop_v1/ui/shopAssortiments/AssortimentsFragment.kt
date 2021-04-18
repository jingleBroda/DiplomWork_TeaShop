package com.example.teashop_v1.ui.shopAssortiments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.teashop_v1.R

class AssortimentsFragment : Fragment() {

    private lateinit var assortimentsViewModel: AssortimentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        assortimentsViewModel =
            ViewModelProvider(this).get(AssortimentsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_assortiments_screen, container, false)
        val textView: TextView = root.findViewById(R.id.text_assortiments)
        assortimentsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}