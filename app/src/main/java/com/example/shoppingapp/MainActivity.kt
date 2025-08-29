package com.example.shoppingapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.data.models.Product
import com.example.shoppingapp.ui.viewmodel.ViewModelFactory
import com.example.shoppingapp.ui.recyclerview.ProductListAdapter
import com.example.shoppingapp.ui.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val adapter = ProductListAdapter {
            onProductClick(it)
        }
        recyclerView.adapter = adapter
        val appComponent = (application as ShoppingApplication).appComponent
        appComponent.inject(this)
        val viewModel: MainViewModel by viewModels {
            viewModelFactory
        }

        viewModel.isLoading.observe(this) {
            it?.let {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }
        }

        viewModel.products.observe(this) { products ->
            adapter.submitList(products)
        }
        viewModel.getAllProducts()

    }

    private fun onProductClick(product: Product) {
        val intent = Intent(this, ProductPage::class.java)
        intent.putExtra("product", product)
        startActivity(intent)
    }
}