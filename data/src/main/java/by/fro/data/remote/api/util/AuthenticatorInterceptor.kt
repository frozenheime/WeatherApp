package by.fro.data.remote.api.util

import by.fro.data.BuildConfig
import io.reactivex.exceptions.Exceptions
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthenticatorInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val currentRequest = chain.request()
        val originalHttpUrl = currentRequest.url()
        val url: HttpUrl = originalHttpUrl.newBuilder()
            .addQueryParameter("appid", BuildConfig.API_KEY)
            .build()

        val newRequest = currentRequest.newBuilder()
            .url(url)
            .build()

        try {
            return chain.proceed(newRequest)
        } catch (e: IOException) {
            // Transform checked Exception in Unchecked Exception
            throw Exceptions.propagate(e)
        }
    }
}