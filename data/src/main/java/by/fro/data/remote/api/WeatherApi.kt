package by.fro.data.remote.api

import by.fro.data.BuildConfig
import by.fro.data.remote.api.util.AuthenticatorInterceptor
import by.fro.data.remote.api.util.MoshiConverters
import by.fro.data.remote.api.util.addGlobalQueryParameter
import by.fro.data.remote.model.ForecastRemoteModel
import by.fro.data.remote.model.WeatherRemoteModel
import com.serjltt.moshi.adapters.Wrapped
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class WeatherApi(baseUrl: String) : WeatherService{

    companion object {
        private const val TIMEOUT = 10L
    }

    private val service: WeatherService

    init {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.BASIC

        val httpClient = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addGlobalQueryParameter("appid", BuildConfig.API_KEY) //incapsulating interceptors
//            .addInterceptor(AuthenticatorInterceptor())

        val client = httpClient.build()

        val moshi = Moshi.Builder()
            .add(Wrapped.ADAPTER_FACTORY)
            .add(MoshiConverters())
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

        service = retrofit.create(WeatherService::class.java)
    }

    override fun getWeather(cityName: String?): Observable<WeatherRemoteModel> = service.getWeather(cityName)

    override fun getForecast(cityName: String?): Observable<ForecastRemoteModel> = service.getForecast(cityName)
}