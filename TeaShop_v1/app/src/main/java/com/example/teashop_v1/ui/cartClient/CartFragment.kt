  package com.example.teashop_v1.ui.cartClient

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teashop_v1.*
import com.example.teashop_v1.R
import com.google.firebase.database.*


  class CartFragment : Fragment() {


    var preff :SharedPreferences? = null

    //устанавливаем коннект к БД
    var database = FirebaseDatabase.getInstance()
    var myRef = database.getReference("Commodity")
    var dbReferensTwo = database.getReference("ActiveOrders")

    private lateinit var cartViewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_cart_screen, container, false)

        //ВРЕМЕННО!!!!!! устанавливаем переменные для изменения веса в зависимости от выбранного количества
        var tmpQuantity = 500

        //считываем собранный формат сохранненых позиций товаров
        preff = activity?.getSharedPreferences("ArrayCartTable", Context.MODE_PRIVATE)
        var finaliPriceCart:Int =0
        var arrayToStringCartItem:String = preff?.getString("ArrayCart", "Tyt Pusto")!!
        var arrayToStringCartItemQuantity:String = preff?.getString("ArrayQuantityProductCart", "")!! //строка количеств товаров в корзине
        var testTXT: TextView = root.findViewById(R.id.TestTextView)
        testTXT.text = "0"//arrayToStringCartItem //arrayToStringCartItemQuantity




        //переводим полученню строку в массив выбранных товаров
        var list =ArrayList<ListIteamCart>()


        val myRecyclerView: RecyclerView =root.findViewById(R.id.rcViewCart)

        //ДЛЯ КОРРЕКТНОГО ОТОБРАЖЕНИЯ RECYCLERVIEW добавляем следющие атрибуты:
        //1.фиксируем размер
        myRecyclerView.hasFixedSize()
        //2.устанавливаем контекс лайаут менеджеру
        myRecyclerView.layoutManager= LinearLayoutManager(activity)


        if (arrayToStringCartItem != "Tyt Pusto") {
            //тут добавляем лист полченных стрингови формирем его с помощью написанной функции decryptionArrayToStringCartItem
            var nameAssortiCart = decryptionArrayToStringCartItem(arrayToStringCartItem)
            var quantityAssortiCart = decryptionArrayToStringCartItem(arrayToStringCartItemQuantity) //массив количеств товаров в корзине

            //устонавливаем выбранные ячейки товаров
            for (i in 0..nameAssortiCart.size-1){
                val myQuery: Query = myRef.orderByChild("name").equalTo(nameAssortiCart[i])
                myQuery.addChildEventListener(object : ChildEventListener {

                    override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                        var item:ListItemAssortiments = snapshot.getValue(ListItemAssortiments::class.java)!!
                        finaliPriceCart+=item.cost*quantityAssortiCart[i].toInt()
                        testTXT.text = finaliPriceCart.toString()

                                list.add(ListIteamCart(
                                item.name,
                                (tmpQuantity * quantityAssortiCart[i].toInt()).toString()+"г",
                                quantityAssortiCart[i],
                                item.cost,
                                item.image,
                                R.drawable.img_plus,
                                R.drawable.img_minus
                        )
                        )

                        //устанавливаем кастомный адаптер для экрана с ассортиментами нашему RecyclerView
                        myRecyclerView.adapter= AdapterForCartScreen(list,requireContext(), testTXT)

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


        //установка слушателя нажатий на кнопку оформления заказа
        var buttonCreateOrder = root.findViewById<Button>(R.id.createOrder)
        //buttonCreateOrder.text= buttonCreateOrder.text.toString()+finaliPriceCart.toString()
        if(arrayToStringCartItem != "Tyt Pusto") {
            buttonCreateOrder.visibility = View.VISIBLE //показываем кнопку оформлениея заказа
            buttonCreateOrder.setOnClickListener() {
                val editor = preff?.edit()
                editor?.clear()
                editor?.apply()

                dbReferensTwo.push().setValue(list) //,testTXT.text

                Toast.makeText(activity, "Заказ успешно сформирован! Ожидайте его готовности.", Toast.LENGTH_SHORT).show()
                val testIntent = Intent(context, NavigtionTeaShop::class.java)
                this.startActivity(testIntent)
            }
        }
        return root
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

  }