package by.fro.data.gateway.mapper

import by.fro.data.local.model.CityLocalModel
import by.fro.domain.entity.City

class SystemMapper {

    fun toEntity(city: CityLocalModel) =
        City(city.id, city.name, city.countyCode, city.current, city.favorite)

    fun toEntity(items: List<CityLocalModel>) = items.map { toEntity(it) }
}