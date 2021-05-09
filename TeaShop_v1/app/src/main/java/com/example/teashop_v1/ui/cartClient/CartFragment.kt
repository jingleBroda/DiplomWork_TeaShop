  package com.example.teashop_v1.ui.cartClient

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teashop_v1.AdapterForCartScreen
import com.example.teashop_v1.ListIteamCart
import com.example.teashop_v1.R


class CartFragment : Fragment() {

    var preff :SharedPreferences? = null

    private lateinit var cartViewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_cart_screen, container, false)


        //считываем собранный формат сохранненых позиций товаров
        preff = activity?.getSharedPreferences("ArrayCartTable", Context.MODE_PRIVATE)
        var arrayToStringCartItem:String = preff?.getString("ArrayCart", "Tyt Pusto")!!
        var testTXT: TextView = root.findViewById(R.id.TestTextView)
        testTXT.text = arrayToStringCartItem

        //переводим полученню строку в массив выбранных товаров
        var list =ArrayList<ListIteamCart>()
        if (arrayToStringCartItem != "Tyt Pusto") {
            //тут добавляем лист полченных стрингови формирем его с помощью написанной функции decryptionArrayToStringCartItem
            var nameAssortiCart = decryptionArrayToStringCartItem(arrayToStringCartItem)

            //устонавливаем выбранные ячейки товаров
            for (i in nameAssortiCart){
                list.add(ListIteamCart(
                        i, //это передача i в параметр name_id
                        "500гр",
                        "1",
                        "390Р",
                        R.drawable.test_avatar,
                        R.drawable.img_plus,
                        R.drawable.img_minus
                )
                )
            }
        }
        /*
        //при пустой корзине пока будет тестовая ячейка
        else{

            list.add(ListIteamCart(
                    "опа, ничего не выбрано", //это передача i в параметр name_id
                    "какая жалость",
                    "0",
                    "0Р",
                    R.drawable.test_avatar,
                    R.drawable.img_plus,
                    R.drawable.img_minus
            )
            )

        }
        */

        val myRecyclerView: RecyclerView =root.findViewById(R.id.rcViewCart)

        //ДЛЯ КОРРЕКТНОГО ОТОБРАЖЕНИЯ RECYCLERVIEW добавляем следющие атрибуты:
        //1.фиксируем размер
        myRecyclerView.hasFixedSize()
        //2.устанавливаем контекс лайаут менеджеру
        myRecyclerView.layoutManager= LinearLayoutManager(activity)

        //устанавливаем кастомный адаптер для экрана с ассортиментами нашему RecyclerView
        myRecyclerView.adapter= AdapterForCartScreen(list,requireContext())



        return root
    }

    fun decryptionArrayToStringCartItem(arrayToString:String):ArrayList<String>{

        var result = ArrayList<String>()
        var tmpString:String=""

        for (i in arrayToString.toCharArray())
        {
            if (i != '|'){
                tmpString += i.toString()
            }
            else {
                result.add(tmpString)
                tmpString=""
            }
        }

        return  result
    }

}