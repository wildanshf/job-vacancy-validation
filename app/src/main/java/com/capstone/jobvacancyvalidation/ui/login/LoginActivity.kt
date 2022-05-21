package com.capstone.jobvacancyvalidation.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.jobvacancyvalidation.databinding.ActivityLoginBinding
import com.capstone.jobvacancyvalidation.ui.home.HomeActivity
import com.capstone.jobvacancyvalidation.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.tvRegisterIntent.setOnClickListener {
            val signUpIntent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(signUpIntent)
            finish()
        }

        binding.loginButton.setOnClickListener {
            val loginIntent = Intent(this@LoginActivity, HomeActivity::class.java)
            startActivity(loginIntent)
        }
    }

}