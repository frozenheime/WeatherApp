package by.fro.data.remote.api.util

import io.reactivex.exceptions.Exceptions
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import java.io.IOException

fun OkHttpClient.Builder.addGlobalQueryParameter(key: String, value: String): OkHttpClient.Builder {

    this.addInterceptor {
        val currentRequest = it.request()
        val originalHttpUrl = currentRequest.url()
        val url: HttpUrl = originalHttpUrl.newBuilder()
            .addQueryParameter(key, value)
            .build()

        val newRequest = currentRequest.newBuilder()
            .url(url)
            .build()

        try {
            it.proceed(newRequest)
        } catch (e: IOException) {
            // Transform checked Exception in Unchecked Exception
            throw Exceptions.propagate(e)
        }
    }
    return this
}