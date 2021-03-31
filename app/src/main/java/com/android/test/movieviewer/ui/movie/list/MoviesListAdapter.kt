package com.android.test.movieviewer.ui.movie.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.test.movieviewer.databinding.MoviesListFragmentItemBinding

class MoviesListAdapter(
    private val onItemClicked: (MoviesListItem) -> Unit
) : RecyclerView.Adapter<MoviesListAdapter.MovieViewHolder>() {

    var data = listOf<MoviesListItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MoviesListFragmentItemBinding.inflate(
            LayoutInflater.from(parent.context)
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class MovieViewHolder(private val binding: MoviesListFragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClicked(data[adapterPosition])
            }
        }

        fun bind(item: MoviesListItem) {
            with(binding) {
                movieItemTitle.text = item.title
            }
        }

    }

}