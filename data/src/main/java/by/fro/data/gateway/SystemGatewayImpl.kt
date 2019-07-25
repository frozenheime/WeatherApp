package by.fro.data.gateway

import by.fro.data.gateway.mapper.SystemMapper
import by.fro.data.repository.CityRepository
import by.fro.domain.entity.City
import by.fro.domain.gateway.SystemGateway
import io.reactivex.Observable

class SystemGatewayImpl(private val cityRepository: CityRepository) : SystemGateway {

    private val mapper = SystemMapper()

    override fun getCurrentCity(refresh: Boolean?): Observable<City> =
        cityRepository.getCurrentCity(refresh)
            .doOnError { println("Error getting current city") }
            .map { mapper.toEntity(it) }

    override fun getFavoriteCities(): Observable<List<City>> =
        cityRepository.getFavoriteCities()
            .doOnError { println("Error getting current cities list") }
            .map { mapper.toEntity(it) }

    override fun addFavoriteCity(city: City?): Observable<Long> {
        return cityRepository.addFavoriteCity(city)
    }

    override fun deleteFavoriteCity(city: City?): Observable<Int> {
        return cityRepository.deleteFavoriteCity(city)
    }
}