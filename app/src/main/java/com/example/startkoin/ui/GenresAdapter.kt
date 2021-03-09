package com.example.startkoin.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.startkoin.R
import com.example.startkoin.model.Genres
import kotlinx.android.synthetic.main.item_genres.view.*

class GenresAdapter : PagedListAdapter<Genres.Genres, GenresAdapter.GenresHolder>(GenresDiffCallback)
{

    companion object {
        val GenresDiffCallback = object : DiffUtil.ItemCallback<Genres.Genres>() {
            override fun areItemsTheSame(oldItem: Genres.Genres, newItem: Genres.Genres): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Genres.Genres, newItem: Genres.Genres): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class GenresHolder (view: View): RecyclerView.ViewHolder(view) {
        fun onBind(genres: Genres.Genres?) {
            itemView.tvTypeProject.text=genres?.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_genres,parent,false)
        return GenresHolder(itemView)
    }

    override fun onBindViewHolder(holder: GenresHolder, position: Int){
        holder.onBind(getItem(position))
    }
}


