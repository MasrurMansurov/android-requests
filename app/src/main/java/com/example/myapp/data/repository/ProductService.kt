package com.example.myapp.data.repository

import com.example.myapp.data.models.ProductModel
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

interface ProductService {
    @GET("bitrix/api/products.php")
    suspend fun getProducts(@Query("BlockId") blockId: String): Response<ArrayList<ProductModel>>
}