package by.fro.presentation.weather.list.mapper

import android.content.Context
import by.fro.domain.entity.Weather
import by.fro.presentation.R
import by.fro.presentation.weather.list.model.WeatherModel

class WeatherMapper(private val context: Context) {

    fun toModel(weather: Weather): WeatherModel {
        return WeatherModel(weather.cityName!!, weather.id!!, weather.description ?: "unknown", weather.temperature!!.toString(), setThumbnail(weather))
    }

    private fun setThumbnail(weather: Weather): Int {
        val description = weather.description
        return when {
            description!!.contains("clouds") -> R.drawable.ic_cloud
            description.contains("fog") -> R.drawable.ic_fog
            description.contains("rain") -> R.drawable.ic_rain
            description.contains("snow") -> R.drawable.ic_snow
            description.contains("sun") -> R.drawable.ic_sun
            description.contains("thunder") -> R.drawable.ic_thunder
            else -> R.drawable.ic_unknown
        }
    }
}