package com.example.payconiqchallenge.presentation.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.payconiqchallenge.presentation.model.UserDetailState
import kotlinx.coroutines.launch




//class UserDetailViewModel(private val userRepository: UserRepository) : ViewModel() {
//    private val _state = mutableStateOf(UserDetailState(loading = false, user = null))
//    val state: State<UserDetailState> = _state
//
//    fun getUserDetail(username: String) {
//        viewModelScope.launch {
//            _state.value = state.value.copy(loading = true)
//            try {
//                val userDetail = userRepository.getUserDetail(username)
//                _state.value = state.value.copy(user = userDetail)
//            } catch (e: Exception) {
//                _state.value = state.value.copy(error = e.message)
//            } finally {
//                _state.value = state.value.copy(loading = false)
//            }
//        }
//    }
//}