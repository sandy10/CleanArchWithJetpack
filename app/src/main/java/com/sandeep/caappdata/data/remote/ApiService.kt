package com.sandeep.caappdata.data.remote

import com.sandeep.caappdata.data.User
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUser(): List<User>
}