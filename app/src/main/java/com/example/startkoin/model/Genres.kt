package com.example.startkoin.model

data class Genres(
    val genres : List<Genres>
) {
    data class Genres(
    val id: Int,
    val name: String)
}