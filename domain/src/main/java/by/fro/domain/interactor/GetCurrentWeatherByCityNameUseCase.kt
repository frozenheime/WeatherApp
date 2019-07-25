package by.fro.domain.interactor

import by.fro.domain.MissingUseCaseParameterException
import by.fro.domain.Schedulers
import by.fro.domain.UseCase
import by.fro.domain.entity.City
import by.fro.domain.entity.Weather
import by.fro.domain.gateway.InventoryGateway
import io.reactivex.Observable

class GetCurrentWeatherByCityNameUseCase(schedulers: Schedulers, private val inventoryGateway: InventoryGateway): UseCase<String, Weather>(schedulers) {

    override fun buildObservable(params: String?): Observable<Weather> {
        if (params == null) throw MissingUseCaseParameterException(javaClass)
        return inventoryGateway.getWeatherByCity(params)
    }
}