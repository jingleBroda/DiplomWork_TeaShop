package com.example.teashop_v1

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NavigtionTeaShop : AppCompatActivity() {

    var preff : SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigtion_tea_shop)


        //Удаление элемента из корзины
        if(intent.getStringExtra("deletePosition") != null){
            //1. получаем позицию, которую нужно удалить
            var insideText:String = intent.getStringExtra("deletePosition")!!
            var deletePosition = insideText.toInt()!!

            //2. Считываем текущий набор товаров в корзине
            preff = getSharedPreferences("ArrayCartTable", Context.MODE_PRIVATE)

            var arrayToStringCartItem:String = preff?.getString("ArrayCart", "Tyt Pusto")!!
            var nameAssortiCart = decryptionArrayToStringCartItem(arrayToStringCartItem)

            var arrayToStringCartItemQuantity:String = preff?.getString("ArrayQuantityProductCart", "")!!
            var quantityAssortiCart = decryptionArrayToStringCartItem(arrayToStringCartItemQuantity)

            //3. Удаляем из массива товаров корзины полченную позицию
            nameAssortiCart.removeAt(deletePosition)
            quantityAssortiCart.removeAt(deletePosition)

            //4. Проверка массива на пустоту(ситуация, когда пользователь удалил все товары из корзины)
            if (nameAssortiCart.size!=0) {
                //4.1.1. Переводим массив в цельную строку
                var resultArrayToString = ""
                var resultQuantityArrayToString = ""
                var j=0
                for (i in nameAssortiCart) {
                    resultArrayToString += i + "|"
                    resultQuantityArrayToString += quantityAssortiCart[j]+"|"
                    j++
                }

                //4.1.2. Сохраняем измененеие в ШардПреференс
                val editor = preff?.edit()
                editor?.putString("ArrayCart", resultArrayToString)
                editor?.putString("ArrayQuantityProductCart", resultQuantityArrayToString)
                editor?.apply()
                Toast.makeText(this,"Товар был успешно удален из корзины.", Toast.LENGTH_SHORT).show()
            }
            else{
                //4.2.1 Очищаем Преференс
                val editor = preff?.edit()
                editor?.clear()
                editor?.apply()
                Toast.makeText(this,"Товар был успешно удален из корзины.", Toast.LENGTH_SHORT).show()
            }

        }


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