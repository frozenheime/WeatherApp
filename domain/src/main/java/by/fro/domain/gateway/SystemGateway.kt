package by.fro.domain.gateway

import by.fro.domain.entity.City
import io.reactivex.Observable

interface SystemGateway {

    fun getCurrentCity(refresh: Boolean?): Observable<City>

    fun getFavoriteCities(): Observable<List<City>>

    fun addFavoriteCity(city: City?): Observable<Long>

    fun deleteFavoriteCity(city: City?): Observable<Int>
}