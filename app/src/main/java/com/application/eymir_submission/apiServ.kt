package com.application.eymir_submission

import retrofit2.Call
import retrofit2.http.GET

interface ApiServ {
    @GET("users")
    fun getUsers(): Call<UserResponse>
}