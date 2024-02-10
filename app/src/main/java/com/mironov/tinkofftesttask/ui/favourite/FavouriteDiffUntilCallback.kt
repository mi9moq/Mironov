package com.mironov.tinkofftesttask.ui.favourite

import androidx.recyclerview.widget.DiffUtil
import com.mironov.tinkofftesttask.domain.entity.FilmInfo

class FavouriteDiffUntilCallback: DiffUtil.ItemCallback<FilmInfo>() {

    override fun areItemsTheSame(oldItem: FilmInfo, newItem: FilmInfo): Boolean =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: FilmInfo, newItem: FilmInfo): Boolean =
        oldItem == newItem
}