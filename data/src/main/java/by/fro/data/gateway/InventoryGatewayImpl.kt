package by.fro.data.gateway

import by.fro.data.gateway.mapper.InventoryMapper
import by.fro.data.repository.ForecastRepository
import by.fro.data.repository.WeatherRepository
import by.fro.domain.entity.City
import by.fro.domain.entity.Forecast
import by.fro.domain.entity.Weather
import by.fro.domain.gateway.InventoryGateway
import io.reactivex.Observable

class InventoryGatewayImpl(
    private val forecastRepository: ForecastRepository,
    private val weatherRepository: WeatherRepository
) : InventoryGateway {

    private val mapper = InventoryMapper()

    override fun getWeatherByCity(city: String?): Observable<Weather> =
        weatherRepository.getWeatherByCityName(city)
            .doOnError { println("Weather by city($city) error")}
            .map { mapper.toEntity(it) }

    override fun getForecastByCity(city: City?): Observable<Forecast> =
        forecastRepository.getForecastByCityName(city?.name)
            .doOnError { println("Forecast by city($city) error") }
            .map { mapper.toEntity(it) }

    override fun getCurrentWeatherInFavoriteCities(cityNames: ArrayList<String>): Observable<Weather> =
        weatherRepository.getCurrentWeatherInFavoriteCitiesList(cityNames)
            .doOnError { println("Current weather in fav cities error") }
            .map { mapper.toEntity(it) }

    override fun getForecastInFavoriteCities(): Observable<List<Forecast>> =
        forecastRepository.getFavoriteCitiesForecasts()
            .doOnError { println("forecast in fav cities error") }
            .map { mapper.forecastToEntity(it) }
}