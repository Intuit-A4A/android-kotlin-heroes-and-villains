package com.example.heroesandvillains.network

import com.example.heroesandvillains.data.HeroDataModel
import com.example.heroesandvillains.network.utils.RestService
import okhttp3.Interceptor
import retrofit2.Converter

/**
 * Implementation of [RestService] interface for performing network request and parsing responses
 * with https://akabab.github.io
 * The actual retrofit API interface is [HeroesApi]
 */
class HeroService: RestService<HeroesApi>(
    api = HeroesApi::class.java,
) {
    override fun getBaseUrl(): String = "https://akabab.github.io"

    override fun getAdditionalConverters(): List<Converter.Factory> = emptyList()

    override fun getInterceptors(): List<Interceptor> = emptyList()

    override suspend fun queryList(): List<HeroDataModel> = apiInstance.queryHeroesList().await().toList()
}