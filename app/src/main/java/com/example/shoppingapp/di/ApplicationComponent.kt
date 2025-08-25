package com.example.shoppingapp.di

import com.example.shoppingapp.data.network.ProductsApiService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {

    fun getProductsApiService(): ProductsApiService
}