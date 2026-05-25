package com.sandeep.caappdata.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun UserScreen(viewModel: UserListViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.fetchUsers()
    }

    val state by viewModel.uiState.collectAsState()

    Column(
       modifier =  Modifier.fillMaxWidth()
    ) {
        when(state) {
            is UserListUiState.Idle -> {

            }

            is UserListUiState.Loading -> {
                Box(contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is UserListUiState.Success -> {
                val users = (state as UserListUiState.Success).users
                Box(contentAlignment = Alignment.Center) {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(users) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(it.name)
                                Spacer(modifier = Modifier.padding(8.dp))
                                Text(it.email)
                                Spacer(modifier = Modifier.padding(8.dp))
                                Text("${it.id}")
                            }
                        }
                    }
                }
            }

            is UserListUiState.Error -> {

                Box(contentAlignment = Alignment.Center) {
                    Text("Try again")
                }
            }
        }
    }
}