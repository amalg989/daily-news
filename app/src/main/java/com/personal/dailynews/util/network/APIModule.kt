package com.personal.dailynews.util.network

import com.personal.dailynews.util.constants.Constants
import com.personal.dailynews.util.network.apis.NewsAPI
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
object APIModule {

    /**
     * Provides the Get Service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the get service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideNewsApi(retrofit: Retrofit): NewsAPI {
        return retrofit.create(NewsAPI::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.HTTP_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(getHttpClientBuilder().build())
            .build()
    }

    @Reusable
    @JvmStatic
    internal fun getHttpClientBuilder(): OkHttpClient.Builder{
        return OkHttpClient.Builder().addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                try {
                    val token = "Bearer a4ca4f0a4084426ca48a69c8dfe9b79a"

                    val authorisedRequest: Request = chain.request().newBuilder().addHeader(
                        "Authorization",
                        token
                    ).build()

                    return chain.proceed(authorisedRequest)
                } catch (e: Exception) {
                    e.printStackTrace()
                    return chain.proceed(chain.request())
                }
            }
        })
    }
}