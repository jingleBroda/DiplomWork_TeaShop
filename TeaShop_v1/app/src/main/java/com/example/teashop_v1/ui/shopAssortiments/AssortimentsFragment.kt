package com.example.teashop_v1.ui.shopAssortiments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teashop_v1.AdapterForAssortiScreen
import com.example.teashop_v1.ListItemAssortiments
import com.example.teashop_v1.R
import com.example.teashop_v1.ui.personalArea.PersonalAreaFragment
import com.google.firebase.database.*


class AssortimentsFragment : Fragment() {

    private lateinit var assortimentsViewModel: AssortimentsViewModel

    //устанавливаем коннект к БД
    var database = FirebaseDatabase.getInstance()
    var myRef = database.getReference("Commodity")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        assortimentsViewModel =
            ViewModelProvider(this).get(AssortimentsViewModel::class.java)

        //устанавливаем root-элемент
        val root = inflater.inflate(R.layout.fragment_assortiments_screen, container, false)

        //формирование list по итогу запроса в БД:
        //объявляем list
        var list =ArrayList<ListItemAssortiments>()
        //объявляем RecyclerView
        val myRecyclerView: RecyclerView =root.findViewById(R.id.rcViewAssorti)
        //ДЛЯ КОРРЕКТНОГО ОТОБРАЖЕНИЯ RECYCLERVIEW добавляем следющие атрибуты:
        //1.фиксируем размер
        myRecyclerView.hasFixedSize()
        //2.устанавливаем контекс лайаут менеджеру
        myRecyclerView.layoutManager=LinearLayoutManager(activity)


        //прописываем запрос
        //расшифрую запрос: БЕРЕМ ВСЕ ЗАПИСИ
        val myQuery: Query = myRef //.orderByChild("id").equalTo(1.0)
        myQuery.addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val item: ListItemAssortiments? = snapshot.getValue(ListItemAssortiments::class.java)
                //testText.text = item?.cost.toString()
                list.add(item!!)
                //устанавливаем кастомный адаптер для экрана с ассортиментами нашему RecyclerView
                myRecyclerView.adapter=AdapterForAssortiScreen(list,requireContext())
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