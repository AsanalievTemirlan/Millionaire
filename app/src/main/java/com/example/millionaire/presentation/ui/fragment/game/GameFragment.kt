package com.example.millionaire.presentation.ui.fragment.game

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.millionaire.R
import com.example.millionaire.data.model.HistoryEntity
import com.example.millionaire.databinding.FragmentGameBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameFragment : Fragment() {

    private val questions = Questions().getQuestions()
    private lateinit var binding: FragmentGameBinding
    private var currentQuestionIndex = 0
    private val viewModel: GameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("TAG", "${questions[0]}")
        showQuestion()
        initListener()
    }

    private fun initListener() = with(binding) {
        home.setOnClickListener { findNavController().navigateUp() }
        history.setOnClickListener { findNavController().navigate(R.id.historyFragment) }
    }

    private fun showQuestion() = with(binding) {
        if (currentQuestionIndex < questions.size) {
            val question = questions[currentQuestionIndex]
            btn1.text = question.answerA
            btn2.text = question.answerB
            btn3.text = question.answerC
            btn4.text = question.answerD
            tvQuestion.text = question.question
            tvMoney.text = question.money.toString()

            listOf(btn1, btn2, btn3, btn4).forEachIndexed { index, button ->
                button.setOnClickListener {
                    processAnswer(index + 1, question.correct, question.money)
                }
            }
        } else {
            showCompletionDialog()
        }
    }

    private fun processAnswer(answer: Int, correct: Int, money: Int) = with(binding) {
        val currentMoney = tvCount.text.toString().toIntOrNull() ?: 0
        if (correctAnswer(answer, correct)) {
            updateMoneyAndShowDialog(true, currentMoney + money, money)
        } else {
            showDialog(false, money.toString(), currentMoney)
        }
    }

    private fun correctAnswer(answer: Int, correct: Int) = answer == correct

    private fun showDialog(correct: Boolean, money: String, currentMoney: Int) {
        val builder = AlertDialog.Builder(requireContext())
        val title = if (correct) "Ответ верный" else "Вы проиграли"
        val message = if (correct) "Вы получили $money" else "Выход в главное меню"
        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                if (correct) {
                    currentQuestionIndex++
                    showQuestion()
                } else {
                    insertHistory("Проигрыш", currentMoney)
                    findNavController().navigateUp()
                }
            }
            .show()
    }

    private fun updateMoneyAndShowDialog(correct: Boolean, newMoney: Int, money: Int) = with(binding) {
        tvCount.text = newMoney.toString()
        showDialog(correct, money.toString(), newMoney)
    }

    private fun showCompletionDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val currentMoney = binding.tvCount.text.toString().toIntOrNull() ?: 0
        insertHistory("Победа", currentMoney)

        builder.setTitle("Поздравляем вы выиграли 1000000")
            .setMessage("Вы ответили на все вопросы!")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                findNavController().navigateUp()
            }
            .show()
    }

    private fun insertHistory(won: String, sum: Int) {
        val level = currentQuestionIndex + 1
        viewModel.insert(
            historyEntity = HistoryEntity(
                won = won,
                sum = sum,
                level = level
            )
        )
    }
}
