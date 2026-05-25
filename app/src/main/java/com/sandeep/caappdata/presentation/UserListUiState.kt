package com.sandeep.caappdata.presentation

import com.sandeep.caappdata.data.User

//data class UserListUiState (
//    val users: List<User> = emptyList(),
//    val error: String? = null,
//    val isLoading: Boolean = false
//)

sealed interface UserListUiState {
    data object Loading : UserListUiState
    data class Success(val users: List<User>): UserListUiState
    data class Error(val errorMessage: String): UserListUiState
    data object Idle : UserListUiState
}