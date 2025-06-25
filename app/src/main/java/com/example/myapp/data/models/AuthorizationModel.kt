package com.example.myapp.data.models

data class AuthorizationModel(
    val status: String,
    val message: String,
    val confirmed: String,
    val staffData: StaffModel
)

data class StaffModel(
    val id: String,
    val create_date: String,
    val first_name: String,
    val last_name: String,
    val phone: String,
    val position: String,
    val status: String,
    val subdivision: String,
    val password: String,
    val confirmed: String
)
