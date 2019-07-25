package by.fro.data.repository.mapper

import by.fro.data.local.model.CityLocalModel
import by.fro.data.local.model.ForecastLocalModel
import by.fro.data.local.model.ForecastWeatherLocalModel
import by.fro.data.remote.model.ForecastRemoteModel

class ForecastMapper {

    fun toLocal(forecast: ForecastRemoteModel): ForecastLocalModel {
        val forecastLocalModel = ForecastLocalModel(CityLocalModel(forecast.city.id, forecast.city?.name, forecast.city?.country, null, null))
        forecastLocalModel.weatherList = toLocal(forecast, forecast.list)!!
        return forecastLocalModel
    }

    fun toLocal(forecast: ForecastRemoteModel, weatherList: List<ForecastRemoteModel.X?>?) =
            weatherList?.map { ForecastWeatherLocalModel(forecast.city.id, forecast.city.name, it?.weather?.get(0)?.description, it?.dtTxt,
                it?.wind?.speed, it?.wind?.deg, it?.clouds?.all, it?.rain?.rain1h, it?.rain?.rain3h, it?.snow?.snow1h, it?.snow?.snow3h,
                it?.main?.temp, it?.main?.tempMin, it?.main?.tempMax, it?.main?.pressure, it?.main?.humidity) }

//    ForecastLocalModel(city.id = null, cityId = null, cityName = forecast.city?.name, country = forecast.city?.country,
//    weatherList = ForecastWeatherListMapper().toLocal(forecast.list))

//    private class ForecastWeatherListMapper{
//        private fun toLocal(forecastWeather: ForecastRemoteModel.X?) =
//                ForecastWeatherLocalModel(forecastWeather?.weather?.get(0).toString(), forecastWeather?.dtTxt, forecastWeather?.wind?.speed,
//                    forecastWeather?.wind?.deg, forecastWeather?.clouds?.all, forecastWeather?.rain?.rain1h, forecastWeather?.rain?.rain3h,
//                    forecastWeather?.snow?.snow1h, forecastWeather?.snow?.snow3h, forecastWeather?.main?.temp, forecastWeather?.main?.tempMin, forecastWeather?.main?.tempMax,
//                    forecastWeather?.main?.pressure, forecastWeather?.main?.humidity)
//        fun toLocal(items: List<ForecastRemoteModel.X?>?) = items?.map { toLocal(it) }
//    }

}