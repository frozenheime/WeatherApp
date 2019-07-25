package by.fro.data.repository.mapper

import by.fro.data.local.model.WeatherLocalModel
import by.fro.data.remote.model.WeatherRemoteModel

class WeatherMapper {

    fun toLocal(weather: WeatherRemoteModel) =
            WeatherLocalModel(
                id = 0,
                cityId = weather.id,
                cityName = weather.name,
                description = weather.weather?.get(0)?.description,
                windSpeed = weather.wind?.speed,
                windDeg = weather.wind?.deg,
                clouds = weather.clouds?.all,
                rain1h = weather.rain?.rain1h,
                rain3h = weather.rain?.rain3h,
                snow1h = weather.snow?.snow1h,
                snow3h = weather.snow?.snow3h,
                temperature = weather.main?.temp,
                temperatureMin = weather.main?.tempMin,
                temperatureMax = weather.main?.tempMax,
                pressure = weather.main?.pressure,
                humidity = weather.main?.humidity
                )
    fun toLocal(items: List<WeatherRemoteModel>) = items.map { toLocal(it) }
}