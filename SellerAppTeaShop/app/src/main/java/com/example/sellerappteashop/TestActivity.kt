package com.example.sellerappteashop

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class TestActivity : AppCompatActivity() {

    //установка конекста к БД
    val dataBase = FirebaseDatabase.getInstance()
    val myRef = dataBase.getReference("ActiveOrders")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        var considerButton:Button = findViewById(R.id.considerButton)

        var idActiveCartOrder:ArrayList<String?> = ArrayList()

        //вызываем запрос в БД на получение id активных заказов
        getIDCartOrder(myRef,idActiveCartOrder)


        considerButton.setOnClickListener(){
            var tmpStr=""
            for(i in 0..idActiveCartOrder.size-1){
                tmpStr+= idActiveCartOrder[i]+"   |   "
            }
            //запрос на выдачу id всех активных заказов
            Toast.makeText(this, tmpStr, Toast.LENGTH_LONG).show()
        }

    }

    private fun getIDCartOrder(myCustomRef:DatabaseReference, idActiveCartOrder:ArrayList<String?>){
        val myQuery:Query = myCustomRef
        myQuery.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                idActiveCartOrder.add(snapshot.key)
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

        })
    }


}