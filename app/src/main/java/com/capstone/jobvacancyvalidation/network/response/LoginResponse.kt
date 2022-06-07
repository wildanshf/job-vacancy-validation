package com.capstone.jobvacancyvalidation.network.response

data class LoginResponse (
    val error: Int,
    val message: String,
    val userid: Int,
    val token: String
)