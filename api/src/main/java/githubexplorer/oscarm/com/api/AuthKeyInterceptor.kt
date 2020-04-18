package githubexplorer.oscarm.com.api

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthKeyInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newUrl =
            originalRequest.url.newBuilder().addQueryParameter(URL_ROUTE_KEY, API_KEY).build()
        return chain.proceed(originalRequest.newBuilder().url(newUrl).build())
    }
}