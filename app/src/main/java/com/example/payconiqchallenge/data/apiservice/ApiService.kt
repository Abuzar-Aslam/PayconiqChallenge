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
     * Fetches a list of users from the API based on the provided query and page number.
     *
     * @param query The search query used to filter the users.
     * @param page The page number of the results to retrieve.
     * @param perPage The number of users to retrieve per page (default value is PER_PAGE).
     * @return A UserSearchResponse object containing the list of users.
     */
    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = PER_PAGE
    ): UserSearchResponse

    /**
     * Fetches the details of a specific user from the API based on the provided username.
     *
     * @param username The username of the user to retrieve the details for.
     * @return A UserDetailResponse object containing the details of the specific user.
     */
    @GET("users/{username}")
    suspend fun getUserDetail(
        @Path("username") username: String
    ): UserDetailResponse

    /**
     * Fetches a list of repositories belonging to a specific user from the API.
     *
     * @param username The username of the user for which to retrieve the repositories.
     * @return A list of UserRepositoryResponse objects representing the repositories of the user.
     */
    @GET("users/{username}/repos")
    suspend fun getUserRepository(@Path("username") username: String): List<UserRepositoryResponse>
}