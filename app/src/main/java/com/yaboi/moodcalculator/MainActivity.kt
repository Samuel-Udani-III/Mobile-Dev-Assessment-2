package com.yaboi.moodcalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var questionText: TextView
    private lateinit var answer1: Button
    private lateinit var answer2: Button
    private lateinit var answer3: Button

    private val questions = listOf(
        Question("How did you sleep last night?", listOf(
            MoodOption("😴 Great", 2),
            MoodOption("😐 Okay", 1),
            MoodOption("😩 Poorly", 0)
        )),
        Question("How's your energy today?", listOf(
            MoodOption("⚡ High", 2),
            MoodOption("😌 Normal", 1),
            MoodOption("😫 Low", 0)
        )),
        Question("How motivated do you feel?", listOf(
            MoodOption("🔥 Very", 2),
            MoodOption("🙂 A bit", 1),
            MoodOption("😕 Not at all", 0)
        )),
        Question("Do you feel stressed?", listOf(
            MoodOption("😌 Not really", 2),
            MoodOption("😐 A little", 1),
            MoodOption("😣 Very", 0)
        )),
        Question("How social are you feeling?", listOf(
            MoodOption("🤗 Super social", 2),
            MoodOption("🙂 Meh", 1),
            MoodOption("😶 I want to be alone", 0)
        )),
        Question("Are you enjoying today?", listOf(
            MoodOption("😁 A lot", 2),
            MoodOption("😐 It’s alright", 1),
            MoodOption("🙁 Not really", 0)
        )),
        Question("How productive have you been?", listOf(
            MoodOption("💪 Very", 2),
            MoodOption("😬 A little", 1),
            MoodOption("🫠 Not at all", 0)
        )),
        Question("Did you laugh today?", listOf(
            MoodOption("😂 Yes!", 2),
            MoodOption("🙂 Once or twice", 1),
            MoodOption("😐 Not yet", 0)
        )),
        Question("Do you feel appreciated?", listOf(
            MoodOption("🥰 Yes", 2),
            MoodOption("😶 Unsure", 1),
            MoodOption("😔 Not really", 0)
        )),
        Question("How anxious do you feel?", listOf(
            MoodOption("😌 Calm", 2),
            MoodOption("😬 Slightly", 1),
            MoodOption("😰 Very", 0)
        )),
        Question("Did you get outside today?", listOf(
            MoodOption("🌞 Yes", 2),
            MoodOption("🚪 A bit", 1),
            MoodOption("🏠 Nope", 0)
        )),
        Question("Are you feeling optimistic?", listOf(
            MoodOption("🌈 Absolutely", 2),
            MoodOption("😐 Kinda", 1),
            MoodOption("☁️ Not really", 0)
        )),
        Question("Did you eat well today?", listOf(
            MoodOption("🍎 Healthy meals", 2),
            MoodOption("🍟 Sort of", 1),
            MoodOption("😓 Skipped or junk", 0)
        )),
        Question("Do you feel focused?", listOf(
            MoodOption("🎯 Laser focused", 2),
            MoodOption("🧠 Distracted", 1),
            MoodOption("🫥 Out of it", 0)
        )),
        Question("Have you been kind to yourself?", listOf(
            MoodOption("💖 Yes", 2),
            MoodOption("🤷 A little", 1),
            MoodOption("🙁 No", 0)
        )),
        Question("How supported do you feel?", listOf(
            MoodOption("🫶 Very", 2),
            MoodOption("😐 Somewhat", 1),
            MoodOption("🙁 Alone", 0)
        )),
        Question("Are you worried about anything?", listOf(
            MoodOption("😌 Not really", 2),
            MoodOption("😬 A little", 1),
            MoodOption("😟 Yes", 0)
        )),
        Question("How was your last interaction with someone?", listOf(
            MoodOption("😊 Positive", 2),
            MoodOption("😐 Neutral", 1),
            MoodOption("😡 Negative", 0)
        )),
        Question("Do you feel creative?", listOf(
            MoodOption("🎨 Super creative", 2),
            MoodOption("🤔 A bit", 1),
            MoodOption("🫥 Not at all", 0)
        )),
        Question("How would you rate your mood right now?", listOf(
            MoodOption("😁 Great", 2),
            MoodOption("😐 Meh", 1),
            MoodOption("😢 Low", 0)
        ))
    )

    private var currentQuestionIndex = 0
    private var totalScore = 0

    private fun showQuestion() {
        if (currentQuestionIndex >= questions.size) {
            showResult()
            return
        }

        val current = questions[currentQuestionIndex]
        questionText.text = "${currentQuestionIndex + 1}/${questions.size} — ${current.text}"
        answer1.text = current.answers[0].emoji
        answer2.text = current.answers[1].emoji
        answer3.text = current.answers[2].emoji
    }

    private fun handleAnswer(selectedIndex: Int) {
        val selected = questions[currentQuestionIndex].answers[selectedIndex]
        totalScore += selected.score
        currentQuestionIndex++
        showQuestion()
    }

    private fun showResult() {
        val maxScore = questions.size * 2
        val percentage = totalScore.toDouble() / maxScore

        val (emoji, message) = when {
            percentage >= 0.75 -> "😊" to "You're in a great mood!"
            percentage >= 0.4 -> "😐" to "You're doing okay."
            else -> "😢" to "You seem a bit down. Try some self-care today."
        }

        val resultMessage = "$emoji\n\n$message"

        val dialog = androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("Your Mood Result")
            .setMessage(resultMessage)
            .setCancelable(false)
            .setPositiveButton("Restart Quiz") { _, _ ->
                currentQuestionIndex = 0
                totalScore = 0
                showQuestion()
            }
            .create()

        dialog.show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionText = findViewById(R.id.questionText)
        answer1 = findViewById(R.id.answer1)
        answer2 = findViewById(R.id.answer2)
        answer3 = findViewById(R.id.answer3)

        answer1.setOnClickListener { handleAnswer(0) }
        answer2.setOnClickListener { handleAnswer(1) }
        answer3.setOnClickListener { handleAnswer(2) }

        showQuestion()
    }
}
