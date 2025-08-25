package com.example.shoppingapp.data.network

data class ProductDto(
    val image: String = "",
    val price: Double = 0.0,
    val rating: RatingDto,
    val description: String = "",
    val id: Int = 0,
    val title: String = "",
    val category: String = ""
)

data class RatingDto(
    val rate: Double = 0.0,
    val count: Int = 0
)