package com.example.shoppingapp.data.network

import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface ProductsApiService {

    @GET("products")
    suspend fun getAllProducts(): List<ProductDto>

    @POST("products")
    suspend fun addProduct(product: ProductDto): ProductDto

    @GET("products/{id}")
    suspend fun getProductById(id: Int): ProductDto

    @PUT("products/{id}")
    suspend fun updateProduct(id: Int, product: ProductDto): ProductDto

    @DELETE("products/{id}")
    suspend fun deleteProduct(id: Int): ProductDto
}