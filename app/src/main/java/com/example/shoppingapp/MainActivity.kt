package com.example.shoppingapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.ui.recyclerview.ProductListAdapter
import com.example.shoppingapp.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val adapter = ProductListAdapter()
        recyclerView.adapter = adapter

        val viewModel: MainViewModel by viewModels()

        viewModel.products.observe(this) { products ->
            adapter.submitList(products)
        }
        viewModel.getAllProducts()

    }
}