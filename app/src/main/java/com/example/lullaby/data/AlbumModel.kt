package com.example.lullaby.data

import kotlinx.serialization.Serializable

@Serializable
data class AlbumModel(
    val title: String? = null,
    val artist: Artist? = null,
    val releaseYear: Int? = null,
    val imgUrl: String? = null,
    val category: String? = null,
    val listOfSongs: ArrayList<Song>? = null
)
