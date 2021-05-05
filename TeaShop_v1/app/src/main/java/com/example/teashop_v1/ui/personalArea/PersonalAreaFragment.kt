package com.example.teashop_v1.ui.personalArea


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.teashop_v1.R


class PersonalAreaFragment : Fragment() {
    private lateinit var personalAreaViewModel: PersonalAreaViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        personalAreaViewModel = ViewModelProvider(this).get(PersonalAreaViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_personal_area_screen, container, false)

        /*
        //проба принятия текста
        //дубль 1
        val txtView = root.findViewById<TextView>(R.id.numberCard)

        if(arguments?.getString("str") !=null){
            val strToAssortiScreen = arguments?.getString("str")
            txtView.setText(strToAssortiScreen)

        }
        */
        

        return root
    }
}