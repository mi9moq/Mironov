package com.mironov.tinkofftesttask.ui.popular

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ListAdapter
import com.mironov.tinkofftesttask.domain.entity.FilmInfo

class FilmsAdapter(
    private val onClick: (Int) -> Unit,
    private val onLongClick: (FilmInfo) -> Unit,
) : PagingDataAdapter<FilmInfo, FilmsViewHolder>(FilmItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder =
        FilmsViewHolder(parent, onClick, onLongClick)

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}