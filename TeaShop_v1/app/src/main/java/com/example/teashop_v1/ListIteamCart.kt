package com.example.teashop_v1

data class ListIteamCart (
   var name_id:String,
   var weight_id:String,
   var quantityProduct_id:String,
   var price_id:Int,
   var imgProduct_id:String? = "",
   var imgPlus_id:Int,
   var imgMinus_id:Int
)