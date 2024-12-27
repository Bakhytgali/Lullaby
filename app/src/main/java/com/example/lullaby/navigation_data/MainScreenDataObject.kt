package com.example.lullaby.navigation_data

import kotlinx.serialization.Serializable

@Serializable
data class MainScreenDataObject(
    val userID: String,
    val email: String
)
