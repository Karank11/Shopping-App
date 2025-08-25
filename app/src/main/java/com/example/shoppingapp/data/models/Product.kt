package com.example.shoppingapp.data.models

data class Product(
    val image: String = "",
    val price: Double = 0.0,
    val rating: Rating,
    val description: String = "",
    val id: Int = 0,
    val title: String = "",
    val category: String = ""
)

data class Rating(
    val rate: Double = 0.0,
    val count: Int = 0
)