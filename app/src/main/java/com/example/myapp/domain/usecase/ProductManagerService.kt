package com.example.myapp.domain.usecase

import com.example.myapp.data.models.ProductModel
import com.example.myapp.data.repository.ProductService
import com.example.myapp.data.service.RetrofitService
import retrofit2.Response

class ProductManagerService {
    val response = RetrofitService().shopRequest().create(ProductService::class.java)

    suspend fun getProducts(blockId: String): Response<ArrayList<ProductModel>> {
        return response.getProducts(blockId)
    }
}