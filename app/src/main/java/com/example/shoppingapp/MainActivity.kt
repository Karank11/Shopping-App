package com.example.shoppingapp

import android.content.Intent
import android.Manifest
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.data.models.Product
import com.example.shoppingapp.ui.viewmodel.ViewModelFactory
import com.example.shoppingapp.ui.recyclerview.ProductListAdapter
import com.example.shoppingapp.ui.viewmodel.MainViewModel
import com.google.firebase.messaging.FirebaseMessaging
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(), { isGranted ->
            if (isGranted) {
                // TODO: FCM SDK can post notifications
            } else {
                // TODO: no notifications
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        requestPermission()

        /** getFCMToken() is only required in the case when you sending notifications from your internal server to Firebase
         * and then getting on apps via firebase**/
        getFCMToken()

        /** subscribe to the user Cohorts that are created on Firebase console**/
        subscribeToFCMGeneral()


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

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PERMISSION_GRANTED) {
                // can display notification

            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                showRationalDialog(
                    "Notifications Required",
                    "This app needs notification permission to send you important updates. Please grant the permission."
                ) { requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS) }
            } else {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private fun showRationalDialog(title: String, message: String, onPositive: ()->Unit) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Ok") { _, _ ->
                onPositive()
            }
            .setNeutralButton("Cancel", null)
            .show()
    }

    private fun getFCMToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener {task ->
            if (!task.isSuccessful) {
                return@addOnCompleteListener
            }

            val token = task.result
        }
    }

    private fun subscribeToFCMGeneral() {
        FirebaseMessaging.getInstance().subscribeToTopic("general")
            .addOnCompleteListener {task ->
                var msg = "Subscribed for general notifications"
                if (!task.isSuccessful) {
                    msg = "Subscribe Failed for general notifications"
                }
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            }
    }
}