package com.app.imdb.model

sealed class Resource<out T> {
    object Loading : Resource<Nothing>()

    data class Success<out T>(val data: T) : Resource<T>()

    data class ServerError(val message: String) : Resource<Nothing>()
    object InternetError : Resource<Nothing>()
    object EmptyDataError : Resource<Nothing>()
}
