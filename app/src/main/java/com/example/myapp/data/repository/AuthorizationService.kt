package com.example.myapp.data.repository

import com.example.myapp.data.models.AuthorizationModel
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthorizationService {
    @POST("manager/users/login.php")
    suspend fun authorization(@Body requestBody: RequestBody): Response<AuthorizationModel>
}