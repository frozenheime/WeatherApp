package by.fro.domain.entity


data class Forecast(
    var cityId: Int?,
    var cityName: String?,
    var countryCode: String?,
    var weatherList: List<ForecastWeather>?
) {
    data class ForecastWeather(
        var description: String?,
        var dtTxt: String?,
        var windSpeed: Double?,
        var windDeg: Double?,
        var clouds: Int?,
        var rain1h: Double?,
        var rain3h: Double?,
        var snow1h: Double?,
        var snow3h: Double?,
        var temperature: Double?,
        var temperatureMin: Double?,
        var temperatureMax: Double?,
        var pressure: Double?,
        var humidity: Double?
    )
}