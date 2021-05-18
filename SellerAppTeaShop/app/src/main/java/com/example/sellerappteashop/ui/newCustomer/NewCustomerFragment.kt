package com.example.sellerappteashop.ui.newCustomer

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sellerappteashop.NavigationActivity
import com.example.sellerappteashop.NewCustomerModel
import com.example.sellerappteashop.R
import com.google.firebase.database.FirebaseDatabase

class NewCustomerFragment : Fragment() {

    private lateinit var newCustomerViewModel: NewCustomerViewModel
    //установка конекста к БД
    val dataBase = FirebaseDatabase.getInstance()
    val myRef = dataBase.getReference("Users")

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        newCustomerViewModel =
                ViewModelProvider(this).get(NewCustomerViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_new_customer, container, false)

        val nameCustomer = root.findViewById<EditText>(R.id.nameCustomer)
        val surnameCustomer = root.findViewById<EditText>(R.id.surnameCustomer)
        val patronymicCustomer = root.findViewById<EditText>(R.id.patronymicCustomer)
        val phoneCustomer = root.findViewById<EditText>(R.id.phoneCustomer)
        val buttonSave = root.findViewById<Button>(R.id.buttonSaveCustomer)

        buttonSave.setOnClickListener(){
            val goodDataCustomer:Boolean = (nameCustomer.text.toString() != "") &&
                                           (surnameCustomer.text.toString() != "") &&
                                           (patronymicCustomer.text.toString() != "") &&
                                           (phoneCustomer.text.toString() != "")
            if (goodDataCustomer){

                var customer = NewCustomerModel()
                customer.name = nameCustomer.text.toString()
                customer.surname = surnameCustomer.text.toString()
                customer.patronymic = patronymicCustomer.text.toString()
                customer.phone = phoneCustomer.text.toString()

                myRef.push().setValue(customer)
                Toast.makeText(activity, "Новый покупатель успешно сохранен в системе", Toast.LENGTH_SHORT).show()
                val testIntent = Intent(context, NavigationActivity::class.java)
                this.startActivity(testIntent)
            }
            else{
                Toast.makeText(activity, "Ошибка введеных данных покупателя, проверьте поля ввода.", Toast.LENGTH_SHORT).show()
            }
        }

        return root
    }
}