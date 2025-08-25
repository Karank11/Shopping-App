package com.example.shoppingapp.data.repository

import com.example.shoppingapp.data.models.Product
import com.example.shoppingapp.data.network.ProductsApiService
import javax.inject.Inject

class ProductsRepository @Inject constructor (
    private val productsApiService: ProductsApiService
) {

    suspend fun getAllProducts(): List<Product> {
        return productsApiService.getAllProducts()
    }
}