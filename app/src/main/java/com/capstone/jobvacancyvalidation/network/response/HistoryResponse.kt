package com.capstone.jobvacancyvalidation.network.response

import com.capstone.jobvacancyvalidation.data.History

data class HistoryResponse(
    val error: Int,
    val datas: ArrayList<History>
)
