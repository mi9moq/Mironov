package com.mironov.tinkofftesttask.ui.favourite

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.mironov.tinkofftesttask.domain.entity.FilmInfo

class FavouriteAdapter: ListAdapter<FilmInfo, FavouriteViewHolder>(FavouriteDiffUntilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder =
        FavouriteViewHolder(parent)

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}