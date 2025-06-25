package com.example.myapp.domain.usecase

import com.example.myapp.data.models.AuthorizationModel
import com.example.myapp.data.repository.AuthorizationService
import com.example.myapp.data.service.RetrofitService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Response

class AuthorizationManagerService {
    private val response = RetrofitService().supportRequest().create(AuthorizationService::class.java)

    suspend fun authorization(phone: String, password: String): Response<AuthorizationModel> {
        val jsonObject = JSONObject()
        jsonObject.put("phone", phone)
        jsonObject.put("password", password)
        val requestBody = jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())
        return response.authorization(requestBody)
    }
}