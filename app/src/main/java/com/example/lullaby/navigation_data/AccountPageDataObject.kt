package com.example.lullaby.navigation_data

import kotlinx.serialization.Serializable

@Serializable
data class AccountPageDataObject(
    val userID: String,
    val email: String
)
