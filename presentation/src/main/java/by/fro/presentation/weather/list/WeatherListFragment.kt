package by.fro.presentation.weather.list


import android.os.Bundle
import dagger.android.support.DaggerFragment
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import by.fro.presentation.R
import by.fro.presentation.databinding.FragmentWeatherListBinding
import by.fro.presentation.internal.util.lazyThreadSafetyNone
import by.fro.presentation.navigation.Navigator
import by.fro.presentation.weather.list.adapter.WeatherListAdapter
import by.fro.presentation.weather.list.model.WeatherModel
import javax.inject.Inject

class WeatherListFragment : DaggerFragment(), WeatherListAdapter.Callbacks, View.OnClickListener{


    companion object {

        fun newInstance(): WeatherListFragment {
            return WeatherListFragment()
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var navigator: Navigator

    private lateinit var binder: FragmentWeatherListBinding

    private val viewModel by lazyThreadSafetyNone {
        ViewModelProviders.of(this, viewModelFactory).get(WeatherListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_weather_list, container, false)
        binder.viewModel = viewModel
        binder.weatherCallbacks = this
        return binder.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.loadWeatherList()
    }

    override fun onItemClick(view: View, item: WeatherModel) {
        val cardView = view.findViewById<View>(R.id.cardview)
        val imageView = view.findViewById<View>(R.id.image_thumbnail)
        val descrView = view.findViewById<View>(R.id.text_descr)
        val tempView = view.findViewById<View>(R.id.text_temp)
        val sharedViews = arrayOf(
            Pair(cardView, ViewCompat.getTransitionName(cardView)),
            Pair(imageView, ViewCompat.getTransitionName(imageView)),
            Pair(descrView, ViewCompat.getTransitionName(descrView)),
            Pair(tempView, ViewCompat.getTransitionName(tempView)))
        activity?.let { navigator.navigateToWeather(it, item.city, sharedViews) }
    }

    override fun onDeleteClick(view: View, item: WeatherModel) {
        viewModel.deleteCityByName(item.city)
    }

    override fun onClick(p0: View?) {

    }
}