package com.example.myapp.domain.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapp.R
import com.example.myapp.data.models.ProductModel
import com.example.myapp.databinding.ProductItemBinding
import com.example.myapp.presentation.product.ProductActivity

class ProductAdapter(val context: Context, val productList: ArrayList<ProductModel>):
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ProductItemBinding.bind(view)

        fun bind(product: ProductModel) = with(binding) {

            Glide.with(context)
                .load("https://paykar.shop" + product.picture)
                .into(binding.productImage)

            productName.text = product.name
            productUnit.text = "Цена за 1${product.baseUnit}"
            productPrice.text = product.price + "сом."

            itemView.setOnClickListener {
                val intent = Intent(context, ProductActivity::class.java)
                intent.putExtra("product", product)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ProductViewHolder,
        position: Int
    ) {
        return holder.bind(productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }

}