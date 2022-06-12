package com.capstone.jobvacancyvalidation.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.sql.Timestamp

@Parcelize
data class History(
    val UserID: Int,
    val input: String,
    val output: String,
    val created_at: Timestamp
): Parcelable
