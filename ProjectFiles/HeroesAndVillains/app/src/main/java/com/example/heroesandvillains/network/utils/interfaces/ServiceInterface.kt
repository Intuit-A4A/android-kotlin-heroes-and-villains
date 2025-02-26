package com.example.heroesandvillains.network.utils.interfaces

import com.example.heroesandvillains.data.HeroDataModel

/**
 * Interface to define the service methods
 */
interface ServiceInterface {

    /**
     * This API is different from the get() function, as some API (V3) has different end point for
     * get individual entity and query list of entity. This API is used by passing sql query as retrofit
     * query parameter to the network request
     * @param searchQuery: search query to be passed to the query string. This will be the search
     * query user type on the list top search bar. When the host app/module implements
     * @return: Return list of identifiable entities found
     */
    suspend fun queryList(): List<HeroDataModel>
}