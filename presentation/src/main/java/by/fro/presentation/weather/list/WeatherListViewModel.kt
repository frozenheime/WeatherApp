package by.fro.presentation.weather.list

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import by.fro.domain.entity.City
import by.fro.domain.entity.Weather
import by.fro.domain.interactor.AddFavoriteCityUseCase
import by.fro.domain.interactor.DeleteFavoriteCityUseCase
import by.fro.domain.interactor.GetCurrentWeatherInFavoriteCitiesUseCase
import by.fro.domain.interactor.GetFavoriteCitiesUseCase
import by.fro.presentation.internal.util.BaseAndroidViewModel
import by.fro.presentation.weather.list.mapper.WeatherMapper
import by.fro.presentation.weather.list.model.WeatherModel
import com.google.android.material.snackbar.Snackbar
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver

class WeatherListViewModel(
    context: Context,
    private val getCurrentWeatherInFavoriteCitiesUseCase: GetCurrentWeatherInFavoriteCitiesUseCase,
    private val getFavoriteCitiesUseCase: GetFavoriteCitiesUseCase,
    private val addFavoriteCityUseCase: AddFavoriteCityUseCase,
    private val deleteFavoriteCityUseCase: DeleteFavoriteCityUseCase
) : BaseAndroidViewModel(context.applicationContext as Application) {

    private val mapper = WeatherMapper(context)

    val loading = ObservableBoolean()
    val result = ObservableArrayList<WeatherModel>()
    val error = ObservableField<String>()
    val empty = ObservableBoolean()

    fun loadWeatherList() {
        addDisposable(getFavoriteCities())
    }

    fun addFavCity(city: String) {
        addDisposable(addFavoriteCity(city))
    }

    fun deleteCityByName(cityName: String) {
        addDisposable(deleteFavoriteCity(City(0, cityName, "", false, false)))
    }

    fun refresh() = loadWeatherList()

    private fun getCurrentWeatherByCities(params: ArrayList<String>): Disposable {
        return getCurrentWeatherInFavoriteCitiesUseCase.execute(params)
            .subscribeWith(
                object : DisposableObserver<Weather>() {

                    override fun onStart() {
                        loading.set(true)
                        empty.set(false)
                    }

                    override fun onNext(t: Weather) {
                        loading.set(false)
                        result.add(mapper.toModel(t))
                    }

                    override fun onError(t: Throwable) {
                        loading.set(false)
                        error.set(t.localizedMessage ?: t.message ?: "unknown error")
                    }

                    override fun onComplete() {
                        // no-op
                    }
                })
    }

    private fun getFavoriteCities(): Disposable {
        return getFavoriteCitiesUseCase.execute()
            .subscribeWith(
                object : DisposableObserver<List<City>>() {

                    override fun onNext(t: List<City>) {
                        result.clear()
                        val params: ArrayList<String> = ArrayList()
                        t.forEach { it.name?.let { params.add(it) } }
                        addDisposable(getCurrentWeatherByCities(params))
                        empty.set(t.isEmpty())
                        loading.set(false)
                    }

                    override fun onError(e: Throwable) {
                        error.set(e.localizedMessage ?: e.message ?: "unknown error")
                    }

                    override fun onComplete() {

                    }
                }
            )
    }

    private fun addFavoriteCity(cityName: String): Disposable {
        return addFavoriteCityUseCase.execute(City(null, cityName.capitalize(), "", false, true))
            .subscribeWith(
                object : DisposableObserver<Long>(){
                    override fun onComplete() {

                    }

                    override fun onNext(t: Long) {
                        Toast.makeText(context, "City added", Toast.LENGTH_SHORT).show()
                        refresh()
                    }

                    override fun onError(e: Throwable) {

                    }

                }
            )
    }

    private fun deleteFavoriteCity(city: City): Disposable {
        return deleteFavoriteCityUseCase.execute(city)
            .subscribeWith(
                object : DisposableObserver<Int>(){
                    override fun onComplete() {

                    }

                    override fun onNext(t: Int) {
                        Toast.makeText(context, "City deleted", Toast.LENGTH_SHORT).show()
                        refresh()
                    }

                    override fun onError(e: Throwable) {

                    }

                }
            )
    }
}