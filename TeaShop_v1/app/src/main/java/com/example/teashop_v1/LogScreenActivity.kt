package com.example.teashop_v1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class LogScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_screen)
    }

    fun nextScreen (view: View){
        val testIntent= Intent(this, NavigtionTeaShop::class.java)
        startActivity(testIntent)
    }

}