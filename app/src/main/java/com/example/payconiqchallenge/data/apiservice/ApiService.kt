package com.example.payconiqchallenge.data.apiservice

import com.example.payconiqchallenge.data.model.UserDetailResponse
import com.example.payconiqchallenge.data.model.UserRepositoryResponse
import com.example.payconiqchallenge.data.model.UserSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit service interface for making API requests related to recipes.
 */
interface ApiService {

    /**
     * Fetches a list of recipes from the API.
     *
     * @return A list of recipes.
     */
    @GET("search/users")
    suspend fun searchUsers(@Query("q") query: String): UserSearchResponse

    @GET("users/{username}")
    suspend fun getUserDetail(@Path("username") username: String): UserDetailResponse


    @GET("users/{username}/repos")
    suspend fun getUserRepository(@Path("username") username: String): List<UserRepositoryResponse>

}