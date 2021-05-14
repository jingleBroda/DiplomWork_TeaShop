package com.example.sellerappteashop.ui.activeOrder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sellerappteashop.AdapterActiveOrder
import com.example.sellerappteashop.ListItemActiveOrder
import com.example.sellerappteashop.R
import com.google.firebase.database.*

class ActiveOrderFragment : Fragment() {

    private lateinit var activeOrderViewModel: ActiveOrderViewModel

    //установка конекста к БД
    val dataBase = FirebaseDatabase.getInstance()
    val myRef = dataBase.getReference("ActiveOrders")

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        activeOrderViewModel = ViewModelProvider(this).get(ActiveOrderViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_list_active_order, container, false)

        val myRecView:RecyclerView = root.findViewById(R.id.activeOrderRecView)
        myRecView.hasFixedSize()
        myRecView.layoutManager= LinearLayoutManager(activity)

        val arrayActiveOrder = ArrayList<ListItemActiveOrder>()

        //делаем запрос в БД на получение id активных заказов
        val myQuery: Query = myRef
        myQuery.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                var tmpItemActiveOrder = ListItemActiveOrder()
                tmpItemActiveOrder.idOrder = snapshot.key
                arrayActiveOrder.add(tmpItemActiveOrder)
                myRecView.adapter= AdapterActiveOrder(arrayActiveOrder,requireContext())
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


        return root
    }

}