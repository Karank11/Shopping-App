package com.example.shoppingapp.data.database
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProductEntity::class], version = 1)
abstract class ShoppingDatabase: RoomDatabase() {
    abstract val productsDao: ProductsDao
}