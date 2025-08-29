package com.example.shoppingapp.di

import com.example.shoppingapp.BuildConfig
import com.razorpay.Checkout
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class PaymentPageModule {

    @Provides
    fun provideCheckout(): Checkout {
        val checkout = Checkout()
        checkout.setKeyID(BuildConfig.RAZORPAY_KEY_ID)
        return checkout
    }
}