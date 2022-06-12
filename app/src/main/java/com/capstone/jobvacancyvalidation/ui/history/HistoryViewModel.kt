package com.capstone.jobvacancyvalidation.ui.history

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.jobvacancyvalidation.data.History
import com.capstone.jobvacancyvalidation.data.UserPreferences
import com.capstone.jobvacancyvalidation.network.api.ApiConfig
import com.capstone.jobvacancyvalidation.network.response.HistoryResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryViewModel : ViewModel() {

    private lateinit var mPreferences: UserPreferences
    val listHistory = MutableLiveData<ArrayList<History>?>()

    fun setHistory(tokenAuth: String, userid: Int) {
        Log.d(this@HistoryViewModel::class.java.simpleName, tokenAuth)
        ApiConfig().getApiService().getUserHistory(token = "Bearer $tokenAuth", userid)
            .enqueue(object : Callback<HistoryResponse> {
                override fun onFailure(call: Call<HistoryResponse>, t: Throwable) {
                    listHistory.postValue(null)
                }
                override fun onResponse(
                    call: Call<HistoryResponse>,
                    response: Response<HistoryResponse>
                ) {
                    if (response.code() == 200) {
                        listHistory.postValue(response.body()?.datas)
                    } else {
                        listHistory.postValue(null)
                    }
                }

            })
    }

    fun getHistory(): MutableLiveData<ArrayList<History>?> {
        return listHistory
    }
}