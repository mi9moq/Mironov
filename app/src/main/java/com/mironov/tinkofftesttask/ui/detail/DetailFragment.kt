package com.mironov.tinkofftesttask.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mironov.tinkofftesttask.databinding.FragmentFilmBinding

class DetailFragment : Fragment() {

    companion object {

        fun newInstance(id: Int) = DetailFragment().apply {
            arguments = Bundle().apply {
                putInt(FILM_ID, id)
            }
        }

        private const val FILM_ID = "film id"
    }

    private var _binding: FragmentFilmBinding? = null
    private val binding: FragmentFilmBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmBinding.inflate(inflater,container,false)
        return binding.root
    }
}