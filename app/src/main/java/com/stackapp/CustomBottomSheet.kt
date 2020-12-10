package com.stackapp

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior

class CustomBottomSheetBehavior<V : View>(context: Context, attrs: AttributeSet) :
    BottomSheetBehavior<V>(context, attrs) {

    private var enableCollapse = true

    fun setEnableCollapse(enableCollapse: Boolean) {
        this.enableCollapse = enableCollapse
    }

    override fun onInterceptTouchEvent(
        parent: CoordinatorLayout,
        child: V,
        event: MotionEvent
    ): Boolean {
        return if (enableCollapse) {
            false
        } else super.onInterceptTouchEvent(parent, child, event)
    }
}