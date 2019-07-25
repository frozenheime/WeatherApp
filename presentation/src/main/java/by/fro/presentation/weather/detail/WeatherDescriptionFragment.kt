package by.fro.presentation.weather.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import by.fro.presentation.R
import by.fro.presentation.databinding.FragmentWeatherDescriptionBinding
import by.fro.presentation.internal.util.lazyThreadSafetyNone
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class WeatherDescriptionFragment : DaggerFragment() {

    companion object {

        fun newInstance(): WeatherDescriptionFragment {
            return WeatherDescriptionFragment()
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binder: FragmentWeatherDescriptionBinding

    private val viewModel by lazyThreadSafetyNone {
        activity?.let { ViewModelProviders.of(it, viewModelFactory).get(WeatherDetailViewModel::class.java) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_weather_description, container, false)
        binder.viewModel = viewModel
        return binder.root
    }
}