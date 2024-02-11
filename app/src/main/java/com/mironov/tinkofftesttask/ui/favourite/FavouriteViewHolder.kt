package com.mironov.tinkofftesttask.ui.favourite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mironov.tinkofftesttask.R
import com.mironov.tinkofftesttask.databinding.FilmItemBinding
import com.mironov.tinkofftesttask.domain.entity.FilmInfo
import com.mironov.tinkofftesttask.ui.utils.formatFilmDescription
import com.squareup.picasso.Picasso

class FavouriteViewHolder(
    parent: ViewGroup,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.film_item, parent, false)
) {

    private val binding = FilmItemBinding.bind(itemView)

    fun bind(film: FilmInfo) {
        with(binding) {
            name.text = film.name
            genres.text = formatFilmDescription(film.genres.first(), film.year)
            Picasso.get().load(film.posterUrl).into(poster)
        }
    }
}