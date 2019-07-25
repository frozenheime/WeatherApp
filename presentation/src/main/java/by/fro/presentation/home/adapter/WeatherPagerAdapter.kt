package by.fro.presentation.home.adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import by.fro.domain.entity.Weather
import by.fro.presentation.weather.list.WeatherListFragment
import by.fro.presentation.weather.list.model.WeatherModel


class WeatherPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {


    private val items = arrayOf("Weather", "Forecast")

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> WeatherListFragment.newInstance()
//            1 -> ForecastFragment.newInstance(cityName) //implement forecast here in the future
            else -> Fragment()
        }
    }

    override fun getCount() = items.size

    override fun getPageTitle(position: Int) = items[position]

}