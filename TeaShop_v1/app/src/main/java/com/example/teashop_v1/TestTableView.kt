package com.example.teashop_v1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teashop_v1.ui.personalArea.PersonalAreaFragment

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


    fun backButtonClick(view: View) {

        val editor = preff?.edit()
        //если была нажата кнопка очистки, то:
        if (tmpInsideText==""){
            val sTr: String = ""

        }
        else { //иначе сохраняем в последовательнось выбранную позицию
            val sTr = preff?.getString("ArrayCart", "") + tmpInsideText + "|"
            editor?.putString("ArrayCart", sTr)
            editor?.apply()
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


}