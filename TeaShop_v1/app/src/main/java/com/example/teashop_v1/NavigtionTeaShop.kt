package com.example.teashop_v1

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NavigtionTeaShop : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigtion_tea_shop)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigtion_assortiments_screen,
                R.id.navigation_cart_screen,
                R.id.navigation_personal_area_screen
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        /*
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

        val myRecyclerView: RecyclerView =this.findViewById(R.id.rcViewTest)
        myRecyclerView.hasFixedSize()
        myRecyclerView.layoutManager=LinearLayoutManager(this)
        myRecyclerView.adapter=MyAdapter(list,this)
        */


    }
}