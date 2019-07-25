package by.fro.presentation.weather.detail.adapter

import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager


object ViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("sectionAdapter")
    fun setSectionAdapter(viewPager: ViewPager, cityName: String?) {
        cityName?.let {
            val fm = (viewPager.context as FragmentActivity).supportFragmentManager
            viewPager.adapter = SectionPagerAdapter(fm, cityName)
        }
    }
}