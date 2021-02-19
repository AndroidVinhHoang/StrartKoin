package com.example.startkoin.repository

import com.example.startkoin.model.ListGenres
import io.reactivex.Observable
import io.reactivex.Observer

interface Repo {
    fun get(): Observable<ListGenres>
}