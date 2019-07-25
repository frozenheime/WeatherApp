package by.fro.data.gateway.mapper

import by.fro.data.local.model.ForecastLocalModel
import by.fro.data.local.model.WeatherLocalModel
import by.fro.domain.entity.Forecast
import by.fro.domain.entity.Weather
import java.math.BigDecimal

private const val dif = 273.15

class InventoryMapper {

    fun toEntity(forecast: ForecastLocalModel) =
        Forecast(forecast.city.id, forecast.city.name, forecast.city.countyCode, forecast.weatherList.map { Forecast.ForecastWeather(
            it.description,
            it.dtTxt,
            it.windSpeed,
            it.windDeg,
            it.clouds,
            it.rain1h,
            it.rain3h,
            it.snow1h,
            it.snow3h,
            kelvinToCelsium(it.temperature),
            kelvinToCelsium(it.temperatureMin),
            kelvinToCelsium(it.temperatureMax),
            it.pressure,
            it.humidity) })

    fun toEntity(weather: WeatherLocalModel) =
            Weather(weather.id,
                weather.cityId,
                weather.cityName,
                weather.description,
                weather.windSpeed,
                weather.windDeg,
                weather.clouds,
                weather.rain1h,
                weather.rain3h,
                weather.snow1h,
                weather.snow3h,
                kelvinToCelsium(weather.temperature),
                kelvinToCelsium(weather.temperatureMin),
                kelvinToCelsium(weather.temperatureMax),
                weather.pressure,
                weather.humidity)

    fun weatherToEntity(items: List<WeatherLocalModel>) = items.map { toEntity(it) }

    fun forecastToEntity(items: List<ForecastLocalModel>) = items.map { toEntity(it) }

    private fun kelvinToCelsium(kelvin: Double?): Double?{
        return if (kelvin == null) null else (kelvin - dif).roundTo2DecimalPlaces()
    }

    private fun Double.roundTo2DecimalPlaces() = BigDecimal(this).setScale(2, BigDecimal.ROUND_HALF_UP).toDouble()
}