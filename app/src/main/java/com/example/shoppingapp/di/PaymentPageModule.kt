package com.example.shoppingapp.di

import com.example.shoppingapp.BuildConfig
import com.razorpay.Checkout
import dagger.Module
import dagger.Provides

@Module
class PaymentPageModule {

    @Provides
    fun provideCheckout(): Checkout {
        val checkout = Checkout()
        checkout.setKeyID(BuildConfig.RAZORPAY_KEY_ID)
        return checkout
    }
}