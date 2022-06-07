package com.capstone.jobvacancyvalidation.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.jobvacancyvalidation.R
import com.capstone.jobvacancyvalidation.databinding.ActivityHomeBinding
import com.capstone.jobvacancyvalidation.ui.login.LoginActivity
import com.capstone.jobvacancyvalidation.ui.profile.ProfileActivity
import com.capstone.jobvacancyvalidation.ui.validation.ValidationActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.validationCard.setOnClickListener {
            val validationIntent = Intent(this@HomeActivity, ValidationActivity::class.java)
            startActivity(validationIntent)
        }

        binding.profileCard.setOnClickListener {
            val profileIntent = Intent(this@HomeActivity, ProfileActivity::class.java)
            startActivity(profileIntent)
        }

        binding.logoutButton.setOnClickListener {
            val logoutIntent = Intent(this@HomeActivity, LoginActivity::class.java)
            startActivity(logoutIntent)
        }
    }
}