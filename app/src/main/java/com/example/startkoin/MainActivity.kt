package com.example.startkoin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.startkoin.model.Genres
import com.example.startkoin.ui.GenresAdapter
import com.example.startkoin.ui.GenresViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

        private val viewModel by lazy { ViewModelProviders.of(this).get(GenresViewModel::class.java) }
        private val genresAdapter by lazy {
            GenresAdapter {

            }
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            srPaging.setOnRefreshListener { viewModel.reset() }
            initAdapter()
        }

        private fun initAdapter() {
            usersRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false)
            usersRecyclerView.adapter = genresAdapter
            viewModel.genresList.observe(this, Observer<PagedList<Genres.Genres>> { genresAdapter.submitList(it) })
            viewModel.getLoading().observe(this,
                Observer<Boolean> {  genresAdapter.setLoading(it) })
            viewModel.getRefreshState().observe(this, Observer<Boolean> {
                srPaging.isRefreshing = it?:false
            })
        }
}