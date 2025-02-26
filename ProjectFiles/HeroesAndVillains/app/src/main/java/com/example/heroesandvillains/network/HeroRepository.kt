package com.example.heroesandvillains.network


import com.example.heroesandvillains.data.HeroDataModel
import com.example.heroesandvillains.network.utils.interfaces.RepositoryInterface
import com.example.heroesandvillains.network.utils.interfaces.ServiceInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Repository class to fetch hero data
 */
class HeroRepository(
    private val heroService: ServiceInterface = HeroService()
): RepositoryInterface {
    override fun getFullList(): Flow<List<HeroDataModel>> =
        flow { emit(heroService.queryList()) }
}