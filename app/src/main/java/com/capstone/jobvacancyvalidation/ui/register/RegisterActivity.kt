package com.capstone.jobvacancyvalidation.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.capstone.jobvacancyvalidation.R
import com.capstone.jobvacancyvalidation.data.Register
import com.capstone.jobvacancyvalidation.databinding.ActivityRegisterBinding
import com.capstone.jobvacancyvalidation.network.api.ApiConfig
import com.capstone.jobvacancyvalidation.network.response.RegisterResponse
import com.capstone.jobvacancyvalidation.ui.login.LoginActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.tvLoginIntent.setOnClickListener {
            val loginIntent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(loginIntent)
            finish()
        }

        binding.registerButton.setOnClickListener{
            registerUser()
        }
    }

    private fun registerUser() {
        val name = binding.tfName.text.toString()
        val email = binding.tfEmail.text.toString()
        val password = binding.tfPassword.text.toString().trim()

        binding.registerButton.isEnabled = false
        binding.pbRegister.visibility = View.VISIBLE
        ApiConfig().getApiService().registerUser(Register(name, email, password))
            .enqueue(object : Callback<RegisterResponse> {

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Log.d("failure: ", t.message.toString())
                }

                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    if (response.code() == 201) {
                        binding.registerButton.isEnabled = true
                        binding.pbRegister.visibility = View.INVISIBLE

                        Toast.makeText(applicationContext, getString(R.string.success_login), Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))

                    } else {

                        binding.registerButton.isEnabled = true
                        binding.pbRegister.visibility = View.INVISIBLE

                        Toast.makeText(applicationContext, getString(R.string.invalid_credentials), Toast.LENGTH_SHORT).show()

                        Log.d(
                            LoginActivity::class.java.simpleName,
                            response.body()?.message.toString()
                        )
                    }
                }

            })


    }
}