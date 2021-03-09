package com.example.startkoin.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import com.example.startkoin.api.API
import com.example.startkoin.api.ApiClient
import com.example.startkoin.common.Constants
import com.example.startkoin.model.Genres
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

class RepoIpm(private val githubService: API,
              private val compositeDisposable: CompositeDisposable
) : ItemKeyedDataSource<Long, Genres.Genres>() {
    val isLoading = MutableLiveData<Boolean>()
    val isRefresh = MutableLiveData<Boolean>()
    override fun getKey(item: Genres.Genres): Long {
        return item.id.toLong()
    }

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Genres.Genres>
    ) {
        isLoading.postValue(true)
        isRefresh.postValue(true)
        compositeDisposable.add(githubService.getGenres(Constants.API_KEY,Constants.LANGUAGE, params.requestedLoadSize)
            .subscribe({
                isLoading.postValue(false)
                isRefresh.postValue(false)
                callback.onResult(it.results)
                Log.d("vvvvvvvvv",it.results.toString())
            }, {
                isLoading.postValue(false)
                isRefresh.postValue(false)
            }))
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Genres.Genres>) {
        isLoading.postValue(true)
        compositeDisposable.add(
            githubService.getGenres(Constants.API_KEY,Constants.LANGUAGE, params.requestedLoadSize)
                .subscribe({ genres ->
                    isLoading.postValue(false)
                    callback.onResult(genres.results)
                }, {
                    isLoading.postValue(false)
                }))
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Genres.Genres>) {
         Log.d("dfsddsd","ddddddddddddddd")
    }

}