package by.fro.data.remote.api

import by.fro.data.remote.model.ForecastRemoteModel
import by.fro.data.remote.model.WeatherRemoteModel
import com.serjltt.moshi.adapters.Wrapped
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    fun getWeather(@Query("q")cityName: String?): Observable<WeatherRemoteModel>

    @GET("forecast/{q}")
    fun getForecast(@Path("q")cityName: String?): Observable<ForecastRemoteModel>
}