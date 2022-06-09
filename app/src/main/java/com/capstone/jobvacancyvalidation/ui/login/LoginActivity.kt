package com.capstone.jobvacancyvalidation.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.capstone.jobvacancyvalidation.R
import com.capstone.jobvacancyvalidation.data.Login
import com.capstone.jobvacancyvalidation.data.UserPreferences
import com.capstone.jobvacancyvalidation.databinding.ActivityLoginBinding
import com.capstone.jobvacancyvalidation.network.api.ApiConfig
import com.capstone.jobvacancyvalidation.network.response.LoginResponse
import com.capstone.jobvacancyvalidation.ui.home.HomeActivity
import com.capstone.jobvacancyvalidation.ui.register.RegisterActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var mPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mPreferences = UserPreferences(this)

        if (mPreferences.getToken() != ""){
            startActivity(Intent((this@LoginActivity), HomeActivity::class.java))
            finish()
        }
        supportActionBar?.hide()

        binding.tvRegisterIntent.setOnClickListener {
            val signUpIntent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(signUpIntent)
            finish()
        }

        binding.loginButton.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        val email = binding.tfEmail.text.toString()
        val password = binding.tfPassword.text.toString().trim()

        binding.loginButton.isEnabled = false
        binding.pbLogin.visibility = View.VISIBLE
        ApiConfig().getApiService().loginUser(Login(email, password))
            .enqueue(object : Callback<LoginResponse> {

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.d("failure: ", t.message.toString())
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.code() == 200) {
                        mPreferences.setToken(response.body()!!.token)

                        binding.loginButton.isEnabled = true
                        binding.pbLogin.visibility = View.INVISIBLE

                        Toast.makeText(applicationContext, getString(R.string.success_login), Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                        finish()
                    } else {

                        binding.loginButton.isEnabled = true
                        binding.pbLogin.visibility = View.INVISIBLE

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