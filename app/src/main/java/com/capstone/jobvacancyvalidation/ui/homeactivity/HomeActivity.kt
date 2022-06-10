package com.capstone.jobvacancyvalidation.ui.homeactivity

import android.content.ClipboardManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.jobvacancyvalidation.data.UserPreferences
import com.capstone.jobvacancyvalidation.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var mPreferences: UserPreferences
    private var myClipboard: ClipboardManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mPreferences = UserPreferences(this)
        myClipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager?

        supportActionBar?.hide()

//        binding.logoutButton.setOnClickListener {
//            MaterialAlertDialogBuilder(this, R.style.AlertDialogTheme)
//                .setTitle(resources.getString(R.string.logout_dialog_title))
//                .setMessage(resources.getString(R.string.logout_dialog_message))
//                .setNegativeButton(resources.getString(R.string.cancel)) { dialog, which ->
//                    // Respond to neutral button press
//                }
//                .setPositiveButton(resources.getString(R.string.logout)) { dialog, which ->
//                    mPreferences.clearPreference()
//                    val logoutIntent = Intent(this@HomeActivity, LoginActivity::class.java)
//                    startActivity(logoutIntent)
//                    finish()
//                }
//                .show()
//
//        }
    }
}