package com.example.teashop_v1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

            //если выбранный товар входит в массив элементов корзины, то мы его не добавляем, иначе добавляем
            if (tmpInsideText in nameAssortiCart) {
                Toast.makeText(this,"Вы уже добавили этот товар в корзину.", Toast.LENGTH_SHORT).show()
            }
            else {
                //сохраняем в последовательнось выбранную позицию
                val sTr = preff?.getString("ArrayCart", "") + tmpInsideText + "|"
                editor?.putString("ArrayCart", sTr)
                editor?.apply()
                Toast.makeText(this,"Товар успешно добавлен в корзину.", Toast.LENGTH_SHORT).show()
            }
        }


        val testIntent = Intent(this, NavigtionTeaShop::class.java)
        startActivity(testIntent)

    }

    //очистка ShaardePrefernce
    fun clearArrayCart(view: View) {
        val editor = preff?.edit()
        editor?.clear()
        editor?.apply()
        tmpInsideText=""
        Toast.makeText(this,"Корзина очищена", Toast.LENGTH_SHORT).show()
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