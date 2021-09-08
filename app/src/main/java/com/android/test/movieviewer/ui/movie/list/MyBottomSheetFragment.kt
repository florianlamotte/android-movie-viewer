package com.android.test.movieviewer.ui.movie.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.test.movieviewer.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MyBottomSheetFragment: BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.bottomsheet_fragment,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentList = MoviesListFragment.newInstance();
        childFragmentManager.beginTransaction().apply {
            replace(R.id.bottomsheet_fragment_container, fragmentList)
                .commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): MyBottomSheetFragment {
            return MyBottomSheetFragment()
        }
    }


}