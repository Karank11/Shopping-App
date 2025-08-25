package com.example.shoppingapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.data.network.ProductDto
import com.example.shoppingapp.data.repository.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor (private val productsRepository: ProductsRepository): ViewModel() {
    private val _products = MutableLiveData<List<ProductDto>>()
    val products: LiveData<List<ProductDto>>
        get() = _products

    fun getAllProducts() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val products = productsRepository.getAllProducts()
                _products.postValue(products)
            }
        }
    }

}