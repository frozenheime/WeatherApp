package by.fro.presentation.weather.detail.model

data class DetailedWeatherModel(
    val cityName: String?,
    val description: String?,
    val windSpeed: Double?,
    val clouds: Int?,
    val windDeg: Double?,
    val rain1h: Double?,
    val snow1h: Double?,
    val temperature: Double?,
    val temperatureMin: Double?,
    val temperatureMax: Double?,
    val pressure: Double?,
    val thumbnail: Int,
    val humidity: Double?
)