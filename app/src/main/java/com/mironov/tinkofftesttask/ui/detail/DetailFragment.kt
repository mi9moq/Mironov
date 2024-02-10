package com.mironov.tinkofftesttask.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mironov.tinkofftesttask.MainActivity
import com.mironov.tinkofftesttask.databinding.FragmentFilmBinding
import com.mironov.tinkofftesttask.domain.entity.FilmDetailInfo
import com.mironov.tinkofftesttask.presentation.ViewModelFactory
import com.mironov.tinkofftesttask.presentation.detail.DetailState
import com.mironov.tinkofftesttask.presentation.detail.DetailViewModel
import com.mironov.tinkofftesttask.ui.utils.collectStateFlow
import com.squareup.picasso.Picasso
import javax.inject.Inject

class DetailFragment : Fragment() {

    companion object {

        fun newInstance(id: Int) = DetailFragment().apply {
            arguments = Bundle().apply {
                putInt(FILM_ID, id)
            }
        }

        private const val FILM_ID = "film id"
        private const val DEFAULT_ID = 1
    }

    private var id = DEFAULT_ID

    private val component by lazy {
        (requireActivity() as MainActivity).component
    }

    private var _binding: FragmentFilmBinding? = null
    private val binding: FragmentFilmBinding
        get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArguments()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addClickListeners()
        observeViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun parseArguments() {
        val args = requireArguments()
        id = args.getInt(FILM_ID)
        viewModel.loadFilm(id)
    }

    private fun addClickListeners() {
        binding.tryAgain.setOnClickListener {
            viewModel.loadFilm(id)
        }
    }

    private fun observeViewModel() {
        collectStateFlow(viewModel.state, ::applyState)
    }

    private fun applyState(state: DetailState) {

        when (state) {
            DetailState.Initial -> Unit
            is DetailState.Content -> applyContentState(state.content)
            DetailState.Error -> applyErrorState()
            DetailState.Loading -> applyLoadingState()
        }
    }

    private fun applyContentState(film: FilmDetailInfo) {

        with(binding) {
            content.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            error.visibility = View.GONE
            Picasso.get().load(film.posterUrl).into(poster)
            name.text = film.name
            genres.text = film.genres.joinToString(", ")
            countries.text = film.countries.joinToString(", ")
            description.text = film.description
        }
    }

    private fun applyErrorState() {
        with(binding) {
            content.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            error.visibility = View.GONE
        }
    }

    private fun applyLoadingState() {
        with(binding) {
            content.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            error.visibility = View.GONE
        }
    }
}