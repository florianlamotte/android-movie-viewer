package com.android.test.movieviewer.ui.movie.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.android.test.movieviewer.R
import com.android.test.movieviewer.databinding.MoviesListFragmentBinding
import com.android.test.movieviewer.ui.util.UIState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MoviesListFragment : Fragment() {

    companion object {
        fun newInstance() = MoviesListFragment()
    }

    private var _binding: MoviesListFragmentBinding? = null
    private val binding: MoviesListFragmentBinding get() = _binding!!

    @Inject
    lateinit var viewModelProvider: MoviesListViewModelProvider

    private lateinit var viewModel: MoviesListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = MoviesListFragmentBinding.inflate(
            layoutInflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MoviesListAdapter { item ->
            onItemClicked(item)
        }
        binding.listMovies.adapter = adapter

        viewModel = ViewModelProvider(this, viewModelProvider).get(MoviesListViewModel::class.java)

        viewModel.state.observe(viewLifecycleOwner) { uiState ->
            val (loading, error, list) = when(uiState) {
                is UIState.Loading -> {
                    listOf(View.VISIBLE, View.GONE, View.GONE)
                }

                is UIState.Error -> {
                    listOf(View.GONE, View.VISIBLE, View.GONE)
                }

                is UIState.Success -> {
                    adapter.data = uiState.data
                    listOf(View.GONE, View.GONE, View.VISIBLE)
                }
            }
            with(binding) {
                listMoviesProgress.visibility = loading
                listMoviesError.visibility = error
                listMovies.visibility = list
            }
        }

        viewModel.refreshMovies()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun onItemClicked(
        item: MoviesListItem
    ) {
        findNavController()
            .navigate(
                MoviesListFragmentDirections.actionMoviesListFragmentToMovieDetailsFragment(item.id)
            )
    }
}