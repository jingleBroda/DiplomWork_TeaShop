package com.example.sellerappteashop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class AdditionalInformationActivity : AppCompatActivity() {

    //установка конекста к БД
    private val dataBase = FirebaseDatabase.getInstance()
    private val myRef = dataBase.getReference("ActiveOrders")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_additional_information)


        //установка стартовых параметров для запроса о конкретном заказе
        var idOrder = intent.getStringExtra("IdOrder")!!
        var idCustomer = intent.getStringExtra("IdCustomer")!!

        val textIdOrder:TextView = this.findViewById(R.id.IdOrderSingle)
        textIdOrder.text = "Номер заказа: " + idOrder

        var textIdCustomer: TextView = this.findViewById(R.id.IdCustomerSingleOrder)
        textIdCustomer.text = "ID заказчика: " + idCustomer

        var singleOrderItem = ArrayList<ListItemSingleOrder>()
        val myRecView: RecyclerView = this.findViewById(R.id.recViewSingleOrder)
        myRecView.hasFixedSize()
        myRecView.layoutManager= LinearLayoutManager(this)

        //делаем запрос в БД на получение id активных заказов
        val myQuery: Query = myRef.child(idOrder)
        myQuery.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val item = snapshot.getValue(ListItemSingleOrder::class.java)!!
                singleOrderItem.add(item)
                myRecView.adapter = AdapterSingleOrder(singleOrderItem, context = this@AdditionalInformationActivity)
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
