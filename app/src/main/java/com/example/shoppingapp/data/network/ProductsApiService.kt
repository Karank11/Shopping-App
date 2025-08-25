package com.example.shoppingapp.data.network

import com.example.shoppingapp.data.models.Product
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface ProductsApiService {

    @GET("products")
    suspend fun getAllProducts(): List<Product>

    @POST("products")
    suspend fun addProduct(product: Product): Product

    @GET("products/{id}")
    suspend fun getProductById(id: Int): Product

    @PUT("products/{id}")
    suspend fun updateProduct(id: Int, product: Product): Product

    @DELETE("products/{id}")
    suspend fun deleteProduct(id: Int): Product
}