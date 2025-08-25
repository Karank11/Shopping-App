package com.example.shoppingapp.di

import android.content.Context
import androidx.room.Room
import com.example.shoppingapp.data.database.ShoppingDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): ShoppingDatabase {
        return Room.databaseBuilder(
            context,
            ShoppingDatabase::class.java,
            "shopping_database")
            .fallbackToDestructiveMigration()
            .build()
    }
}