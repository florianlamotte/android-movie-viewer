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
import com.android.test.movieviewer.ui.util.UIState
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

        val args = MovieDetailsFragmentArgs.fromBundle(requireArguments())
        val movieId = args.id

        viewModel = ViewModelProvider(
            this,
            MovieDetailsViewModelProvider(coroutineContextProvider, getMovieById, movieId)
        ).get(MovieDetailsViewModel::class.java)

        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            val (loading, error, details, collection) = when(uiState) {
                is UIState.Loading -> {
                    listOf(View.VISIBLE, View.GONE, View.GONE, View.GONE)
                }

                is UIState.Error -> {
                    listOf(View.GONE, View.VISIBLE, View.GONE, View.GONE)
                }

                is UIState.Success -> {
                    with(binding) {
                        movieDetailsTitle.text = uiState.data.title
                        movieDetailsCollectionName.text = uiState.data.collectionName
                    }
                    listOf(View.GONE, View.GONE, View.VISIBLE,
                        if (uiState.data.CollectionItems.isNullOrEmpty()) View.GONE else View.VISIBLE
                    )
                }
            }
            with(binding) {
                movieDetailsProgress.visibility = loading
                moviesDetailsError.visibility = error
                movieDetailsGroup.visibility = details
                movieDetailsCollectionGroup.visibility = collection
            }
        }

        viewModel.getMovieDetails()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}