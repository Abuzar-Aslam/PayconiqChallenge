package com.example.payconiqchallenge.data.apiservice

import com.example.payconiqchallenge.data.model.UserDetailResponse
import com.example.payconiqchallenge.data.model.UserRepositoryResponse
import com.example.payconiqchallenge.data.model.UserSearchResponse
import com.example.payconiqchallenge.utils.Constants.PER_PAGE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit service interface for making API requests related to recipes.
 */
interface ApiService {

    /**
     * Fetches a list of users from the API.
     *
     * @return A list of users.
     */
    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = PER_PAGE
    ): UserSearchResponse

    /**
     * Fetches a detail of specific users from the API.
     *
     * @return user detail object of specific user.
     */
    @GET("users/{username}")
    suspend fun getUserDetail(
        @Path("username") username: String,
        @Path("page") currentPage: Int
    ): UserDetailResponse

    /**
     * Fetches a list of repos of specific user from the API.
     *
     * @return list of repos of specific user.
     */
    @GET("users/{username}/repos")
    suspend fun getUserRepository(@Path("username") username: String): List<UserRepositoryResponse>

}