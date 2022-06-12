package com.capstone.jobvacancyvalidation.ui.history

import android.annotation.SuppressLint
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.jobvacancyvalidation.data.UserPreferences
import com.capstone.jobvacancyvalidation.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {
    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var adapter: HistoryAdapter
    private lateinit var mPreferences: UserPreferences
    private var _binding: FragmentHistoryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHistoryBinding.inflate(inflater, container, false)

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPreferences = UserPreferences(requireActivity())

        historyViewModel = ViewModelProvider(this@HistoryFragment)[HistoryViewModel::class.java]
        val token = mPreferences.getToken()
        val userId = mPreferences.getUserId().toInt()

        initRecyclerView()

        historyViewModel.setHistory(token, userId)
        historyViewModel.getHistory().observe(requireActivity()) {
            if (it != null) {
                adapter.setHistoryList(it)
                adapter.notifyDataSetChanged()
                showLoading(false)
            } else {
                showLoading(false)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecyclerView() {
        binding.rvHistory.layoutManager = LinearLayoutManager(requireActivity())
        adapter = HistoryAdapter()
        binding.rvHistory.adapter = adapter
    }

    private fun showLoading(state: Boolean) {
        if(state) {
            binding.pbHistory.visibility = View.VISIBLE
        } else {
            binding.pbHistory.visibility = View.GONE
        }
    }
}