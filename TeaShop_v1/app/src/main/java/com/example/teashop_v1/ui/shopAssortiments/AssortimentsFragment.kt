package com.example.teashop_v1.ui.shopAssortiments

import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teashop_v1.AdapterForAssortiScreen
import com.example.teashop_v1.ListItemAssortiments
import com.example.teashop_v1.R
import com.google.firebase.database.*


class AssortimentsFragment : Fragment(), TextView.OnEditorActionListener {

    private lateinit var assortimentsViewModel: AssortimentsViewModel

    //устанавливаем коннект к БД
    var database = FirebaseDatabase.getInstance()
    var myRef = database.getReference("Commodity")

    //для теста работы с клавиатурой
    var editSearch: EditText? = null

    //параметры для таблицы товаров
    var myRecyclerView:RecyclerView? = null
    var list =ArrayList<ListItemAssortiments>()
    var filterList =ArrayList<ListItemAssortiments>()

    //признаки выбранных категорий: true-выбрана, false-не выбрана
    var prViborCategor = arrayOf<Boolean>(false, false, false)

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
        //var list =ArrayList<ListItemAssortiments>()
        //объявляем RecyclerView
        myRecyclerView = root.findViewById<RecyclerView>(R.id.rcViewAssorti)
        //ДЛЯ КОРРЕКТНОГО ОТОБРАЖЕНИЯ RECYCLERVIEW добавляем следющие атрибуты:
        //1.фиксируем размер
        myRecyclerView!!.hasFixedSize()
        //2.устанавливаем контекс лайаут менеджеру
        myRecyclerView!!.layoutManager=LinearLayoutManager(activity)

        //тест ввода в клавиатуру
        editSearch = root.findViewById(R.id.editTextNameTea)
        editSearch!!.setOnEditorActionListener(this)
        editSearch!!.setImeActionLabel("Найти", EditorInfo.IME_ACTION_DONE);

        //тест отображения меню с категориями товаров
        val selectCatssortiment = root.findViewById<Button>(R.id.selectCategoryButton)
        val categMenu = androidx.appcompat.widget.PopupMenu(requireContext(), selectCatssortiment)
        categMenu.inflate(R.menu.menu_filter_refinement)

        categMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menuFilterCateg1 -> {
                    //если категория выбрана
                    if (it.isChecked){
                        //делаем ее не выбранной
                        prViborCategor[0] = false
                        it.isChecked = false

                        var i=0
                        while (i<filterList.size){
                            if ( filterList[i].category == "Зеленый чай"){
                                filterList.removeAt(i)
                            }
                            else i++
                        }

                        if(!(prViborCategor[0] || prViborCategor[1] || prViborCategor[2])) {
                            for (i in list) {
                                val prinad = i.name.findAnyOf(strings = listOf(editSearch?.text.toString()), startIndex = 0, ignoreCase = false)
                                if (prinad != null) {
                                    filterList.add(i)
                                }
                            }
                        }

                        if(filterList.size == 0){
                            Toast.makeText(activity, "Товара с заданными значениями нет в ассортименте.", Toast.LENGTH_SHORT).show()
                        }

                        myRecyclerView!!.adapter =  AdapterForAssortiScreen(filterList,requireContext())
                    }
                    else {
                        //иначе делаем ее выбранной и ормирем новый массив товаров

                        if(!(prViborCategor[0] || prViborCategor[1] || prViborCategor[2])) {
                            filterList.clear()
                        }

                        it.isChecked = true
                        prViborCategor[0] = true


                        if (editSearch?.text.toString() !="") {
                            for (i in list) {
                                val prinad = i.name.findAnyOf(strings = listOf(editSearch?.text.toString()), startIndex = 0, ignoreCase = false)
                                if ((i.category == "Зеленый чай")&&(prinad != null)&&(i !in filterList)) {
                                    filterList.add(i)
                                }
                            }
                        }
                        else {
                            for (i in list) {
                                if ((i.category == "Зеленый чай")&&(i !in filterList)) {
                                    filterList.add(i)
                                }
                            }
                        }

                        if(filterList.size == 0){
                            Toast.makeText(activity, "Товара с заданными значениями нет в ассортименте.", Toast.LENGTH_SHORT).show()
                            myRecyclerView!!.adapter =  AdapterForAssortiScreen(filterList,requireContext())
                        }
                        else{
                            myRecyclerView!!.adapter =  AdapterForAssortiScreen(filterList,requireContext())
                        }

                    }
                    true
                }
                R.id.menuFilterCateg2 -> {
                    //если категория выбрана
                    if (it.isChecked){
                        //делаем ее не выбранной
                        prViborCategor[1] = false
                        it.isChecked = false

                        var i=0
                        while (i<filterList.size){
                            if ( filterList[i].category == "Черный чай"){
                                filterList.removeAt(i)
                            }
                            else i++
                        }

                        if(!(prViborCategor[0] || prViborCategor[1] || prViborCategor[2])) {
                            for (i in list) {
                                val prinad = i.name.findAnyOf(strings = listOf(editSearch?.text.toString()), startIndex = 0, ignoreCase = false)
                                if (prinad != null) {
                                    filterList.add(i)
                                }
                            }
                        }


                        if(filterList.size == 0){
                            Toast.makeText(activity, "Товара с заданными значениями нет в ассортименте.", Toast.LENGTH_SHORT).show()
                        }

                        myRecyclerView!!.adapter =  AdapterForAssortiScreen(filterList,requireContext())

                    }
                    else {
                        //иначе делаем ее выбранной и ормирем новый массив товаров
                        if(!(prViborCategor[0] || prViborCategor[1] || prViborCategor[2])) {
                            filterList.clear()
                        }

                        it.isChecked = true
                        prViborCategor[1] = true



                        if (editSearch?.text.toString() !="") {
                            for (i in list) {
                                val prinad = i.name.findAnyOf(strings = listOf(editSearch?.text.toString()), startIndex = 0, ignoreCase = false)
                                if ((i.category == "Черный чай")&&(prinad != null)&&(i !in filterList)) {
                                    filterList.add(i)
                                }
                            }
                        }
                        else {
                            for (i in list) {
                                if ((i.category == "Черный чай")&&(i !in filterList)) {
                                    filterList.add(i)
                                }
                            }
                        }

                        if(filterList.size == 0){
                            Toast.makeText(activity, "Товара с заданными значениями нет в ассортименте.", Toast.LENGTH_SHORT).show()
                            myRecyclerView!!.adapter =  AdapterForAssortiScreen(filterList,requireContext())
                        }
                        else{
                            myRecyclerView!!.adapter =  AdapterForAssortiScreen(filterList,requireContext())
                        }

                    }
                    true
                }
                R.id.menuFilterCateg3 -> {
                    //если категория выбрана
                    if (it.isChecked){
                        //делаем ее не выбранной
                        prViborCategor[2] = false
                        it.isChecked = false

                        var i=0
                        while (i<filterList.size){
                            if ( filterList[i].category == "Прочее"){
                                filterList.removeAt(i)
                            }
                            else i++
                        }

                        if(!(prViborCategor[0] || prViborCategor[1] || prViborCategor[2])) {
                            for (i in list) {
                                val prinad = i.name.findAnyOf(strings = listOf(editSearch?.text.toString()), startIndex = 0, ignoreCase = false)
                                if (prinad != null) {
                                    filterList.add(i)
                                }
                            }
                        }

                        if(filterList.size == 0){
                            Toast.makeText(activity, "Товара с заданными значениями нет в ассортименте.", Toast.LENGTH_SHORT).show()
                        }

                        myRecyclerView!!.adapter =  AdapterForAssortiScreen(filterList,requireContext())

                    }
                    else {
                        //иначе делаем ее выбранной и ормирем новый массив товаров

                        if(!(prViborCategor[0] || prViborCategor[1] || prViborCategor[2])) {
                            filterList.clear()
                        }

                        it.isChecked = true
                        prViborCategor[2] = true



                        if (editSearch?.text.toString() !="") {
                            for (i in list) {
                                val prinad = i.name.findAnyOf(strings = listOf(editSearch?.text.toString()), startIndex = 0, ignoreCase = false)
                                if ((i.category == "Прочее")&&(prinad != null)&&(i !in filterList)) {
                                    filterList.add(i)
                                }
                            }
                        }
                        else {
                            for (i in list) {
                                if ((i.category == "Прочее")&&(i !in filterList)) {
                                    filterList.add(i)
                                }
                            }
                        }

                        if(filterList.size == 0){
                            Toast.makeText(activity, "Товара с заданными значениями нет в ассортименте.", Toast.LENGTH_SHORT).show()
                            myRecyclerView!!.adapter =  AdapterForAssortiScreen(filterList,requireContext())
                        }
                        else{
                            myRecyclerView!!.adapter =  AdapterForAssortiScreen(filterList,requireContext())
                        }

                    }
                    true
                }
                else -> false
            }

        }
        //слушатель нажатия на кнопк отображения категорий
        selectCatssortiment.setOnClickListener(){
            categMenu.show()
        }

        //прописываем запрос
        //расшифрую запрос: БЕРЕМ ВСЕ ЗАПИСИ
        val myQuery: Query = myRef //.orderByChild("id").equalTo(1.0)
        myQuery.addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val item: ListItemAssortiments? = snapshot.getValue(ListItemAssortiments::class.java)
                //testText.text = item?.cost.toString()
                list.add(item!!)
                //устанавливаем кастомный адаптер для экрана с ассортиментами нашему RecyclerView
                myRecyclerView!!.adapter=AdapterForAssortiScreen(list,requireContext())
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

    //устанавливаем метод работы с клавиатрой при вводе названия товара
    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        val handled = false
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            if (v?.getId() == R.id.editTextNameTea) {
                // обрабатываем нажатие кнопки ввода наименования инстересующего покпателя товара
                filterList.clear()
                for(i in list){
                    val prinad = i.name.findAnyOf(strings = listOf(editSearch?.text.toString()), startIndex = 0, ignoreCase = false)

                    if(prViborCategor[0] && prViborCategor[1] && prViborCategor[2] && (prinad != null)) {
                        filterList.add(i)
                    }
                    else
                    {
                        if (prViborCategor[0] && prViborCategor[1] && (prinad != null)){
                            if((i.category == "Зеленый чай")||(i.category == "Черный чай")){
                                filterList.add(i)
                            }
                        }
                        else{
                            if (prViborCategor[0] && prViborCategor[2] && (prinad != null)) {
                                if((i.category == "Зеленый чай")||(i.category == "Прочее")){
                                    filterList.add(i)
                                }
                            }
                            else
                            {
                                if (prViborCategor[1] && prViborCategor[2] && (prinad != null)) {
                                    if((i.category == "Черный чай")||(i.category == "Прочее")){
                                        filterList.add(i)
                                    }
                                }
                                else{
                                    if (prViborCategor[0] && (prinad != null)) {
                                        if(i.category == "Зеленый чай"){
                                            filterList.add(i)
                                        }
                                    }
                                    else{
                                        if (prViborCategor[1] && (prinad != null)) {
                                            if(i.category == "Черный чай"){
                                                filterList.add(i)
                                            }
                                        }
                                        else{
                                            if (prViborCategor[2] && (prinad != null)) {
                                                if(i.category == "Прочее"){
                                                    filterList.add(i)
                                                }
                                            }
                                            else{
                                                if (prinad != null) {
                                                        filterList.add(i)
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                }

                myRecyclerView!!.adapter=AdapterForAssortiScreen(filterList,requireContext())
                editSearch?.clearFocus()
                return handled
            }
        }
        return handled
    }
}