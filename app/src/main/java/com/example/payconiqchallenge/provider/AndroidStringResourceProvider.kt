package com.example.payconiqchallenge.provider

import android.content.Context

class AndroidStringResourceProvider(private val androidContext: Context) : StringResourceProvider {
    override fun getString(resId: Int): String {
        return androidContext.getString(resId)
    }
}