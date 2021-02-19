package com.example.startkoin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.startkoin.ui.GenresViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val genresViewModel: GenresViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        genresViewModel.getGenres()
        genresViewModel.genres.observe(this, Observer {
            Log.d("aaaaaaaa",it.toString())
        })
    }
}