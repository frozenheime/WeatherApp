package by.fro.presentation.weather.list.model

data class WeatherModel(
    val city: String,
    val id: Int,
    val description: String,
    val temperature: String,
    val thumbnail: Int
)