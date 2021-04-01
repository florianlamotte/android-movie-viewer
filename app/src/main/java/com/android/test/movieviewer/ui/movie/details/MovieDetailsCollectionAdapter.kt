package com.android.test.movieviewer.ui.movie.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.test.movieviewer.databinding.MoviesListFragmentItemBinding

class MovieDetailsCollectionAdapter(
    private val onItemClicked: (CollectionItem) -> Unit
) :
    RecyclerView.Adapter<MovieDetailsCollectionAdapter.CollectionItemViewHolder>() {

    var data = emptyList<CollectionItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionItemViewHolder {
        val binding = MoviesListFragmentItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CollectionItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CollectionItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class CollectionItemViewHolder(
        private val binding: MoviesListFragmentItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener{
                onItemClicked(data[adapterPosition])
            }
        }

        fun bind(item: CollectionItem) {
            with(binding) {
                movieItemTitle.text = item.title
            }
        }

    }

}