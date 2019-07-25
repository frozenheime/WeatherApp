package by.fro.domain.gateway

import by.fro.domain.entity.City
import by.fro.domain.entity.Forecast
import by.fro.domain.entity.Weather
import io.reactivex.Observable

interface InventoryGateway {


    fun getWeatherByCity(city: String?): Observable<Weather>

    fun getForecastByCity(city: City?): Observable<Forecast>

    fun getCurrentWeatherInFavoriteCities(cityNames: ArrayList<String>): Observable<Weather>

    fun getForecastInFavoriteCities(): Observable<List<Forecast>>
}