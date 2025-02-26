package com.example.heroesandvillains.network.utils.interfaces

import com.example.heroesandvillains.data.HeroDataModel
import kotlinx.coroutines.flow.Flow

/**
 * Interface to define the repository methods
 */
interface RepositoryInterface {

    fun getFullList(): Flow<List<HeroDataModel>>
}