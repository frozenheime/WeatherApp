package by.fro.presentation.weather.detail

import android.app.Application
import android.content.Context
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import by.fro.domain.entity.City
import by.fro.domain.entity.Weather
import by.fro.domain.interactor.GetCurrentCityUseCase
import by.fro.domain.interactor.GetCurrentWeatherByCityNameUseCase
import by.fro.presentation.weather.detail.mapper.DetailedWeatherMapper
import by.fro.presentation.weather.detail.model.DetailedWeatherModel
import by.fro.presentation.internal.util.BaseAndroidViewModel
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver


class WeatherDetailViewModel(
    context: Context,
    private val getCurrentWeatherByCityNameUseCase: GetCurrentWeatherByCityNameUseCase,
    private val getCurrentCityUseCase: GetCurrentCityUseCase
) : BaseAndroidViewModel(context.applicationContext as Application) {

    private val mapper = DetailedWeatherMapper()

    val loading = ObservableBoolean()
    val cityIsCurrent = ObservableBoolean()
    val currentCityName = ObservableField<String>()
    val weather = ObservableField<DetailedWeatherModel>()
    val error = ObservableField<String>()

    fun loadWeatherDetail(city: String) = addDisposable(getWeatherByCityName(city))

    private fun getWeatherByCityName(city: String): Disposable {
        return getCurrentWeatherByCityNameUseCase.execute(city)
            .subscribeWith(object : DisposableObserver<Weather>() {

                override fun onStart() {
                    loading.set(true)
                }

                override fun onNext(result: Weather) {
                    loading.set(false)
                    weather.set(mapper.toModel(result))

                    cityIsCurrent.set(checkCityIsCurrent())

                    addDisposable(getCurrentCity())
                }

                override fun onError(t: Throwable) {
                    loading.set(false)
                    error.set(t.localizedMessage ?: t.message ?: "Unknown error")
                }

                override fun onComplete() {
                    // no-op
                }
            })
    }

    private fun getCurrentCity(): Disposable {
        return getCurrentCityUseCase.execute()
            .subscribeWith(object : DisposableObserver<City>() {

                override fun onComplete() {

                }

                override fun onNext(t: City) {
                    currentCityName.set(t.name)
                }

                override fun onError(e: Throwable) {
                    loading.set(false)
                    error.set(e.localizedMessage ?: e.message ?: "Unknown error")
                }

            }
            )
    }

    private fun checkCityIsCurrent(): Boolean {
        return currentCityName.get().equals(weather.get()?.cityName)
    }

}