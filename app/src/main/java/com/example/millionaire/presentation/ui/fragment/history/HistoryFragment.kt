package com.example.millionaire.presentation.ui.fragment.history

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.millionaire.R
import com.example.millionaire.data.model.HistoryEntity
import com.example.millionaire.databinding.FragmentHistoryBinding
import com.example.millionaire.presentation.ui.interfaces.OnClick
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HistoryFragment : Fragment(), OnClick {

    private lateinit var binding: FragmentHistoryBinding
    private val viewModel: HistoryViewModel by viewModels()
    private lateinit var adapter: HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initListener()
    }

    private fun initListener() = with(binding) {
        home.setOnClickListener { findNavController().navigateUp() }
    }

    private fun initAdapter() {
        viewModel.getHistory().observe(viewLifecycleOwner, { history ->
            adapter.submitList(history)
        })
        adapter = HistoryAdapter(onLongClickItem = this)
        binding.rvHistory.adapter = adapter
    }


    override fun onLongClick(historyEntity: HistoryEntity) {
        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setTitle("Удалить")
            setMessage("Хотите удалить результат")
            setPositiveButton("Да") { dialog, which ->
                lifecycleScope.launch {
                    viewModel.delete(historyEntity)
                }
            }
            setNegativeButton("Нет") { dialog, which ->
                dialog.dismiss()
            }
            show()
        }
    }

}