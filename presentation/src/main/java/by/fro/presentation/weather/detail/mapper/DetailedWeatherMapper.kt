package by.fro.presentation.weather.detail.mapper

import by.fro.domain.entity.Weather
import by.fro.presentation.R
import by.fro.presentation.weather.detail.model.DetailedWeatherModel

class DetailedWeatherMapper {

    fun toModel(weather: Weather): DetailedWeatherModel =
        DetailedWeatherModel(
            weather.cityName,
            weather.description,
            weather.windSpeed,
            weather.clouds,
            weather.windDeg,
            weather.rain1h,
            weather.snow1h,
            weather.temperature,
            weather.temperatureMin,
            weather.temperatureMax,
            weather.pressure,
            setThumbnail(weather),
            weather.humidity)

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