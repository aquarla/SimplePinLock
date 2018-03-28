package net.aquarla.simplepinlock

import android.content.Context

/*
 * Utility interface.
 */
interface Util {
    // dp->pxの変換
    fun dp2px(context: Context, dp: Float) : Float {
        return dp * context.resources.displayMetrics.density
    }

    //px->dpの変換
    fun px2dp(context: Context, px: Float) : Float {
        return px / context.resources.displayMetrics.density
    }
}