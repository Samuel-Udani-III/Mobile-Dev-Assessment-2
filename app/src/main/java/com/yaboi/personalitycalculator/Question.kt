package com.yaboi.personalitycalculator

data class Question(
    val text: String,
    val answers: List<MoodOption>
)

data class MoodOption(
    val emoji: String,
    val score: Int
)
