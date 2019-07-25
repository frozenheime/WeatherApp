package by.fro.domain.entity

data class Weather(
    val id: Int,
    val cityId: Int?,
    val cityName: String?,
    val description: String?,
    val windSpeed: Double?,
    val windDeg: Double?,
    val clouds: Int?,
    val rain1h: Double?,
    val rain3h: Double?,
    val snow1h: Double?,
    val snow3h: Double?,
    val temperature: Double?,
    val temperatureMin: Double?,
    val temperatureMax: Double?,
    val pressure: Double?,
    val humidity: Double?
)