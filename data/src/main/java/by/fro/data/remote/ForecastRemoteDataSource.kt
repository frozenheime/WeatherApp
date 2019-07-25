package by.fro.data.remote

import by.fro.data.remote.api.WeatherService
import by.fro.data.remote.model.ForecastRemoteModel
import io.reactivex.Observable

class ForecastRemoteDataSource(private val weatherService: WeatherService) {

    fun getForecastByCityName(cityName: String?): Observable<ForecastRemoteModel> = weatherService.getForecast(cityName)
}