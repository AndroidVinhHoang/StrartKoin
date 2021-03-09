package com.example.startkoin.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.startkoin.api.API
import com.example.startkoin.model.Genres
import io.reactivex.disposables.CompositeDisposable

class GenresDataSourceFactory(private val api: API, private val compositeDisposable: CompositeDisposable):
    DataSource.Factory<Long,Genres.Genres>() {
     val genres = MutableLiveData<RepoIpm>()
    override fun create(): DataSource<Long, Genres.Genres> {
        val dataSource =RepoIpm(api,compositeDisposable)
        genres.postValue(dataSource)
        return dataSource
    }
}