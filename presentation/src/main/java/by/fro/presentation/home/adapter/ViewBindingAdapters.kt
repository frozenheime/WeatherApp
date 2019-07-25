package by.fro.presentation.home.adapter


import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import by.fro.presentation.weather.list.model.WeatherModel

object ViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("weatherPagerAdapter")
    fun setWeatherPagerAdapter(viewPager: ViewPager, items: List<WeatherModel>?) {
        items?.let {
            val fm = (viewPager.context as FragmentActivity).supportFragmentManager
            viewPager.adapter = WeatherPagerAdapter(fm)
        }
    }
}