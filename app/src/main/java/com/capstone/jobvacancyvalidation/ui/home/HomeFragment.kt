package com.capstone.jobvacancyvalidation.ui.home

import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.capstone.jobvacancyvalidation.R
import com.capstone.jobvacancyvalidation.data.UserPreferences
import com.capstone.jobvacancyvalidation.databinding.FragmentHomeBinding
import com.capstone.jobvacancyvalidation.network.api.ApiValidationConfig
import com.capstone.jobvacancyvalidation.network.response.ValidationResponse
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private lateinit var mPreferences: UserPreferences
    private var myClipboard: ClipboardManager? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPreferences = UserPreferences(requireActivity())
        myClipboard = requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?

        val token = mPreferences.getToken()
        val btnValidate:Button = view.findViewById(R.id.validate_button)
        btnValidate.setOnClickListener {
            val userInput = binding.tfParameter.text.toString()
            postValidateInput(token, userInput)
        }

        val btnPaste:ImageView = view.findViewById(R.id.paste_button)


        val tb = view.findViewById(R.id.tf_parameter) as EditText
        binding.pasteButton.setOnClickListener {
            pasteText()
        }

        tb .addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                val userInput = binding.tfParameter.text.toString()
                if (userInput == ""){
                    btnPaste.setImageResource(R.drawable.ic_paste_button)
                    btnPaste.setOnClickListener {
                        pasteText()
                    }
                }
                else{
                    btnPaste.setImageResource(R.drawable.ic_baseline_close_24)
                    btnPaste.setOnClickListener {
                        clearText()
                    }
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showValidJobDialog() {
        MaterialAlertDialogBuilder(requireActivity(), R.style.AlertDialogTheme)
            .setIcon(R.drawable.ic_real_job)
            .setTitle(resources.getString(R.string.validation_positive_result))
            .setMessage(resources.getString(R.string.validation_positive_dialog_message))
            .setPositiveButton("OK") { _, _ ->

            }
            .show()

    }

    private fun showFraudJobDialog() {
        MaterialAlertDialogBuilder(requireActivity(), R.style.AlertDialogTheme)
            .setIcon(R.drawable.ic_fraud_job)
            .setTitle(resources.getString(R.string.validation_negative_result))
            .setMessage(resources.getString(R.string.validation_negative_dialog_message))
            .setPositiveButton("OK") { _, _ ->

            }
            .show()
    }

    private fun postValidateInput(tokenAuth: String, inputUser: String) {
        when {
            (binding.tfParameter.text.toString() != "") -> {
                Log.d(this@HomeFragment::class.java.simpleName, tokenAuth)
                binding.validateButton.isEnabled = false
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
                                binding.validateButton.isEnabled = true
                                binding.pbValidation.visibility = View.INVISIBLE

                                if (response.body()?.Prediction.toString() == "Fraud Job") {
                                    showFraudJobDialog()
                                } else if (response.body()?.Prediction.toString() == "Real Job") {
                                    showValidJobDialog()
                                }

                            } else {
                                binding.validateButton.isEnabled = true
                                binding.pbValidation.visibility = View.INVISIBLE

                                Toast.makeText(requireActivity(), getString(R.string.invalid_credentials), Toast.LENGTH_SHORT).show()

                            }
                        }
                    })
            }
            (binding.tfParameter.text.toString() == "") -> {
                Toast.makeText(requireActivity(), getString(R.string.empty_input_warning), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun pasteText() {

        val a = myClipboard?.primaryClip?.getItemAt(0)

        if (a.toString() == "") {
            Toast.makeText(requireActivity(), getString(R.string.empty_clipboard_warning), Toast.LENGTH_SHORT).show()
        } else binding.tfParameter.text = Editable.Factory.getInstance().newEditable(a?.text)
    }

    private fun clearText() {
        binding.tfParameter.text = Editable.Factory.getInstance().newEditable("")
    }
}