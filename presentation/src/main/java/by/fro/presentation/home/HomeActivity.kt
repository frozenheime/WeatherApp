package by.fro.presentation.home


import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import by.fro.presentation.databinding.ActivityHomeBinding
import by.fro.presentation.internal.util.lazyThreadSafetyNone
import by.fro.presentation.R
import by.fro.presentation.weather.list.WeatherListViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class HomeActivity : DaggerAppCompatActivity(), OnClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val binder by lazyThreadSafetyNone<ActivityHomeBinding> {
        DataBindingUtil.setContentView(this, R.layout.activity_home)
    }

    private val viewModel by lazyThreadSafetyNone {
        ViewModelProviders.of(this, viewModelFactory).get(WeatherListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binder.toolbar)

        binder.viewModel = viewModel
        binder.fabClick = this

        viewModel.loadWeatherList()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.action_search -> true
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }

    override fun onClick(v: View?) {

        val builder = AlertDialog.Builder(this)
        val editText = EditText(this)

        builder
            .setTitle(getString(R.string.add_a_city))
            .setView(editText)
            .setNeutralButton(R.string.add) { dialogInterface: DialogInterface, id: Int ->
                viewModel.addFavCity(editText.text.toString())
            }.show()
    }
}
