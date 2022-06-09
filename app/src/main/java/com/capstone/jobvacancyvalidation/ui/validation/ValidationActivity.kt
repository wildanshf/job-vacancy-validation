package com.capstone.jobvacancyvalidation.ui.validation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.capstone.jobvacancyvalidation.R
import com.capstone.jobvacancyvalidation.data.UserPreferences
import com.capstone.jobvacancyvalidation.databinding.ActivityValidationBinding
import com.capstone.jobvacancyvalidation.network.api.ApiValidationConfig
import com.capstone.jobvacancyvalidation.network.response.ValidationResponse
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ValidationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityValidationBinding
    private lateinit var mPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityValidationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mPreferences = UserPreferences(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val token = mPreferences.getToken()

        binding.buttonValidation.setOnClickListener {
            val kontol = binding.tfUserInput.text.toString()

            postValidateInput(token, kontol)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun showValidJobDialog() {
        MaterialAlertDialogBuilder(this, R.style.AlertDialogTheme)
            .setTitle(resources.getString(R.string.validation_positive_result))
            .setMessage(resources.getString(R.string.logout_dialog_message))
            .setNegativeButton(resources.getString(R.string.cancel)) { dialog, which ->
                // Respond to neutral button press
            }
            .setPositiveButton(resources.getString(R.string.logout)) { dialog, which ->
                // KONTOL
            }
            .show()
    }

    private fun showFraudJobDialog() {
        MaterialAlertDialogBuilder(this, R.style.AlertDialogTheme)
            .setTitle(resources.getString(R.string.validation_negative_result))
            .setMessage(resources.getString(R.string.logout_dialog_message))
            .setNegativeButton(resources.getString(R.string.cancel)) { dialog, which ->
                // Respond to neutral button press
            }
            .setPositiveButton(resources.getString(R.string.logout)) { dialog, which ->
                // KONTOL
            }
            .show()
    }

    private fun postValidateInput(tokenAuth: String, inputUser: String) {
        when {
            (binding.tfUserInput.text.toString() != "") -> {
                Log.d(this@ValidationActivity::class.java.simpleName, tokenAuth)
                binding.buttonValidation.isEnabled = false
                binding.pbValidation.visibility = View.VISIBLE
                ApiValidationConfig().getApiService().validateJob(token = "Bearer $tokenAuth", inputUser)
                    .enqueue(object : Callback<ValidationResponse> {
                        override fun onFailure(call: Call<ValidationResponse>, t: Throwable) {
                            Log.d("failure: ", t.message.toString())
                        }
                        override fun onResponse(
                            call: Call<ValidationResponse>,
                            response: Response<ValidationResponse>
                        ) {
                            if (response.code() == 200) {
                                binding.buttonValidation.isEnabled = true
                                binding.pbValidation.visibility = View.INVISIBLE

                                if (response.body()?.Prediction.toString() == "Fraud Job") {
                                    showFraudJobDialog()
                                } else if (response.body()?.Prediction.toString() == "Real Job") {
                                    showValidJobDialog()
                                }
                                binding.tvValidationResult.text = response.body()?.Prediction.toString()

                            } else {
                                binding.buttonValidation.isEnabled = true
                                binding.pbValidation.visibility = View.INVISIBLE

                                Toast.makeText(applicationContext, getString(R.string.invalid_credentials), Toast.LENGTH_SHORT).show()

                            }
                        }
                    })
            }
            (binding.tfUserInput.text.toString() == "") -> {
                Toast.makeText(applicationContext, getString(R.string.empty_input_warning), Toast.LENGTH_SHORT).show()
            }
        }
    }
}