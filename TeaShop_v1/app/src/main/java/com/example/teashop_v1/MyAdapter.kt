package com.example.teashop_v1

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.security.AccessControlContext

class MyAdapter(listArray:ArrayList<ListItemAssortiments>, context: Context):RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    var listArrayResicle=listArray
    var listContextResicle=context

    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val rowName=view.findViewById<TextView>(R.id.itemName)
        val rowPrice=view.findViewById<TextView>(R.id.itemPrice)
        val rowDescription=view.findViewById<TextView>(R.id.itemDescription)
        val rowImage=view.findViewById<ImageView>(R.id.itemImage)
        val moreInfoText=view.findViewById<TextView>(R.id.moredetailsText)


        fun bind(listItem:ListItemAssortiments, context: Context){
            rowName.text=listItem.name_id
            rowPrice.text=listItem.price_id.toString()
            rowDescription.text=listItem.descript_id
            rowImage.setImageResource(listItem.img_id)
            moreInfoText.text="Подробнее..."

            itemView.setOnClickListener(){
                Toast.makeText(context,"Press number Row=${listItem.name_id}",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater=LayoutInflater.from(listContextResicle)
        return ViewHolder(inflater.inflate(R.layout.item_assortiments_screen,parent,false))
    }

    override fun getItemCount(): Int {
        return listArrayResicle.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var listItem=listArrayResicle.get(position)
        holder.bind(listItem,listContextResicle)
    }
}