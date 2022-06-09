package com.capstone.jobvacancyvalidation.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.jobvacancyvalidation.R
import com.capstone.jobvacancyvalidation.data.UserPreferences
import com.capstone.jobvacancyvalidation.databinding.ActivityHomeBinding
import com.capstone.jobvacancyvalidation.ui.login.LoginActivity
import com.capstone.jobvacancyvalidation.ui.profile.ProfileActivity
import com.capstone.jobvacancyvalidation.ui.validation.ValidationActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var mPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mPreferences = UserPreferences(this)

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
            MaterialAlertDialogBuilder(this, R.style.AlertDialogTheme)
                .setTitle(resources.getString(R.string.logout_dialog_title))
                .setMessage(resources.getString(R.string.logout_dialog_message))
                .setNegativeButton(resources.getString(R.string.cancel)) { dialog, which ->
                    // Respond to neutral button press
                }
                .setPositiveButton(resources.getString(R.string.logout)) { dialog, which ->
                    mPreferences.clearPreference()
                    val logoutIntent = Intent(this@HomeActivity, LoginActivity::class.java)
                    startActivity(logoutIntent)
                    finish()
                }
                .show()

        }
    }
}