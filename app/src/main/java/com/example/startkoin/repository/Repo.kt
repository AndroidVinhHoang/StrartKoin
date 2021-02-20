package com.example.startkoin.repository

import com.example.startkoin.model.Genres
import io.reactivex.Observable

interface Repo {
    fun get(): Observable<Genres>
}