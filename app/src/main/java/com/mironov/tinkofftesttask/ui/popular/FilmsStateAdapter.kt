package com.mironov.tinkofftesttask.ui.popular

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mironov.tinkofftesttask.databinding.ErrorItemBinding
import com.mironov.tinkofftesttask.databinding.LoadingItemBinding

class FilmsStateAdapter(
) : LoadStateAdapter<FilmsStateAdapter.ItemViewHolder>() {

    private companion object {
        private const val ERROR = 1
        private const val LOADING = 0
    }

    override fun getStateViewType(loadState: LoadState): Int =
        when (loadState) {
            is LoadState.Error -> ERROR
            LoadState.Loading -> LOADING
            is LoadState.NotLoading -> error("not supported")
        }

    override fun onBindViewHolder(holder: ItemViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ItemViewHolder {
        return when (loadState) {
            is LoadState.Error -> {
                Log.d("FilmsStateAdapter", "ERROR")
                ErrorViewHolder(
                    layoutInflater = LayoutInflater.from(parent.context),
                )
            }

            LoadState.Loading -> {
                LoadingViewHolder(layoutInflater = LayoutInflater.from(parent.context))
            }

            is LoadState.NotLoading -> {
                error("not supported")
            }
        }
    }

    abstract class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        abstract fun bind(loadState: LoadState)
    }

    class ErrorViewHolder internal constructor(
        private val binding: ErrorItemBinding,
    ) : ItemViewHolder(binding.root) {

        override fun bind(loadState: LoadState) {
            binding.tryAgain.setOnClickListener {
            }
        }

        companion object {
            operator fun invoke(
                layoutInflater: LayoutInflater,
                parent: ViewGroup? = null,
                attachToRoot: Boolean = false,
            ): ErrorViewHolder {
                return ErrorViewHolder(
                    ErrorItemBinding.inflate(
                        layoutInflater,
                        parent,
                        attachToRoot
                    )
                )
            }
        }
    }

    class LoadingViewHolder internal constructor(
        private val binding: LoadingItemBinding
    ) : ItemViewHolder(binding.root) {

        override fun bind(loadState: LoadState) {}

        companion object {
            operator fun invoke(
                layoutInflater: LayoutInflater,
                parent: ViewGroup? = null,
                attachToRoot: Boolean = false
            ): LoadingViewHolder {
                return LoadingViewHolder(
                    LoadingItemBinding.inflate(
                        layoutInflater,
                        parent,
                        attachToRoot
                    )
                )
            }
        }
    }
}