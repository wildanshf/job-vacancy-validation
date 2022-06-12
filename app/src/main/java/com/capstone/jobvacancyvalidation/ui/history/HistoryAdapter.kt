package com.capstone.jobvacancyvalidation.ui.history

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.capstone.jobvacancyvalidation.data.History
import com.capstone.jobvacancyvalidation.databinding.ItemRowHistoryBinding

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    private var historyList: List<History>? = null

    inner class HistoryViewHolder(private val binding: ItemRowHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: History) {

//            itemView.setOnClickListener{
//                val intent = Intent(itemView.context, DetailActivity::class.java)
//                intent.putExtra("Story", data)
//
//                val optionsCompat: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                    itemView.context as Activity,
//                    Pair(binding.ivPhoto, "image"),
//                    Pair(binding.tvName, "name"),
//                )
//
//                itemView.context.startActivity(intent, optionsCompat.toBundle())
//            }

            binding.apply {
                tvInput.text = data.input
                tvOutput.text = data.output

            }

        }
    }
    fun setHistoryList(historyList: List<History>?) {
        this.historyList = historyList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = ItemRowHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(historyList?.get(position)!!)
    }

    override fun getItemCount(): Int {
        return if(historyList == null) 0
        else historyList?.size!!
    }
}