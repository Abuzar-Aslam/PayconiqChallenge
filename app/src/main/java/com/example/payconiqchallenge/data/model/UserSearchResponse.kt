package com.example.payconiqchallenge.data.model

import com.google.gson.annotations.SerializedName

/**
 * Represents a user search response from the API.
 *
 * @property totalCount The total count of search results.
 * @property incompleteResults Indicates if the search results are incomplete.
 * @property items The list of user items matching the search query.
 */
data class UserSearchResponse(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<UserItemResponse>
)