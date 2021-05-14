package com.example.sellerappteashop

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterActiveOrder(listArray:ArrayList<ListItemActiveOrder>, context: Context):RecyclerView.Adapter<AdapterActiveOrder.ViewHolder>() {
    private var listArrayResicle=listArray
    private var listContextResicle=context

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val rowIdOrder=view.findViewById<TextView>(R.id.idOrderActive)
        private val rowIdCustomer=view.findViewById<TextView>(R.id.idCustomerActiveOrder)

        fun bind(listItem:ListItemActiveOrder, context:Context){
            rowIdOrder.text = listItem.idOrder
            rowIdCustomer.text = listItem.idCustomer

            //устанавливаем слушатель нажатия на ячейку RecyclerView
            itemView.setOnClickListener(){
                //передаем данные о заказе в экран более подробного отображения
                val testIntent = Intent(context, AdditionalInformationActivity::class.java).apply {
                    putExtra("IdOrder", listItem.idOrder)
                    putExtra("IdCustomer", listItem.idCustomer)
                }
                //вызываем открытие экрана с подробной инфой о товаре
                context.startActivity(testIntent)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater= LayoutInflater.from(listContextResicle)
        return ViewHolder(inflater.inflate(R.layout.row_active_order,parent,false))
    }

    override fun getItemCount(): Int {
        return listArrayResicle.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem= listArrayResicle[position]
        holder.bind(listItem, listContextResicle)
    }
}
