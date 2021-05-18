package com.example.teashop_v1

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class AdapterForAssortiScreen(listArray:ArrayList<ListItemAssortiments>, context: Context):RecyclerView.Adapter<AdapterForAssortiScreen.ViewHolder>() {

    var listArrayResicle=listArray
    var listContextResicle=context

    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {



        val rowName=view.findViewById<TextView>(R.id.itemName)
        val rowPrice=view.findViewById<TextView>(R.id.itemPrice)
        val rowDescription=view.findViewById<TextView>(R.id.itemDescription)
        val rowImage=view.findViewById<ImageView>(R.id.itemImage)
        val moreInfoText=view.findViewById<TextView>(R.id.moredetailsText)


        fun bind(listItem:ListItemAssortiments, context: Context){
            rowName.text=listItem.name
            rowPrice.text=listItem.cost.toString()+"P"
            rowDescription.text=listItem.description
            Picasso.get().load(listItem.image).placeholder(R.drawable.test_avatar).into(rowImage)
            //rowImage.setImageResource(R.drawable.test_avatar)
            moreInfoText.text="Подробнее..."

            //устанавливаем слушатель нажатия на ячейку RecyclerView
            itemView.setOnClickListener(){
                //Делаем передачу на экран TestTableView(он так называется пока, потому что использовался для изучения работы RecyclerView)
                val testIntent = Intent(context, TestTableView::class.java).apply {
                    putExtra("testText", listItem.name)
                    putExtra("categoryTovar", listItem.category)
                }
                //вызываем открытие экрана с подробной инфой о товаре
                context.startActivity(testIntent)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater=LayoutInflater.from(listContextResicle)
        return ViewHolder(inflater.inflate(R.layout.row_assortiments_screen,parent,false))
    }

    override fun getItemCount(): Int {
        return listArrayResicle.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var listItem=listArrayResicle.get(position)
        holder.bind(listItem,listContextResicle)
    }
}