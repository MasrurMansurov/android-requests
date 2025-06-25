package com.example.myapp.presentation.product

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.myapp.R
import com.example.myapp.data.models.ProductModel
import com.example.myapp.databinding.ActivityProductBinding

class ProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductBinding
    private lateinit var product: ProductModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val bundle: Bundle? = intent.extras
        product = bundle?.getSerializable("product") as ProductModel
        setupView()
    }

    private fun setupView() = with(binding) {
        Glide.with(this@ProductActivity)
            .load("https://paykar.shop" + product.picture)
            .into(imageOfProduct)
        nameOfProduct.text = product.name
        unitOfProduct.text = "Цена за 1${product.baseUnit}"
        priceOfProduct.text = product.price + " сом."
        detailTextOfProduct.text = product.detailText
    }
}