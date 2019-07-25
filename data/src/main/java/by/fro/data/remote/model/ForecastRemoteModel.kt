package by.fro.data.remote.model
import com.squareup.moshi.Json

data class ForecastRemoteModel(
    @Json(name = "city")
    val city: City,
    @Json(name = "cnt")
    val cnt: Int?,
    @Json(name = "cod")
    val cod: String?,
    @Json(name = "list")
    val list: List<X?>?,
    @Json(name = "message")
    val message: Double?
) {
    data class City(
        @Json(name = "coord")
        val coord: Coord?,
        @Json(name = "countryCode")
        val country: String?,
        @Json(name = "id")
        val id: Int,
        @Json(name = "name")
        val name: String?,
        @Json(name = "population")
        val population: Int?
    ) {
        data class Coord(
            @Json(name = "lat")
            val lat: Double?,
            @Json(name = "lon")
            val lon: Double?
        )
    }

    data class X(
        @Json(name = "clouds")
        val clouds: Clouds?,
        @Json(name = "dt")
        val dt: Int?,
        @Json(name = "dt_txt")
        val dtTxt: String?,
        @Json(name = "main")
        val main: Main?,
        @Json(name = "rain")
        val rain: Rain?,
        @Json(name = "sys")
        val sys: Sys?,
        @Json(name = "weather")
        val weather: List<Weather?>?,
        @Json(name = "wind")
        val wind: Wind?,
        @Json(name = "snow")
        val snow: Snow?
    ) {
        data class Weather(
            @Json(name = "description")
            val description: String?,
            @Json(name = "icon")
            val icon: String?,
            @Json(name = "id")
            val id: Int?,
            @Json(name = "main")
            val main: String?
        )

        data class Sys(
            @Json(name = "pod")
            val pod: String?
        )

        data class Wind(
            @Json(name = "deg")
            val deg: Double?,
            @Json(name = "speed")
            val speed: Double?
        )

        data class Main(
            @Json(name = "grnd_level")
            val grndLevel: Double?,
            @Json(name = "humidity")
            val humidity: Double?,
            @Json(name = "pressure")
            val pressure: Double?,
            @Json(name = "sea_level")
            val seaLevel: Double?,
            @Json(name = "temp")
            val temp: Double?,
            @Json(name = "temp_kf")
            val tempKf: Int?,
            @Json(name = "temp_max")
            val tempMax: Double?,
            @Json(name = "temp_min")
            val tempMin: Double?
        )

        data class Rain(
            @Json(name = "3h")
            val rain3h: Double,
            @Json(name = "1h")
            val rain1h: Double
        )

        data class Snow(
            @Json(name = "3h")
            val snow3h: Double,
            @Json(name = "1h")
            val snow1h: Double
        )

        data class Clouds(
            @Json(name = "all")
            val all: Int?
        )
    }
}