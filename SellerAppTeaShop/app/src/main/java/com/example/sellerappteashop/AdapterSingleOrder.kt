package com.example.sellerappteashop

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterSingleOrder(listArray:ArrayList<ListItemSingleOrder>, context: Context):RecyclerView.Adapter<AdapterSingleOrder.ViewHolder>() {
    private var listArrayResicle=listArray
    private var listContextResicle=context

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val rowName = view.findViewById<TextView>(R.id.itemName)
        val rowWeight = view.findViewById<TextView>(R.id.itemWeight)
        val rowPrice = view.findViewById<TextView>(R.id.itemPrice)

        fun bind(listItem:ListItemSingleOrder){
            rowName.text = listItem.name_id
            rowWeight.text = (listItem.quantityProduct_id.toInt()*500).toString()+"г"
            //ЭТИ СТРОКИ ОТНОСЯТСЯ К СТРОКЕ КОДА ВЫШЕ
            //ПОКА У ВСЕХ ТОВАРОВ ГРАММОВКА = 500, ПОЭТОМУ ТУТ С ТОВАРА НЕ БУДЕТ БРАТЬСЯ ВЕС
            //НО ЭТО НУЖНО БУДЕТ ИСПРАВИТЬ
            rowPrice.text = (listItem.price_id*listItem.quantityProduct_id.toInt()).toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterSingleOrder.ViewHolder {
        val inflater= LayoutInflater.from(listContextResicle)
        return ViewHolder(inflater.inflate(R.layout.row_single_order,parent,false))
    }

    override fun getItemCount(): Int {
        return listArrayResicle.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem= listArrayResicle[position]
        holder.bind(listItem)
    }
}
