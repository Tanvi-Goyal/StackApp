package com.stackapp.view.interfaces

import android.view.animation.Animation

abstract class MyAnimatorListener : Animation.AnimationListener {
    override fun onAnimationStart(p0: Animation?) {}
    override fun onAnimationRepeat(p0: Animation?) {}
}