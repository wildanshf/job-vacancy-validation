package com.capstone.jobvacancyvalidation.network.response

import java.sql.Timestamp
import java.util.*

data class UserResponse(
    val name: String,
    val email: String,
    val profilePhoto: String,
    val phonenumber: String,
    val birthday: Date,
    val nation: String,
    val updated_at: Timestamp
)
