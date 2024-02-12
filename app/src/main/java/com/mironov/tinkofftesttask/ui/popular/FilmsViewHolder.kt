package com.mironov.tinkofftesttask.ui.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.mironov.tinkofftesttask.R
import com.mironov.tinkofftesttask.databinding.FilmItemBinding
import com.mironov.tinkofftesttask.domain.entity.FilmInfo
import com.mironov.tinkofftesttask.ui.utils.formatFilmDescription
import com.squareup.picasso.Picasso

class FilmsViewHolder(
    parent: ViewGroup,
    private val onClick: (Int) -> Unit,
    private val onLongClick: (FilmInfo) -> Unit,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.film_item, parent, false)
) {

    private val binding = FilmItemBinding.bind(itemView)

    fun bind(film: FilmInfo?) {
        film?.let {
            with(binding) {
                name.text = film.name
                genres.text = formatFilmDescription(film.genres.first(), film.year)
                Picasso.get().load(film.posterUrl).into(poster)
                favourite.isVisible = false
            }

            itemView.setOnClickListener {
                onClick(film.id)
            }

            itemView.setOnLongClickListener {
                onLongClick(film)
                true
            }
        }
    }
}