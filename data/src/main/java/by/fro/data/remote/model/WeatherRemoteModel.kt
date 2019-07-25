package by.fro.data.remote.model
import com.squareup.moshi.Json

data class WeatherRemoteModel(
    @Json(name = "base")
    val base: String?,
    @Json(name = "clouds")
    val clouds: Clouds?,
    @Json(name = "cod")
    val cod: Int? = 0,
    @Json(name = "coord")
    val coord: Coord?,
    @Json(name = "dt")
    val dt: Int? = 0,
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "main")
    val main: Main?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "sys")
    val sys: Sys?,
    @Json(name = "visibility")
    val visibility: Int? = 0,
    @Json(name = "weather")
    val weather: List<Weather?>?,
    @Json(name = "wind")
    val wind: Wind?,
    @Json(name = "rain")
    val rain: Rain?,
    @Json(name = "snow")
    val snow: Snow?

) {
    data class Main(
        @Json(name = "humidity")
        val humidity: Double? = 0.0,
        @Json(name = "pressure")
        val pressure: Double? = 0.0,
        @Json(name = "temp")
        val temp: Double? = 0.0,
        @Json(name = "temp_max")
        val tempMax: Double? = 0.0,
        @Json(name = "temp_min")
        val tempMin: Double? = 0.0
    )

    data class Weather(
        @Json(name = "description")
        val description: String?,
        @Json(name = "icon")
        val icon: String?,
        @Json(name = "id")
        val id: Int? = 0,
        @Json(name = "main")
        val main: String?
    )

    data class Coord(
        @Json(name = "lat")
        val lat: Double? = 0.0,
        @Json(name = "lon")
        val lon: Double? = 0.0
    )

    data class Sys(
        @Json(name = "countryCode")
        val country: String?,
        @Json(name = "id")
        val id: Int? = 0,
        @Json(name = "message")
        val message: Double? = 0.0,
        @Json(name = "sunrise")
        val sunrise: Int? = 0,
        @Json(name = "sunset")
        val sunset: Int? = 0,
        @Json(name = "type")
        val type: Int? = 0
    )

    data class Wind(
        @Json(name = "deg")
        val deg: Double?= 0.0,
        @Json(name = "gust")
        val gust: Double? = 0.0,
        @Json(name = "speed")
        val speed: Double? = 0.0
    )

    data class Rain(
        @Json(name = "3h")
        val rain3h: Double = 0.0,
        @Json(name = "1h")
        val rain1h: Double = 0.0
    )

    data class Snow(
        @Json(name = "3h")
        val snow3h: Double = 0.0,
        @Json(name = "1h")
        val snow1h: Double = 0.0
    )

    data class Clouds(
        @Json(name = "all")
        val all: Int? = 0
    )
}