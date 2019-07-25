package by.fro.presentation.weather.list.adapter


import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.fro.presentation.weather.list.model.WeatherModel

object ViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("weatherAdapter", "weatherCallbacks", requireAll = false)
    fun setWeatherAdapter(recyclerView: RecyclerView, items: List<WeatherModel>?, callbacks: WeatherListAdapter.Callbacks?) {
        items?.let {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = WeatherListAdapter(it, callbacks, recyclerView)
        }
    }
}