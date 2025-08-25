package com.example.shoppingapp.ui.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shoppingapp.R
import com.example.shoppingapp.data.models.Product

class ProductListAdapter: ListAdapter<Product, ProductListAdapter.ProductItemViewHolder>(ProductItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_product_card, parent, false)
        return ProductItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.productTitle.text = item.title
        holder.productPrice.text = buildString {
            append("$")
            append(item.price)
        }
        holder.productRating.rating = item.rating.rate.toFloat()

        holder.productRatingCount.text = buildString {
            append("(")
            append(item.rating.count)
            append(")")
        }
        holder.productCategory.text = item.category

        Glide.with(holder.productImage.context)
            .load(item.image)
            .into(holder.productImage)
    }

    class ProductItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.product_image)
        val productTitle: TextView = itemView.findViewById(R.id.product_title)
        val productPrice: TextView = itemView.findViewById(R.id.product_price)
        val productRating: RatingBar = itemView.findViewById(R.id.product_rating_bar)
        val productRatingCount: TextView = itemView.findViewById(R.id.product_rating_count)
        val productCategory: TextView = itemView.findViewById(R.id.product_category)
    }

    class ProductItemDiffCallback: DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}