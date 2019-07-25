package by.fro.presentation.startup


import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import by.fro.presentation.internal.util.databinding.ViewBindingAdapters
import by.fro.presentation.internal.util.lazyThreadSafetyNone
import by.fro.presentation.navigation.Navigator
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class StartupActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var navigator: Navigator

    private val viewModel by lazyThreadSafetyNone {
        ViewModelProviders.of(this, viewModelFactory).get(StartupViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.result.observe(this, Observer {
            navigator.navigateToHome(this@StartupActivity)
        })

        viewModel.error.observe(this, Observer { error ->
            ViewBindingAdapters.showLongMessage(window.decorView, error,
                object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
                    override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                        this@StartupActivity.finish()
                    }
                })
        })

        viewModel.startup()
    }
}
