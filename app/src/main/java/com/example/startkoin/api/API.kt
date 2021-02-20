package com.example.startkoin.api

import com.example.startkoin.model.Genres
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("list")
    fun getGenres(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Observable<Genres>
}