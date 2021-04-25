package com.example.teashop_v1.ui.cartClient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teashop_v1.*

class CartFragment : Fragment() {

    private lateinit var cartViewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_cart_screen, container, false)


        var list =ArrayList<ListIteamCart>()
        list.add(ListIteamCart(
            "Чай1",
            "500гр",
            "3",
            "390Р",
            R.drawable.test_avatar,
            R.drawable.img_plus,
            R.drawable.img_minus
            )
        )
        list.add(ListIteamCart(
            "Чай2",
            "500гр",
            "3",
            "390Р",
            R.drawable.test_avatar,
            R.drawable.img_plus,
            R.drawable.img_minus
        )
        )
        list.add(ListIteamCart(
            "Чай3",
            "500гр",
            "3",
            "390Р",
            R.drawable.test_avatar,
            R.drawable.img_plus,
            R.drawable.img_minus
        )
        )


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
}