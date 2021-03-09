package com.example.startkoin.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.startkoin.api.API
import com.example.startkoin.api.ApiClient
import com.example.startkoin.model.Genres
import com.example.startkoin.repository.GenresDataSourceFactory
import com.example.startkoin.repository.RepoIpm
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GenresViewModel : ViewModel() {

    var genresList: LiveData<PagedList<Genres.Genres>>

    private val compositeDisposable = CompositeDisposable()

    private val pageSize = 5

    private val sourceFactory: GenresDataSourceFactory

    init {
        sourceFactory = GenresDataSourceFactory(ApiClient.getService(), compositeDisposable)
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        genresList = LivePagedListBuilder<Long, Genres.Genres>(sourceFactory, config).build()
    }

    fun getLoading() = Transformations.switchMap<RepoIpm, Boolean>(
        sourceFactory.genres) { it.isLoading }

    fun getRefreshState() = Transformations.switchMap<RepoIpm, Boolean>(
        sourceFactory.genres) { it.isRefresh }

    fun reset() {
        sourceFactory.genres.value?.invalidate()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}