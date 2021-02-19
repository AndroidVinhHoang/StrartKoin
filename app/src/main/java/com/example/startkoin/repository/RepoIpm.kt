package com.example.startkoin.repository

import com.example.startkoin.api.API
import com.example.startkoin.common.Constants
import com.example.startkoin.model.ListGenres
import io.reactivex.Observable

class RepoIpm(private val api: API) : Repo {
    override fun get(): Observable<ListGenres> {
        return api.getGenres(Constants.API_KEY,Constants.LANGUAGE)
    }
}