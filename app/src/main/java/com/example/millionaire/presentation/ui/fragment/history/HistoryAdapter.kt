package com.example.millionaire.presentation.ui.fragment.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.millionaire.data.model.HistoryEntity
import com.example.millionaire.databinding.ItemHistoryBinding
import com.example.millionaire.presentation.ui.interfaces.OnClick


class HistoryAdapter(
    private val onLongClickItem: OnClick
) : androidx.recyclerview.widget.ListAdapter<HistoryEntity, HistoryAdapter.ViewHolder>(
    DiffCallback()
) {

    class ViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: HistoryEntity) = with(binding) {
            tvWon.text = model.won
            tvMoney.text = "${model.sum} сом"
            tvLevel.text = "Уровень ${model.level}"
            tvGame.text = "Игра ${model.id}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnLongClickListener {
            onLongClickItem.onLongClick(getItem(position))
            true
        }

    }

    class DiffCallback : DiffUtil.ItemCallback<HistoryEntity>() {
        override fun areItemsTheSame(oldItem: HistoryEntity, newItem: HistoryEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HistoryEntity, newItem: HistoryEntity): Boolean {
            return oldItem == newItem
        }
    }
}