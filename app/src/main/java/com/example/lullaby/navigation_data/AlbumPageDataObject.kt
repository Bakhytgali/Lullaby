package com.example.lullaby.navigation_data

import com.example.lullaby.data.AlbumModel
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class AlbumPageDataObject(
    val userID: String,
    val email: String,
    val album: AlbumModel
)
