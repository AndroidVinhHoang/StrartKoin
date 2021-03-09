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

}