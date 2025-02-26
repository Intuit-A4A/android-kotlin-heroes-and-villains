package com.example.heroesandvillains.network.utils

/**
 * Note: This is part of the network layer infrastructure, and not required to be changed
 * in user stories as part of the craft A4A
 *
 * Exception class to handle network communication errors
 */
class NetworkCommunicationException(message: String) : Exception(message) {

    private val throwable: Throwable = this
    private var errors: List<Error>? = null
    private var code: String? = null
    private var data: Any? = null
}