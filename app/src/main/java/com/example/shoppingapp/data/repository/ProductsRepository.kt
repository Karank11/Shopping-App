package com.example.shoppingapp.data.repository

import android.content.Context
import com.example.shoppingapp.data.database.ShoppingDatabase
import com.example.shoppingapp.data.models.Product
import com.example.shoppingapp.data.network.ProductsApiService
import com.example.shoppingapp.utils.Utils
import com.example.shoppingapp.utils.Utils.mapToProductEntities
import com.example.shoppingapp.utils.Utils.mapToProducts
import javax.inject.Inject

class ProductsRepository @Inject constructor (
    private val context: Context,
    private val productsApiService: ProductsApiService,
    private val shoppingDatabase: ShoppingDatabase
) {

    suspend fun getAllProducts(): List<Product> {
        if (!Utils.isNetworkAvailable(context)) {
            val productEntities = shoppingDatabase.productsDao.getAllProducts()
            return productEntities.mapToProducts()
        }
        val products = productsApiService.getAllProducts()
        shoppingDatabase.productsDao.insertProducts(products.mapToProductEntities())
        return products
    }
}