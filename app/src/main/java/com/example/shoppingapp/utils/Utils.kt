package com.example.shoppingapp.utils

import android.content.Context
import android.net.ConnectivityManager
import com.example.shoppingapp.data.database.ProductEntity
import com.example.shoppingapp.data.models.Product
import com.example.shoppingapp.data.models.Rating

object Utils {
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    private fun ProductEntity.mapToProduct(): Product {
        return Product(
            image = this.image,
            price = this.price,
            description = this.description,
            id = this.id,
            title = this.title,
            category = this.category,
            rating = Rating(rate = this.rate, count = this.count)
        )
    }

    fun List<ProductEntity>.mapToProducts(): List<Product> {
        return this.map { it.mapToProduct() }
    }

    fun List<Product>.mapToProductEntities(): List<ProductEntity> {
        return this.map { it.mapToProductEntity() }
    }

    private fun Product.mapToProductEntity(): ProductEntity {
        return ProductEntity(
            image = this.image,
            price = this.price,
            description = this.description,
            id = this.id,
            title = this.title,
            category = this.category,
            rate = this.rating.rate,
            count = this.rating.count
        )
    }
}