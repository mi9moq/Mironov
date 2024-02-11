package com.mironov.tinkofftesttask.ui.favourite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mironov.tinkofftesttask.ui.avtivity.MainActivity
import com.mironov.tinkofftesttask.databinding.FragmentFavouriteFilmsBinding
import com.mironov.tinkofftesttask.domain.entity.FilmInfo
import com.mironov.tinkofftesttask.presentation.ViewModelFactory
import com.mironov.tinkofftesttask.presentation.favourite.FavouriteState
import com.mironov.tinkofftesttask.presentation.favourite.FavouriteViewModel
import com.mironov.tinkofftesttask.ui.utils.collectStateFlow
import javax.inject.Inject

class FavouriteFragment : Fragment() {

    companion object {

        fun newInstance(): FavouriteFragment = FavouriteFragment()
    }

    private var _binding: FragmentFavouriteFilmsBinding? = null
    private val binding: FragmentFavouriteFilmsBinding
        get() = _binding!!

    private val component by lazy {
        (requireActivity() as MainActivity).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[FavouriteViewModel::class.java]
    }

    private val favouriteAdapter by lazy {
        FavouriteAdapter()
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteFilmsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun observeViewModel() {
        collectStateFlow(viewModel.state, ::applyState)
    }

    private fun applyState(state: FavouriteState) {
        when (state) {
            FavouriteState.Initial -> Unit
            is FavouriteState.Content -> {
                applyContentState(state.content)
            }
        }
    }

    private fun applyContentState(content: List<FilmInfo>) {
        binding.filmsList.adapter = favouriteAdapter
        favouriteAdapter.submitList(content)
    }
}