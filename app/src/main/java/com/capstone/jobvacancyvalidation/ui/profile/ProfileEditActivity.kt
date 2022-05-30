package com.capstone.jobvacancyvalidation.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.jobvacancyvalidation.databinding.ActivityProfileEditBinding

class ProfileEditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
}