package com.example.startkoin.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.startkoin.model.ListGenres
import com.example.startkoin.repository.Repo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GenresViewModel(private val repo: Repo) : ViewModel() {
    private val movieGenres = MutableLiveData<ListGenres>()
    val genres: LiveData<ListGenres> = movieGenres
    private val composite = CompositeDisposable()

    fun getGenres() {
        composite.add(
            repo.get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    movieGenres.postValue(it)
                }, { ex ->
                    Log.d("vvvv", ex?.printStackTrace().toString())
                    ex?.printStackTrace()
                })
        )
    }
}