package by.fro.presentation.weather.detail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import by.fro.presentation.weather.detail.WeatherDescriptionFragment


class SectionPagerAdapter(fm: FragmentManager, private val cityName: String) : FragmentStatePagerAdapter(fm) {

    private val items = arrayOf("Detailed", "Forecast")

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> WeatherDescriptionFragment.newInstance()
//            1 -> ForecastFragment.newInstance(cityName) //implement forecast here in the future
            else -> Fragment()
        }
    }

    override fun getCount() = items.size

    override fun getPageTitle(position: Int) = items[position]
}