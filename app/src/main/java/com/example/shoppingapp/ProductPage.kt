package com.example.shoppingapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.shoppingapp.data.models.Product
import com.example.shoppingapp.utils.Constants.TAG
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject
import javax.inject.Inject

@AndroidEntryPoint
class ProductPage : AppCompatActivity(), PaymentResultListener {

    @Inject
    lateinit var checkout: Checkout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_page)
        val productImage: ImageView = findViewById(R.id.product_image)
        val productCategory: TextView = findViewById(R.id.product_category)
        val productTitle: TextView = findViewById(R.id.product_title)
        val productPrice: TextView = findViewById(R.id.product_price)
        val productRating: RatingBar = findViewById(R.id.product_rating_bar)
        val productRatingCount: TextView = findViewById(R.id.product_rating_count)
        val buyNowButton: Button = findViewById(R.id.buy_now_button)

        Checkout.preload(applicationContext)

        val product = intent.getParcelableExtra<Product>("product")
        product?.let {
            productTitle.text = it.title
            productPrice.text = buildString {
                append("₹")
                append(it.price)
            }
            productRating.rating = it.rating.rate.toFloat()
            productRatingCount.text = buildString {
                append("(")
                append(it.rating.count)
                append(")")
            }
            productCategory.text = it.category

            Glide.with(this)
                .load(it.image)
                .into(productImage)
        }

        buyNowButton.setOnClickListener {
            startPayment(product?.price)
        }

    }

    private fun startPayment(price: Double?) {
        try {
            val options = JSONObject()
            options.put("name", "Shopping com.")
            options.put("description", "Payment process")
            options.put("currency", "INR")
            options.put("amount", price?.times(100))

            val retryObj = JSONObject()
            retryObj.put("enabled", true)
            retryObj.put("max_count", 4)
            options.put("retry", retryObj)

            checkout.open(this, options)
        } catch (e: Exception) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }

    override fun onPaymentSuccess(p0: String?) {
        Log.d(TAG, "onPaymentSuccess: $p0")
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        Log.d(TAG, "onPaymentError: $p0")
    }
}