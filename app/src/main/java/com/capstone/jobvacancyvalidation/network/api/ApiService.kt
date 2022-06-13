package com.capstone.jobvacancyvalidation.network.api

import com.capstone.jobvacancyvalidation.data.Login
import com.capstone.jobvacancyvalidation.data.Register
import com.capstone.jobvacancyvalidation.data.User
import com.capstone.jobvacancyvalidation.network.response.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("login")
    fun loginUser(
        @Body login: Login
    ): Call<LoginResponse>

    @POST("register")
    fun registerUser(
        @Body register: Register
    ): Call<RegisterResponse>

    @GET("predict")
    fun validateJob(
        @Header("Authorization") token: String,
        @Query("input") input: String
    ): Call<ValidationResponse>

    @POST("bio")
    fun getUserDetail(
        @Header("Authorization") token: String,
        @Body user: User
    ): Call<UserResponse>

    @GET("history")
    fun getUserHistory(
        @Header("Authorization") token: String,
        @Query("userid") userid : Int
    ): Call<HistoryResponse>
}