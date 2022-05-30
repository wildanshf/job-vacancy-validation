package com.capstone.jobvacancyvalidation.network.response

data class LoginResponse (
    val error: Boolean,
    val message: String,
    val userid: Int,
    val token: String
)