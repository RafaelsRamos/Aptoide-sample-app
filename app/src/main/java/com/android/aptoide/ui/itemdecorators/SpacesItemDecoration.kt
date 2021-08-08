package com.android.aptoide.ui.itemdecorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacesItemDecoration: RecyclerView.ItemDecoration {

    private val rect = Rect()

    constructor(space: Int) {
        rect.run {
            top = space
            bottom = space
            left = space
            right = space
        }
    }

    constructor(top: Int, bottom: Int, start: Int, end: Int) {
        rect.run {
            this.top = top
            this.bottom = bottom
            this.left = start
            this.right = end
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.top = rect.top
        outRect.right = rect.right
        outRect.bottom = rect.bottom
        outRect.left = rect.left
    }
}