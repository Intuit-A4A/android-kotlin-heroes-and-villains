package com.example.heroesandvillains.network

import com.example.heroesandvillains.data.HeroDataModel
import com.example.heroesandvillains.network.utils.ErrorHandlingCall
import retrofit2.http.GET

/**
 * Interface to define the API endpoints
 */
interface HeroesApi {
    @GET("/superhero-api/api/all.json")
    fun queryHeroesList(): ErrorHandlingCall<List<HeroDataModel>>
}