package com.android.test.movieviewer.ui.movie.list

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.test.movieviewer.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.PEEK_HEIGHT_AUTO
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class MyBottomSheetFragment: BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            (this as? BottomSheetDialog)
                ?.behavior
                ?.apply {
                    setPeekHeight(PEEK_HEIGHT_AUTO, true)
                    isFitToContents = true
                    expandedOffset = getBottomSheetDialogDefaultHeight()
                    state = BottomSheetBehavior.STATE_EXPANDED
                }
        }
    }

    private fun getBottomSheetDialogDefaultHeight(): Int {
        return getWindowHeight() * 15 / 100 // 15%
    }

    private fun getWindowHeight(): Int {
        // Calculate window height for fullscreen use
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    companion object {
        @JvmStatic
        fun newInstance(): MyBottomSheetFragment {
            return MyBottomSheetFragment()
        }
    }


}