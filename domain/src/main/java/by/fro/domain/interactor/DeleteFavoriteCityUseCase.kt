package by.fro.domain.interactor

import by.fro.domain.Schedulers
import by.fro.domain.UseCase
import by.fro.domain.entity.City
import by.fro.domain.gateway.SystemGateway
import io.reactivex.Observable

class DeleteFavoriteCityUseCase(schedulers: Schedulers, private val systemGateway: SystemGateway): UseCase<City, Int>(schedulers) {

    override fun buildObservable(params: City?): Observable<Int> {
        return systemGateway.deleteFavoriteCity(params)
    }
}