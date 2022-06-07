package com.capstone.jobvacancyvalidation.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.jobvacancyvalidation.databinding.ActivityProfileBinding
import com.capstone.jobvacancyvalidation.ui.validation.ValidationActivity

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        binding.editButton.setOnClickListener{
            val profileEditIntent = Intent(this@ProfileActivity, ProfileEditActivity::class.java)
            startActivity(profileEditIntent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}