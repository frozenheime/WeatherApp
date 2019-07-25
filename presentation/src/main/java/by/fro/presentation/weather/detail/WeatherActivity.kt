package by.fro.presentation.weather.detail

import android.os.Bundle
import by.fro.presentation.R
import android.content.Intent
import android.view.Menu
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.view.doOnPreDraw
import by.fro.presentation.databinding.ActivityWeatherBinding
import by.fro.presentation.internal.util.lazyThreadSafetyNone
import by.fro.presentation.navigation.Navigator
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class WeatherActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var navigator: Navigator

    private val binder by lazyThreadSafetyNone<ActivityWeatherBinding> {
        DataBindingUtil.setContentView(this, R.layout.activity_weather)
    }

    private val weatherDetailViewModel by lazyThreadSafetyNone {
        ViewModelProviders.of(this, viewModelFactory).get(WeatherDetailViewModel::class.java)
    }

    private val eventRatingViewModel by lazyThreadSafetyNone {
        ViewModelProviders.of(this, viewModelFactory).get(WeatherDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportPostponeEnterTransition()

//        setSupportActionBar(binder.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binder.weatherDetailViewModel = weatherDetailViewModel

        val cityName = navigator.getCityName(this)
        weatherDetailViewModel.loadWeatherDetail(cityName)

        weatherDetailViewModel.weather.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(observable: Observable, propertyId: Int) {
                (window.decorView as ViewGroup).doOnPreDraw {
                    supportStartPostponedEnterTransition()
                }
            }
        })
    }

    override fun getParentActivityIntent(): Intent {
        return super.getParentActivityIntent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_event, menu)
        return true
    }
}
