package by.fro.data.remote

import by.fro.data.remote.api.WeatherService
import by.fro.data.remote.model.WeatherRemoteModel
import io.reactivex.Observable

class WeatherRemoteDataSource(private val weatherService: WeatherService) {

    fun getWeather(cityName: String?): Observable<WeatherRemoteModel> = weatherService.getWeather(cityName)

    fun getWeather(cityName: ArrayList<String>): Observable<WeatherRemoteModel> {

        val weatherList: ArrayList<Observable<WeatherRemoteModel>> = ArrayList()

        cityName.forEach { weatherList.add(getWeather(it)) }

        return Observable.merge(weatherList)
    }
}