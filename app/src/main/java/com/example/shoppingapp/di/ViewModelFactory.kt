package com.example.shoppingapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject


class ViewModelFactory @Inject constructor(
    private val map: Map<Class<*>, @JvmSuppressWildcards ViewModel>
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (!map.containsKey(modelClass)) {
            throw IllegalArgumentException("Unknown model class: $modelClass")
        }
        return map[modelClass] as T
    }
}
