package com.capstone.jobvacancyvalidation.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.jobvacancyvalidation.R
import com.capstone.jobvacancyvalidation.databinding.ActivityHomeBinding
import com.capstone.jobvacancyvalidation.ui.login.LoginActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.logoutButton.setOnClickListener {
            val logoutIntent = Intent(this@HomeActivity, LoginActivity::class.java)
            startActivity(logoutIntent)
        }
    }
}