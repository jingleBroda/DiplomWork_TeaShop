package com.example.teashop_v1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.teashop_v1.ui.cartClient.CartFragment
import com.google.firebase.database.*


class TestTableView : AppCompatActivity() {
    var preff : SharedPreferences? = null
    var tmpInsideText = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_table_view)

        //принятие данных от фрагмента с ассортиментом
        var insideText=this.findViewById<TextView>(R.id.testInsideTxt)
        insideText.text = intent.getStringExtra("testText")
        tmpInsideText = insideText.text.toString()
        preff = getSharedPreferences("ArrayCartTable", Context.MODE_PRIVATE)



    }

    //переход на экран с фрагментами
    fun goToCart(view: View) {

        val editor = preff?.edit()
        //если была нажата кнопка очистки, то:
        if (tmpInsideText!="") {
            //проверяем добавляемую позицию в корзину
            var arrayToStringCartItem:String = preff?.getString("ArrayCart", "Tyt Pusto")!!
            var nameAssortiCart = decryptionArrayToStringCartItem(arrayToStringCartItem)

            //если выбранный товар входит в массив элементов корзины
            if (tmpInsideText in nameAssortiCart) {
                // То мы его не добавляем
                Toast.makeText(this,"Вы уже добавили этот товар в корзину.", Toast.LENGTH_SHORT).show()
            }
            else {
                //Иначе добавляем
                //сохраняем в последовательнось выбранную позицию:
                //1. Сохраняем ее наименование в общую строку наименований
                val sTr1 = preff?.getString("ArrayCart", "") + tmpInsideText + "|"
                editor?.putString("ArrayCart", sTr1)

                //2. Сохранем ее количество (по умолчанию колиество = 1)
                val sTr2 = preff?.getString("ArrayQuantityProductCart", "") + "1" + "|"
                editor?.putString("ArrayQuantityProductCart", sTr2)

                //Сохраняем.
                editor?.apply()

                Toast.makeText(this,"Товар успешно добавлен в корзину.", Toast.LENGTH_SHORT).show()
            }
        }


        val testIntent = Intent(this, NavigtionTeaShop::class.java)
        startActivity(testIntent)

    }

    //преобразование строки с перечнем товаров в корзине в массив строк
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