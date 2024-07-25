package com.example.millionaire.data.model

data class QuestionModel(
    val question: String,
    val answerA: String,
    val answerB: String,
    val answerC: String,
    val answerD: String,
    val correct: Int,
    val money: Int,
)