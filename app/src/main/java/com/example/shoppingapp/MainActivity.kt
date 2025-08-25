package com.example.shoppingapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.di.ViewModelFactory
import com.example.shoppingapp.ui.recyclerview.ProductListAdapter
import com.example.shoppingapp.ui.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val adapter = ProductListAdapter()
        recyclerView.adapter = adapter
        val appComponent = (application as ShoppingApplication).appComponent
        appComponent.inject(this)
        val viewModel: MainViewModel by viewModels {
            viewModelFactory
        }

        viewModel.products.observe(this) { products ->
            adapter.submitList(products)
        }
        viewModel.getAllProducts()

    }
}