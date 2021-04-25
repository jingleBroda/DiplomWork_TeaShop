package com.example.teashop_v1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TestTableView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_table_view)

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
                102,
                "Подробнее...")
        )
        list.add(ListItemAssortiments(
                R.drawable.test_avatar,
                "Чай 3",
                "Ну тут типо описание",
                1000,
                "Подробнее...")
        )
        list.add(ListItemAssortiments(
            R.drawable.test_avatar,
            "Чай 4",
            "Ну тут типо описание",
            130,
            "Подробнее...")
        )
        list.add(ListItemAssortiments(
            R.drawable.test_avatar,
            "Чай 5",
            "Ну тут типо описание",
            500,
            "Подробнее...")
        )
        list.add(ListItemAssortiments(
            R.drawable.test_avatar,
            "Чай 6",
            "Ну тут типо описание",
            0,
            "Подробнее...")
        )

        val myRecyclerView: RecyclerView =this.findViewById(R.id.rcViewAssorti)
        myRecyclerView.hasFixedSize()
        myRecyclerView.layoutManager=LinearLayoutManager(this)
        myRecyclerView.adapter=AdapterForAssortiScreen(list,this)


    }
}