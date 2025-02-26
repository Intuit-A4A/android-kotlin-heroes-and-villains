package com.example.heroesandvillains.network.utils

import com.example.heroesandvillains.network.utils.interfaces.ServiceInterface
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * Note: This is part of the network layer infrastructure, and not required to be changed
 * in user stories as part of the craft A4A
 *
 * Base Rest Service class.
 * Extend this service class and implement the abstract function to provide base URL and additional network
 * interceptors or converter if applicable
 */
abstract class RestService<API : Any>(private val api: Class<API>): ServiceInterface {

    /**
     * Construct the API instance with default network call headers (including auth headers), and with
     * default retrofit converter and error handling call adapter.
     */
    val apiInstance: API by lazy {
        Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .client(
                HttpClient.okHttpClient
                    .newBuilder()
                    .also { okHttpBuilder ->
                        okHttpBuilder.addInterceptor(
                            HttpLoggingInterceptor().setLevel(
                                HttpLoggingInterceptor.Level.BASIC
                            )
                        )
                        getInterceptors().forEach { okHttpBuilder.addInterceptor(it) }
                    }.build()
            ).also { retrofitBuilder ->
                getAdditionalConverters().forEach { retrofitBuilder.addConverterFactory(it) }
                getRetrofitConverterFactory().forEach { retrofitBuilder.addConverterFactory(it) }
                retrofitBuilder.addCallAdapterFactory(ErrorHandlingCallAdapterFactory())
            }
            .build()
            .create(api)
    }

    /**
     * Host app/module consuming the generic data layer will implement this function to provide the base
     * URL for the REST network call. The path of each request will be defined through respective retrofit
     * interface.
     * The sandbox's contextDelegate can be used to determine if E2E or PROD URL should be provided
     */
    abstract fun getBaseUrl(): String

    /**
     * Host app/module consuming the generic data layer will implement this function to provide additional
     * Retrofit converter for response data parsing
     */
    abstract fun getAdditionalConverters(): List<Converter.Factory>

    /**
     * Add additional okhttp interceptors to the retrofit call interface. The host app/module consuming the
     * generic data layer will implement this function. Returns empty list if no additional interceptors are
     * required in addition to CommonRequestInterceptor
     */
    abstract fun getInterceptors(): List<Interceptor>

    private fun getRetrofitConverterFactory(): List<Converter.Factory> = mutableListOf(
        ScalarsConverterFactory.create(),
        GsonConverterFactory.create(GsonBuilder().setLenient().serializeNulls().create())
    )
}

object HttpClient {
    /**
     * Single shared instance of OkHttpClient
     * Each specific Rest/GraphQL service interface will customize this shared okHttpClient through
     * newBuilder() call.
     * https://square.github.io/okhttp/3.x/okhttp/okhttp3/OkHttpClient.html
     */
    internal val okHttpClient by lazy { OkHttpClient() }
}