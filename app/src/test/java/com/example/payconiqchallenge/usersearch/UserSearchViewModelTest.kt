package com.example.payconiqchallenge.usersearch

import com.example.payconiqchallenge.data.repository.Result
import com.example.payconiqchallenge.domain.interactor.UserInteractor
import com.example.payconiqchallenge.domain.model.UserModel
import com.example.payconiqchallenge.presentation.model.UserSearchState
import com.example.payconiqchallenge.presentation.usersearch.UserSearchViewModel
import com.example.payconiqchallenge.provider.StringResourceProvider
import com.example.payconiqchallenge.utils.Constants.DEFAULT_PAGE
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals

@ExperimentalCoroutinesApi
class UserSearchViewModelTest {


    private lateinit var viewModel: UserSearchViewModel
    private lateinit var userInteractor: UserInteractor
    private lateinit var stringResourceProvider: StringResourceProvider

    private val testDispatcher = TestCoroutineDispatcher()


    @Before
    fun setup() {
        userInteractor = mockk()
        stringResourceProvider = mockk()
        Dispatchers.setMain(testDispatcher)
        viewModel = UserSearchViewModel(userInteractor, stringResourceProvider)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `onSearchTextChanged should initiate search with first page when query is not empty`() =
        runBlockingTest {
            // Arrange
            val query = "john"
            val initialState = UserSearchState(searchQuery = "", isLoading = false, error = "")

            // Act
            viewModel.userSearchState.value = initialState
            viewModel.onSearchTextChanged(query)

            // Assert
            assertEquals(query, viewModel.userSearchState.value.searchQuery)
            assertEquals(true, viewModel.userSearchState.value.isLoading)

            coVerify(exactly = 1) { userInteractor.searchUser(query, DEFAULT_PAGE) }
        }

    @Test
    fun `onSearchTextChanged should clear search state when query is empty`() = runBlockingTest {
        // Arrange
        val query = ""
        val initialState = UserSearchState(searchQuery = "john", isLoading = false, error = "")

        // Act
        viewModel.userSearchState.value = initialState
        viewModel.onSearchTextChanged(query)

        // Assert
        assertEquals(query, viewModel.userSearchState.value.searchQuery)
        assertEquals(false, viewModel.userSearchState.value.isLoading)
        assertEquals("", viewModel.userSearchState.value.error)
        assertEquals(emptyList<UserModel>(), viewModel.userSearchState.value.searchResults)
    }

    @Test
    fun `onLoadMore should not load next page of search results when there are no more pages available`() =
        runBlockingTest {
            // Arrange
            val query = "john"
            val initialState = UserSearchState(
                searchQuery = query,
                searchResults = listOf(
                    UserModel("john1", 1, "avatar1", "htmlUrl1"),
                    UserModel("john2", 2, "avatar2", "htmlUrl2")
                ),
                totalPageCount = 2,
                isLoading = false,
                error = ""
            )

            // Act
            viewModel.userSearchState.value = initialState
            viewModel.onLoadMore()

            // Assert
            assertEquals(query, viewModel.userSearchState.value.searchQuery)
            assertEquals(false, viewModel.userSearchState.value.isLoading)
            assertEquals("", viewModel.userSearchState.value.error)

            //coVerify{ userInteractor.searchUser(query, any()) } wasNot Called
        }

    @Test
    fun `onClearClick should reset search state to initial values`() = runBlockingTest {
        // Arrange
        val query = "john"
        val initialState = UserSearchState(
            searchQuery = query,
            searchResults = listOf(
                UserModel("john1", 1, "avatar1", "htmlUrl1"),
                UserModel("john2", 2, "avatar2", "htmlUrl2")
            ),
            totalPageCount = 2,
            isLoading = false,
            error = ""
        )

        // Act
        viewModel.userSearchState.value = initialState
        viewModel.onClearClick()

        // Assert
        assertEquals("", viewModel.userSearchState.value.searchQuery)
        assertEquals(false, viewModel.userSearchState.value.isLoading)
        assertEquals("", viewModel.userSearchState.value.error)
        assertEquals(emptyList<UserModel>(), viewModel.userSearchState.value.searchResults)
    }

    @Test
    fun `searchUsers should update search state with empty results and error message when search fails`() =
        runBlockingTest {
            // Arrange
            val query = "john"
            val initialState = UserSearchState(
                searchQuery = query,
                searchResults = emptyList(),
                isLoading = false,
                error = ""
            )
            val errorMessage = "Error occurred"

            coEvery { userInteractor.searchUser(query, DEFAULT_PAGE) } returns Result.Error(
                errorMessage
            )

            // Act
            viewModel.userSearchState.value = initialState
            viewModel.onSearchTextChanged(query)
            // Introduce a delay to allow the API response to complete
            delay(1000) // Adjust the delay time as needed

            // Assert
            assertEquals(query, viewModel.userSearchState.value.searchQuery)

            advanceUntilIdle()

            assertEquals(emptyList<UserModel>(), viewModel.userSearchState.value.searchResults)
            assertEquals(false, viewModel.userSearchState.value.isLoading)
            assertEquals(errorMessage, viewModel.userSearchState.value.error)

            coVerify(exactly = 1) { userInteractor.searchUser(query, DEFAULT_PAGE) }
        }

    @Test
    fun `test isNameValid with valid name`() {
        val validName = "John-Doe"
        val result = viewModel.isNameValid(validName)
        assertEquals(true, result)
    }

    @Test
    fun `test isNameValid with invalid name`() {
        val invalidName = "John123"
        val result = viewModel.isNameValid(invalidName)
        assertEquals(false, result)
    }

}