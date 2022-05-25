package com.capstone.jobvacancyvalidation.network.api

import com.capstone.jobvacancyvalidation.data.Login
import com.capstone.jobvacancyvalidation.data.Register
import com.capstone.jobvacancyvalidation.network.response.LoginResponse
import com.capstone.jobvacancyvalidation.network.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    fun loginUser(
        @Body login: Login
    ): Call<LoginResponse>

    @POST("register")
    fun registerUser(
        @Body register: Register
    ): Call<RegisterResponse>
}