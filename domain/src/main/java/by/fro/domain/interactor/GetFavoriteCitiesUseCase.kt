package by.fro.domain.interactor

import by.fro.domain.Schedulers
import by.fro.domain.UseCase
import by.fro.domain.entity.City
import by.fro.domain.gateway.SystemGateway
import io.reactivex.Observable

class GetFavoriteCitiesUseCase(schedulers: Schedulers, private val systemGateway: SystemGateway): UseCase<Void, List<City>>(schedulers) {

    override fun buildObservable(params: Void?): Observable<List<City>> {
        return systemGateway.getFavoriteCities()
    }
}