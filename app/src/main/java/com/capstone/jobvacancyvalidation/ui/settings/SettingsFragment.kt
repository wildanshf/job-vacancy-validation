package com.capstone.jobvacancyvalidation.ui.settings

import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.capstone.jobvacancyvalidation.R
import com.capstone.jobvacancyvalidation.data.User
import com.capstone.jobvacancyvalidation.data.UserPreferences
import com.capstone.jobvacancyvalidation.databinding.FragmentSettingsBinding
import com.capstone.jobvacancyvalidation.network.api.ApiConfig
import com.capstone.jobvacancyvalidation.network.api.ApiValidationConfig
import com.capstone.jobvacancyvalidation.network.response.UserResponse
import com.capstone.jobvacancyvalidation.network.response.ValidationResponse
import com.capstone.jobvacancyvalidation.ui.MainActivity
import com.capstone.jobvacancyvalidation.ui.aboutus.AboutUsActivity
import com.capstone.jobvacancyvalidation.ui.login.LoginActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private lateinit var mPreferences: UserPreferences

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPreferences = UserPreferences(requireActivity())

        val token = mPreferences.getToken()
        val userId = mPreferences.getUserId().toInt()

        getUserDetail(token, userId)
        binding.rlAboutUs.setOnClickListener {

            startActivity(Intent(requireActivity(), AboutUsActivity::class.java))
        }

        binding.rlLogout.setOnClickListener {
            MaterialAlertDialogBuilder(requireActivity(), R.style.AlertDialogTheme)
                .setTitle(resources.getString(R.string.logout_dialog_title))
                .setMessage(resources.getString(R.string.logout_dialog_message))
                .setNegativeButton(resources.getString(R.string.cancel)) { dialog, which ->
                }
                .setPositiveButton(resources.getString(R.string.logout)) { dialog, which ->
                    mPreferences.clearPreference()
                    startActivity(Intent(requireActivity(), LoginActivity::class.java))
                }
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getUserDetail(tokenAuth: String, userId: Int) {
        Log.d(this@SettingsFragment::class.java.simpleName, tokenAuth)
        ApiConfig().getApiService().getUserDetail(token = "Bearer $tokenAuth", User(userId))
            .enqueue(object : Callback<UserResponse> {
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d("failure: ", t.message.toString())
                }
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.code() == 200) {
                        binding.tvName.text = response.body()?.name ?: "Name"
                        binding.tvEmail.text = response.body()?.email ?: "email@mail.com"
                    } else {

                        Toast.makeText(requireActivity(), getString(R.string.invalid_credentials), Toast.LENGTH_SHORT).show()

                    }
                }
            })
    }
}