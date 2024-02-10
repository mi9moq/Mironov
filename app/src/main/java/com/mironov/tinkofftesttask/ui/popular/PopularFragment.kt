package com.mironov.tinkofftesttask.ui.popular

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import com.mironov.tinkofftesttask.MainActivity
import com.mironov.tinkofftesttask.databinding.FragmentPopularFilmsBinding
import com.mironov.tinkofftesttask.domain.entity.FilmInfo
import com.mironov.tinkofftesttask.presentation.ViewModelFactory
import com.mironov.tinkofftesttask.presentation.popular.PopularState
import com.mironov.tinkofftesttask.presentation.popular.PopularViewModel
import com.mironov.tinkofftesttask.ui.utils.collectStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularFragment : Fragment() {

    companion object {

        fun newInstance(): PopularFragment = PopularFragment()
    }

    private val component by lazy {
        (requireActivity() as MainActivity).component
    }

    private var _binding: FragmentPopularFilmsBinding? = null
    private val binding: FragmentPopularFilmsBinding
        get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[PopularViewModel::class.java]
    }

    private val filmsAdapter by lazy {
        FilmsAdapter(
            onClick = { id ->
                viewModel.openDetails(id)
            },
            onLongClick = { film ->
                viewModel.saveFavourite(film)
            }
        )
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
        _binding = FragmentPopularFilmsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addClickListeners()
        observeViewModel()
    }

    private fun addClickListeners() {
        binding.tryAgain.setOnClickListener {
            viewModel.loadFilms()
        }
    }

    private fun observeViewModel() {
        collectStateFlow(viewModel.state, ::applyState)
    }

    private fun applyState(state: PopularState) {
        when (state) {
            PopularState.Initial -> Unit
            is PopularState.Content -> applyContentState(state.content)
            PopularState.Loading -> applyLoadingState()
            PopularState.Error -> applyErrorState()
        }
    }

    private fun applyContentState(content: PagingData<FilmInfo>) {
        with(binding) {
            Log.d("PopularFragment", "CONTENT STATE")
//            filmsList.visibility = View.VISIBLE
//            error.visibility = View.GONE
//            progressBar.visibility = View.GONE
            filmsList.adapter = filmsAdapter.withLoadStateFooter(FilmsStateAdapter(
                onTryAgainClick = {
                    viewModel.loadFilms()
                }
            ))
//            lifecycleScope.launch {
//                (filmsAdapter as PagingDataAdapter<FilmInfo, FilmsViewHolder>).loadStateFlow.collectLatest {
//                    when(it.refresh){
//                        is LoadState.Error -> {
//                            applyErrorState()
//                        }
//                        LoadState.Loading -> Unit
//                        is LoadState.NotLoading -> Unit
//                    }
//                }
//            }

            filmsAdapter.addLoadStateListener {
                filmsList.isVisible = it.refresh is LoadState.NotLoading
                error.isVisible = it.refresh is LoadState.Error
                progressBar.isVisible = it.refresh is LoadState.Loading
            }
            viewLifecycleOwner.lifecycleScope.launch {
                filmsAdapter.submitData(content)
            }
        }
    }

    private fun applyLoadingState() {
        Log.d("PopularFragment", "LOADING STATE")
        with(binding) {
            filmsList.visibility = View.GONE
            error.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        }
    }

    private fun applyErrorState() {
        Log.d("PopularFragment", "ERROR STATE")
        with(binding) {
            filmsList.visibility = View.GONE
            error.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
    }
}