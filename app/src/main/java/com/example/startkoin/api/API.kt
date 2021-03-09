package com.example.startkoin.api

import com.example.startkoin.model.Genres
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("movie/popular")
    fun getGenres(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") perPage: Int
    ): Observable<Genres>
}