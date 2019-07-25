package by.fro.domain.interactor

import by.fro.domain.Schedulers
import by.fro.domain.UseCase
import by.fro.domain.entity.City
import by.fro.domain.gateway.SystemGateway
import io.reactivex.Observable

class GetCurrentCityUseCase(schedulers: Schedulers, private val systemGateway: SystemGateway): UseCase<Boolean, City>(schedulers) {

    override fun buildObservable(params: Boolean?): Observable<City> {
        return systemGateway.getCurrentCity(params)
    }
}