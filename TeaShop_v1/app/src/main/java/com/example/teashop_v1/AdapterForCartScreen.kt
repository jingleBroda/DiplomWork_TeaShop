package com.example.teashop_v1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

 class AdapterForCartScreen(listArray:ArrayList<ListIteamCart>, context: Context, sumList:TextView): RecyclerView.Adapter<AdapterForCartScreen.ViewHolder>(){

    var listArrayResicle=listArray
    var listContextResicle=context
    var sumListResicle = sumList

      class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val rowName=view.findViewById<TextView>(R.id.nameProduct)
        val rowWeight=view.findViewById<TextView>(R.id.weightProduct)
        val rowQuantity=view.findViewById<TextView>(R.id.quantityProduct)
        val rowPrice=view.findViewById<TextView>(R.id.priceProduct)



        val rowMainImg=view.findViewById<ImageView>(R.id.imgProduct)
        val rowPlusImg=view.findViewById<ImageView>(R.id.btnPlus)
        val rowMinusImg=view.findViewById<ImageView>(R.id.btnMinus)

        var preff : SharedPreferences? = null

        fun bind(listItem:ListIteamCart, context: Context,position: Int, sumList:TextView){

            rowName.text = listItem.name_id
            rowWeight.text = listItem.weight_id
            rowQuantity.text = listItem.quantityProduct_id
            rowPrice.text = (listItem.price_id*listItem.quantityProduct_id.toInt()).toString()+"Р"

            Picasso.get().load(listItem.imgProduct_id).placeholder(R.drawable.test_avatar).into(rowMainImg)
            rowPlusImg.setImageResource(listItem.imgPlus_id)
            rowMinusImg.setImageResource(listItem.imgMinus_id)


            //обработка нажатия на кнопку +
            rowPlusImg.setOnClickListener(){
                //увеличиваем количество выбранного товара
                var increaseWeightProduct = listItem.quantityProduct_id.toInt()+1
                listItem.quantityProduct_id = increaseWeightProduct.toString()
                rowQuantity.text = listItem.quantityProduct_id

                //Сохранение измененого количества товара в ShardPreference
                //1. Устонавливаем коннект с ShardPreference
                preff = context.getSharedPreferences("ArrayCartTable", Context.MODE_PRIVATE)
                val editor = preff?.edit()
                //2. Извлекаем строку с количествами товаров
                var arrayToStringCartItemQuantity:String = preff?.getString("ArrayQuantityProductCart", "")!!
                //3. Переводим эту строку в массив строк
                var quantityAssortiCart = decryptionArrayToStringCartItem(arrayToStringCartItemQuantity)
                //4. Изменяем записанное число на высчитанное в резльтате кнопки +
                quantityAssortiCart[position] = increaseWeightProduct.toString()
                //5. Сохраняем изменение
                var newQuantityCartString = encryptionArrayToStringCartItem(quantityAssortiCart)
                editor?.putString("ArrayQuantityProductCart", newQuantityCartString)
                editor?.apply()

                //ВРЕМЕННО!!!!!! устанавливаем переменные для изменения веса и цены товара, в зависимости от выбранного количества
                var tmpWeight = 500
                var tmpWeightString = ""

                var tmpPrice = listItem.price_id
                var tmpPriceString=""

                tmpWeightString = (tmpWeight * quantityAssortiCart[position].toInt()).toString()+"г"
                tmpPriceString = (tmpPrice * quantityAssortiCart[position].toInt()).toString()+"Р"
                rowWeight.text = tmpWeightString
                rowPrice.text = tmpPriceString

                sumList.text = (sumList.text.toString().toInt()+ tmpPrice ).toString()

            }

            //обработка нажатия на кнопку -
            rowMinusImg.setOnClickListener(){

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

                    //Сохранение измененого количества товара в ShardPreference
                    //1. Устонавливаем коннект с ShardPreference
                    preff = context.getSharedPreferences("ArrayCartTable", Context.MODE_PRIVATE)
                    val editor = preff?.edit()
                    //2. Извлекаем строку с количествами товаров
                    var arrayToStringCartItemQuantity:String = preff?.getString("ArrayQuantityProductCart", "")!!
                    //3. Переводим эту строку в массив строк
                    var quantityAssortiCart = decryptionArrayToStringCartItem(arrayToStringCartItemQuantity)
                    //4. Изменяем записанное число на высчитанное в резльтате кнопки +
                    quantityAssortiCart[position] = decreaseWeightProduct.toString()
                    //5. Сохраняем изменение
                    var newQuantityCartString = encryptionArrayToStringCartItem(quantityAssortiCart)
                    editor?.putString("ArrayQuantityProductCart", newQuantityCartString)
                    editor?.apply()

                    //ВРЕМЕННО!!!!!! устанавливаем переменные для изменения веса и цены товара, в зависимости от выбранного количества
                    var tmpWeight = 500
                    var tmpWeightString = ""


                    var tmpPrice = listItem.price_id
                    var tmpPriceString=""


                    tmpWeightString = (tmpWeight * quantityAssortiCart[position].toInt()).toString()+"г"
                    tmpPriceString = (tmpPrice * quantityAssortiCart[position].toInt()).toString()+"Р"
                    rowWeight.text = tmpWeightString
                    rowPrice.text = tmpPriceString

                    sumList.text = (sumList.text.toString().toInt() - tmpPrice ).toString()

                }

            }

        }

        fun decryptionArrayToStringCartItem(arrayToString:String):ArrayList<String>{

            var result = ArrayList<String>()
            var tmpString:String=""

            for (i in arrayToString.toCharArray())
            {
                if (i != '|'){
                    tmpString += i.toString()
                }
                else {
                    result.add(tmpString)
                    tmpString=""
                }
            }

            return  result
        }

        fun encryptionArrayToStringCartItem (arrayList:ArrayList<String>):String{
            var result=""

            for (i in arrayList)
            {
                result += i +"|"
            }

            return  result
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
        holder.bind(listItem,listContextResicle,position, sumListResicle)

    }

}