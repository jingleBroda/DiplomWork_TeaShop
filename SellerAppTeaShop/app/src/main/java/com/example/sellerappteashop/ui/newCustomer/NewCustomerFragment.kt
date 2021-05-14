package com.example.sellerappteashop.ui.newCustomer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sellerappteashop.R

class NewCustomerFragment : Fragment() {

    private lateinit var newCustomerViewModel: NewCustomerViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        newCustomerViewModel =
                ViewModelProvider(this).get(NewCustomerViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_new_customer, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)


        return root
    }
}