package com.example.shoppingapp

import android.app.Application
import com.example.shoppingapp.di.ApplicationComponent
import com.example.shoppingapp.di.DaggerApplicationComponent

class ShoppingApplication: Application() {
    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this@ShoppingApplication)
    }
}