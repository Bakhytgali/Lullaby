package com.example.lullaby.data

import kotlinx.serialization.Serializable

@Serializable
data class Song(
    val title: String? = null,
    val artist: String? = null,
    val featuringArtists: ArrayList<Artist>? = null,
    val albumName: String? = null
)
