package com.android.test.movieviewer.ui.movie.button

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.test.movieviewer.databinding.ButtonFragmentBinding
import com.android.test.movieviewer.ui.movie.list.MyBottomSheetFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ButtonFragment: Fragment() {
    companion object {
        fun newInstance() = ButtonFragment()
    }

    private var _binding: ButtonFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ButtonFragmentBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            parentFragmentManager.let {
                MyBottomSheetFragment.newInstance().apply {
                    show(it, tag)
                }
            }
        }
    }

}