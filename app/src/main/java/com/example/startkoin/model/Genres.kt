package com.example.startkoin.model

data class Genres(
    val results : List<Genres>
) {
    data class Genres(
        val id: Long = 0L,
        var title: String = "")
}