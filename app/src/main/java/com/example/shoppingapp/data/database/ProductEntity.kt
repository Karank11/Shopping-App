package com.example.shoppingapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products_table")
class ProductEntity (
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    val image: String = "",
    val price: Double = 0.0,
    val description: String = "",
    val title: String = "",
    val category: String = "",
    var rate: Double = 0.0,
    var count: Int = 0
)