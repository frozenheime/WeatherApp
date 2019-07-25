package by.fro.presentation.internal.util.databinding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import by.fro.presentation.internal.util.fade
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

object ViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("fadeView")
    fun fadeView(view: View, show: Boolean) {
        view.fade(show)
    }

    @JvmStatic
    @BindingAdapter("visible")
    fun setVisible(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.INVISIBLE
    }

    @JvmStatic
    @BindingAdapter("showLongMessage", "callback", requireAll = false)
    fun showLongMessage(view: View, text: String?, callback: BaseTransientBottomBar.BaseCallback<Snackbar>? = null) {
        text?.let {
            val snackbar = Snackbar.make(view, it, Snackbar.LENGTH_LONG)
            if (callback != null) snackbar.addCallback(callback)
            snackbar.show()
        }
    }

    @JvmStatic
    @BindingAdapter("loadUrl")
    fun loadUrl(imageView: ImageView, url: String?) {
        url?.let {
            Glide.with(imageView.context)
                .load(it)
                .apply(RequestOptions.noTransformation())
                .into(imageView)
        }
    }

    @JvmStatic
    @BindingAdapter("loadImage")
    fun loadImage(imageView: ImageView, url: Int?) {
        url?.let {
            imageView.setImageResource(url)
        }
    }

}