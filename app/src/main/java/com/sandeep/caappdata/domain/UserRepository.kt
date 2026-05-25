package com.sandeep.caappdata.domain

import com.sandeep.caappdata.common.Resource
import com.sandeep.caappdata.data.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUser(): Flow<Resource<List<User>>>
}