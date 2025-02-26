package com.example.heroesandvillains.data

/**
 * Note: This is part of the data layer infrastructure, and not required to be changed
 *
 * Generic class that contains data and status about loading data
 */
sealed class DataAccessState<out T : Any> {
    data object Loading : DataAccessState<Nothing>()
    data class Success<out T : Any>(val data: T) : DataAccessState<T>()
    data class Error(val exception: Throwable) : DataAccessState<Nothing>()
}