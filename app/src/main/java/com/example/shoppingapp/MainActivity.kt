package com.example.shoppingapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.shoppingapp.utils.Constants.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val appComponent = (application as ShoppingApplication).appComponent

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val list = appComponent.getProductsApiService().getAllProducts()
                Log.d(TAG, list.toString())
            }
        }

    }
}