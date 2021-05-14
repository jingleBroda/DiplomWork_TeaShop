package com.example.sellerappteashop.ui.personaLInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sellerappteashop.R

class PersonaLInfoFragment : Fragment() {

    private lateinit var personaLInfoViewModel: PersonaLInfoViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        personaLInfoViewModel =
                ViewModelProvider(this).get(PersonaLInfoViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_personal_info, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)


        return root
    }
}