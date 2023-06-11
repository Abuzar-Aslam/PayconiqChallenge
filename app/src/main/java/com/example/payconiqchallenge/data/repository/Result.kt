package com.example.payconiqchallenge.data.repository

/**
 * A sealed class representing the result of an operation.
 */
sealed class Result<out T : Any> {

    /**
     * Represents a successful result with data of type [T].
     *
     * @property data The data associated with the successful result.
     */
    data class Success<out T : Any>(val data: T) : Result<T>()

    /**
     * Represents an error result with an error message.
     *
     * @property message The error message associated with the result.
     */
    data class Error(val message: String) : Result<Nothing>()
}