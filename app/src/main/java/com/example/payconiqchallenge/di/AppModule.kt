package com.example.payconiqchallenge.di

import com.example.payconiqchallenge.data.repository.UserSearchRepository
import com.example.payconiqchallenge.data.repository.UserSearchRepositoryImpl
import com.example.payconiqchallenge.domain.interactor.UserInteractor
import com.example.payconiqchallenge.presentation.usersearch.UserSearchViewModel
import com.example.payconiqchallenge.data.apiservice.ApiService
import com.example.payconiqchallenge.data.repository.UserDetailRepository
import com.example.payconiqchallenge.data.repository.UserDetailRepositoryImpl
import com.example.payconiqchallenge.data.repository.UserRepoRepository
import com.example.payconiqchallenge.data.repository.UserRepoRepositoryImpl
import com.example.payconiqchallenge.domain.interactor.UserDetailInteractor
import com.example.payconiqchallenge.presentation.userdetail.UserDetailViewModel
import com.example.payconiqchallenge.provider.AndroidStringResourceProvider
import com.example.payconiqchallenge.provider.StringResourceProvider
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Defines app module which will inject the component which is required by the application
 */
val appModule = module {

    // Define a single instance of Retrofit using the provided BASE_URL, OkHttpClient, and
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Define a single instance of OkHttpClient with default settings
    single {
        //Can be updated later to set the timeout for the request like connectionTimeout and pass TimeUnit
        OkHttpClient.Builder()
            .build()
    }

    // Define a single instance of ApiService using Retrofit to create the API service
    single<ApiService> {
        get<Retrofit>().create(ApiService::class.java)
    }

    // Define a single instance of RecipeRepository using the provided ApiService
    single<UserSearchRepository> { UserSearchRepositoryImpl(get()) }
    single<UserDetailRepository> { UserDetailRepositoryImpl(get()) }
    single<UserRepoRepository> { UserRepoRepositoryImpl(get()) }

    // Define a single instance of RecipeRepository using the provided ApiService
    single { UserInteractor(get()) }
    single { UserDetailInteractor(get(), get()) }

    single<StringResourceProvider> { AndroidStringResourceProvider(androidContext()) }


    // Define the RecipeViewModel using the provided RecipeRepository
    viewModel { UserSearchViewModel(get(),get()) }
    viewModel { UserDetailViewModel(get(), get()) }

}

// Base URL for the API service
private const val BASE_URL = "https://api.github.com/"
