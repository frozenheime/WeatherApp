package by.fro.presentation.internal.util

import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorListenerAdapter
import android.view.View

/**
 * @param show true, if the view will fade-in or false to fade-out
 */
fun View.fade(show: Boolean) {

    // Cancel any on-going animation
    ViewCompat.animate(this).cancel()

    if (show) {
        if (!isShown) {
            visibility = View.VISIBLE
            alpha = 0f
            ViewCompat.animate(this).alpha(1f).start()
        }
    } else {
        if (isShown) {
            alpha = 1f
            ViewCompat.animate(this)
                .alpha(0f)
                .setListener(object : ViewPropertyAnimatorListenerAdapter() {
                    override fun onAnimationEnd(view: View?) {
                        ViewCompat.animate(view!!).setListener(null)
                        view?.visibility = View.INVISIBLE
                    }
                })
                .start()
        }
    }
}