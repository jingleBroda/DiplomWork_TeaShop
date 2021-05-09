package com.example.teashop_v1

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class AdapterForCartScreen(listArray:ArrayList<ListIteamCart>, context: Context): RecyclerView.Adapter<AdapterForCartScreen.ViewHolder>(){

    var listArrayResicle=listArray
    var listContextResicle=context

    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val rowName=view.findViewById<TextView>(R.id.nameProduct)
        val rowWeight=view.findViewById<TextView>(R.id.weightProduct)
        val rowQuantity=view.findViewById<TextView>(R.id.quantityProduct)
        val rowPrice=view.findViewById<TextView>(R.id.priceProduct)

        val rowMainImg=view.findViewById<ImageView>(R.id.imgProduct)
        val rowPlusImg=view.findViewById<ImageView>(R.id.btnPlus)
        val rowMinusImg=view.findViewById<ImageView>(R.id.btnMinus)



        fun bind(listItem:ListIteamCart, context: Context,position: Int){

            rowName.text = listItem.name_id
            rowWeight.text = listItem.weight_id
            rowQuantity.text = listItem.quantityProduct_id
            rowPrice.text = listItem.price_id

            rowMainImg.setImageResource(listItem.imgProduct_id)
            rowPlusImg.setImageResource(listItem.imgPlus_id)
            rowMinusImg.setImageResource(listItem.imgMinus_id)


            //обработка нажатия на кнопку +
            rowPlusImg.setOnClickListener(){
                //увеличиваем количество выбранного товара
                var increaseWeightProduct = listItem.quantityProduct_id.toInt()+1
                listItem.quantityProduct_id = increaseWeightProduct.toString()
                rowQuantity.text = listItem.quantityProduct_id
            }

            //обработка нажатия на кнопку -
            rowMinusImg.setOnClickListener(){
                //Toast.makeText(context,"Press number Row PlusButton=${listItem.name_id}", Toast.LENGTH_SHORT).show()
                //уменьшаем количество выбранного товара
                var decreaseWeightProduct = listItem.quantityProduct_id.toInt()-1
                //Если число товара в корзине = 0, то товар из корзины удаляется.
                if (decreaseWeightProduct==0){
                    val testIntent = Intent(context, NavigtionTeaShop::class.java).apply {
                        putExtra("deletePosition", position.toString())
                    }
                    context.startActivity(testIntent)
                }
                else {
                    listItem.quantityProduct_id = decreaseWeightProduct.toString()
                    rowQuantity.text = listItem.quantityProduct_id
                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater= LayoutInflater.from(listContextResicle)
        return AdapterForCartScreen.ViewHolder(inflater.inflate(R.layout.row_cart_screen, parent, false))
    }

    override fun getItemCount(): Int {
        return listArrayResicle.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var listItem=listArrayResicle[position] //.get(position)
        holder.bind(listItem,listContextResicle,position)

    }

}