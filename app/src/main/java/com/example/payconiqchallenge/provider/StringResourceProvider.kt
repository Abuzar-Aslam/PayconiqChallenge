package com.example.payconiqchallenge.provider

/**
 * Interface for providing string resources.
 */
interface StringResourceProvider {

    /**
     * Retrieves the string resource associated with the given resource ID.
     *
     * @param resId The ID of the string resource.
     * @return The string value of the resource.
     */
    fun getString(resId: Int): String
}