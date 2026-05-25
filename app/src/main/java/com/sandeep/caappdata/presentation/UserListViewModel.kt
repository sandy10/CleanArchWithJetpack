package com.sandeep.caappdata.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandeep.caappdata.common.Resource
import com.sandeep.caappdata.domain.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UserListUiState>(UserListUiState.Idle)
    val uiState: StateFlow<UserListUiState> = _uiState.asStateFlow()

    fun fetchUsers() {
        viewModelScope.launch {
            userRepository.getUser().collect { result ->
                when(result) {
                    is Resource.Loading -> {
                        _uiState.value = UserListUiState.Loading
                    }

                    is Resource.Error -> {
                        _uiState.update {
                            UserListUiState.Error(result.message.orEmpty())
                        }
                    }

                    is Resource.Success -> {
                        _uiState.update {

                            UserListUiState.Success(
                                users = result.data ?: listOf()
                            )
                        }
                    }
                }
            }
        }
    }
}