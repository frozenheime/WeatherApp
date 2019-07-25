package by.fro.presentation.startup

import android.app.Application
import android.content.Context
import by.fro.domain.entity.City
import by.fro.domain.interactor.GetCurrentCityUseCase
import by.fro.domain.interactor.GetFavoriteCitiesUseCase
import by.fro.presentation.internal.util.BaseAndroidViewModel
import by.fro.presentation.internal.util.SingleLiveData
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver

class StartupViewModel(context: Context,
                       private val getCurrentCityUseCase: GetCurrentCityUseCase,
                       private val getFavoriteCitiesUseCase: GetFavoriteCitiesUseCase)
    : BaseAndroidViewModel(context.applicationContext as Application) {

    private val _result = SingleLiveData<Boolean>()
    val result = _result
    private val _error = SingleLiveData<String>()
    val error = _error

    fun startup(){
        addDisposable(getCurrentCity())
    }

    private fun getCurrentCity(): Disposable {
        return getCurrentCityUseCase.execute(true)
            .subscribeWith(object : DisposableObserver<City>() {

                override fun onNext(t: City) {
                    result.value = true
                    println("current city is: $t")
                    addDisposable(getFavCities())
                }

                override fun onError(t: Throwable) {
                    error.value = t.localizedMessage ?: t.message ?: "unknown error"
                }

                override fun onComplete() {
                    // no-op
                }
            })
    }

    private fun getFavCities(): Disposable {
        return getFavoriteCitiesUseCase.execute()
            .subscribeWith(object : DisposableObserver<List<City>>() {

                override fun onNext(t: List<City>) {
                    println("Fav cities go here")
                    println("cities count: ${t.size}")
                    t.forEach { println(it) }
                }

                override fun onError(t: Throwable) {
                    error.value = t.localizedMessage ?: t.message ?: "unknown error"
                }

                override fun onComplete() {
                    // no-op
                }
            })
    }
}