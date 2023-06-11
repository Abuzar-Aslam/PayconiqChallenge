package com.example.payconiqchallenge.provider

import android.content.Context

/**

 *Implementation of the StringResourceProvider interface for Android platform.
 *@param androidContext The Android Context object used for accessing string resources.
 */
class AndroidStringResourceProvider(private val androidContext: Context) : StringResourceProvider {

    /**
     *Retrieves the string resource associated with the given resource ID.
     *@param resId The ID of the string resource.
     *@return The string value of the resource.
     */
    override fun getString(resId: Int): String {
        return androidContext.getString(resId)
    }
}