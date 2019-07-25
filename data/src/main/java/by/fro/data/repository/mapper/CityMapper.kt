package by.fro.data.repository.mapper

import by.fro.data.local.model.CityLocalModel
import by.fro.data.remote.model.CityRemoteModel
import by.fro.domain.entity.City

class CityMapper {

    fun toLocal(city: CityRemoteModel) =
            CityLocalModel(id = null, name = city.name, countyCode = city.countryCode, current = city.current, favorite = city.favorite)
    fun toLocal(items: List<CityRemoteModel>) = items.map { toLocal(it) }

    fun toLocal(city: City) =
            CityLocalModel(id = null, name = city.name, countyCode = city.countryCode, current = city.current, favorite = city.favorite)
}