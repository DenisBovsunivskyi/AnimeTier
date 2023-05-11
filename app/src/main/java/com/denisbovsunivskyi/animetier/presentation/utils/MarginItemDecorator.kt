package com.denisbovsunivskyi.animetier.presentation.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginHorizontalItemDecoration(
    private val topSize: Int = 0,
    private val leftSize: Int = 0,
    private val rightSize: Int = 0,
    private val bottomSize: Int = 0
) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                left = leftSize
            }
            top = topSize
            right = rightSize
            bottom = bottomSize
        }
    }
}
class MarginVerticalItemDecoration(
    private val topSize: Int = 0,
    private val leftSize: Int = 0,
    private val rightSize: Int = 0,
    private val bottomSize: Int = 0
) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            left = leftSize
            top = topSize
            right = rightSize
            bottom = bottomSize
        }
    }
}