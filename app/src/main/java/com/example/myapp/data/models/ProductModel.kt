package com.example.myapp.data.models

import java.io.Serializable

data class ProductModel(
    val id: String,
    val name: String,
    val detailText: String,
    val picture: String,
    val price: String,
    val baseUnit: String,
    val nutritional: String,
    val composition: String,
    val term_conditions: String,
    val manufacturer: String,
): Serializable
