package com.example.teashop_v1.ui.shopAssortiments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teashop_v1.AdapterForAssortiScreen
import com.example.teashop_v1.ListItemAssortiments
import com.example.teashop_v1.R
import com.example.teashop_v1.ui.personalArea.PersonalAreaFragment


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


        var list =ArrayList<ListItemAssortiments>()
        list.add(ListItemAssortiments(
                R.drawable.test_avatar,
                "Чай 1",
                "Ну тут типо описание",
                100,
                "Подробнее...")
        )
        list.add(ListItemAssortiments(
                R.drawable.test_avatar,
                "Чай 2",
                "Ну тут типо описание",
                100,
                "Подробнее...")
        )
        list.add(ListItemAssortiments(
                R.drawable.test_avatar,
                "Чай 3",
                "Ну тут типо описание",
                100,
                "Подробнее...")
        )
        list.add(ListItemAssortiments(
                R.drawable.test_avatar,
                "Чай 4",
                "Ну тут типо описание",
                100,
                "Подробнее...")
        )
        list.add(ListItemAssortiments(
                R.drawable.test_avatar,
                "Чай 5",
                "Ну тут типо описание",
                100,
                "Подробнее...")
        )

        val myRecyclerView: RecyclerView =root.findViewById(R.id.rcViewAssorti)

        //ДЛЯ КОРРЕКТНОГО ОТОБРАЖЕНИЯ RECYCLERVIEW добавляем следющие атрибуты:
        //1.фиксируем размер
        myRecyclerView.hasFixedSize()
        //2.устанавливаем контекс лайаут менеджеру
        myRecyclerView.layoutManager=LinearLayoutManager(activity)

        //устанавливаем кастомный адаптер для экрана с ассортиментами нашему RecyclerView
        myRecyclerView.adapter=AdapterForAssortiScreen(list,requireContext())
        return root
    }
}