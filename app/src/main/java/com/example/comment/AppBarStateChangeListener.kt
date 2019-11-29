package com.example.comment

import com.google.android.material.appbar.AppBarLayout

abstract class AppBarStateChangeListener : AppBarLayout.OnOffsetChangedListener {
    enum class State {
        EXPANDED,
        COLLAPSED,
        IDLE

    }

    private var mCurrentState: State = State.IDLE
    override fun onOffsetChanged(appBarLayoyt: AppBarLayout?, i: Int) {
        if (i == 0) {
            if (mCurrentState != State.EXPANDED) {
                appBarLayoyt?.let { onStateChanged(it, State.EXPANDED,i) }
            }
            mCurrentState = State.EXPANDED
        } else if (Math.abs(i) >= appBarLayoyt?.totalScrollRange ?: 0) {
            if (mCurrentState != State.COLLAPSED) {
                appBarLayoyt?.let { onStateChanged(it, State.COLLAPSED,i) }
            }
            mCurrentState = State.COLLAPSED
        } else {
            if (mCurrentState != State.IDLE) {
                appBarLayoyt?.let { onStateChanged(it, State.IDLE,i) }
            }
            mCurrentState = State.IDLE
        }

    }

    abstract fun onStateChanged(appBarlayout: AppBarLayout, state: State, i: Int);
}