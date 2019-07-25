package by.fro.domain.interactor

import by.fro.domain.MissingUseCaseParameterException
import by.fro.domain.Schedulers
import by.fro.domain.UseCase
import by.fro.domain.entity.Weather
import by.fro.domain.gateway.InventoryGateway
import io.reactivex.Observable

class GetCurrentWeatherInFavoriteCitiesUseCase(schedulers: Schedulers, private val inventoryGateway: InventoryGateway): UseCase<ArrayList<String>, Weather>(schedulers) {

    override fun buildObservable(params: ArrayList<String>?): Observable<Weather> {
        if (params == null) throw MissingUseCaseParameterException(javaClass)
        return inventoryGateway.getCurrentWeatherInFavoriteCities(params)
    }
}