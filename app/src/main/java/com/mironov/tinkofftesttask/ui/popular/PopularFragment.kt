package com.mironov.tinkofftesttask.ui.popular

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mironov.tinkofftesttask.MainActivity
import com.mironov.tinkofftesttask.databinding.FragmentPopularFilmsBinding
import com.mironov.tinkofftesttask.domain.entity.FilmInfo
import com.mironov.tinkofftesttask.presentation.ViewModelFactory
import com.mironov.tinkofftesttask.presentation.popular.PopularState
import com.mironov.tinkofftesttask.presentation.popular.PopularViewModel
import com.mironov.tinkofftesttask.ui.utils.collectStateFlow
import javax.inject.Inject

class PopularFragment : Fragment() {

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
            onClick = {}//TODO Открыть полную информацию о фильме
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
        observeViewModel()
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

    private fun applyContentState(content: List<FilmInfo>) {
        with(binding) {
            filmsList.visibility = View.VISIBLE
            error.visibility = View.GONE
            progressBar.visibility = View.GONE
            filmsList.adapter = filmsAdapter
            filmsAdapter.submitList(content)
        }
    }

    private fun applyLoadingState() {
        with(binding) {
            filmsList.visibility = View.GONE
            error.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        }
    }

    private fun applyErrorState() {
        with(binding) {
            filmsList.visibility = View.GONE
            error.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
    }
}