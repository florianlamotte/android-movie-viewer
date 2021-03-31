package com.android.test.movieviewer.ui.movie.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.test.movieviewer.databinding.MovieDetailsFragmentBinding
import com.android.test.movieviewer.domain.movie.MovieId
import com.android.test.movieviewer.domain.movie.usecase.GetMovieById
import com.android.test.movieviewer.ui.util.CoroutineContextProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = MovieDetailsFragment()
    }

    private var _binding: MovieDetailsFragmentBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var coroutineContextProvider: CoroutineContextProvider
    @Inject
    lateinit var getMovieById: GetMovieById

    private lateinit var viewModel: MovieDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MovieDetailsFragmentBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            MovieDetailsViewModelProvider(coroutineContextProvider, getMovieById, MovieId("someId"))
        ).get(MovieDetailsViewModel::class.java)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}