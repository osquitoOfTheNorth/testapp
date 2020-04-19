package githubexplorer.oscarm.com.api.dagger

import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import githubexplorer.oscarm.com.api.AuthKeyInterceptor
import githubexplorer.oscarm.com.api.BASE_URL
import githubexplorer.oscarm.com.api.GiphyApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.time.Duration
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier


@Module
class ApiModule {

    @Provides
    @Retrofit
    fun retrofit(@OkHttp client: OkHttpClient) = retrofit2.Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client)
        .build()

    @Provides
    @OkHttp
    fun okHttp(interceptors: Set<@JvmSuppressWildcards Interceptor>) : OkHttpClient = with(OkHttpClient.Builder()) {
        interceptors.forEach { addInterceptor(it) }
        connectTimeout(10, TimeUnit.SECONDS)
        return build()
    }

    @Provides
    fun giphyService(@Retrofit retrofit: retrofit2.Retrofit) = retrofit.create(GiphyApi::class.java)

    @Provides
    @IntoSet
    fun keyInterceptor(authKeyInterceptor: AuthKeyInterceptor): Interceptor = authKeyInterceptor

    @Provides
    @IntoSet
    fun networkInterceptor(): Interceptor = StethoInterceptor()

}

@Qualifier
private annotation class OkHttp

@Qualifier
private annotation class Retrofit