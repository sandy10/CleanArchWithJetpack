package com.sandeep.caappdata.data.repository

import com.sandeep.caappdata.common.Resource
import com.sandeep.caappdata.data.User
import com.sandeep.caappdata.data.remote.ApiService
import com.sandeep.caappdata.domain.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): UserRepository  {
    override fun getUser(): Flow<Resource<List<User>>> = flow {
        emit(Resource.Loading())

        try {
            val users = apiService.getUser()
            emit(Resource.Success(users))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message?: "Unknown Error"))
        }

    }
}