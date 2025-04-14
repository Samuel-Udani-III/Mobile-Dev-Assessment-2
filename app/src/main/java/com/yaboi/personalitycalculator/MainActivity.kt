package com.yaboi.personalitycalculator // Change this to match the namespace


import com.yaboi.personalitycalculator.R
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var questionText: TextView
    private lateinit var answer1: Button
    private lateinit var answer2: Button
    private lateinit var answer3: Button

    private val questions = listOf(
        Question("How would you describe your ideal weekend?", listOf(
            MoodOption("Relaxing and quiet", 1),
            MoodOption("Busy with activities", 2),
            MoodOption("Social with friends", 3)
        )),
        Question("How do you feel about deadlines?", listOf(
            MoodOption("I thrive under pressure", 3),
            MoodOption("I prefer to avoid them", 1),
            MoodOption("I can manage them fine", 2)
        )),
        Question("How do you usually handle stress?", listOf(
            MoodOption("I stay calm and focused", 3),
            MoodOption("I get overwhelmed", 1),
            MoodOption("I try to distract myself", 2)
        )),
        Question("Which of these is your favorite activity?", listOf(
            MoodOption("Reading a book", 1),
            MoodOption("Hiking or nature walks", 2),
            MoodOption("Spending time with friends", 3)
        )),
        Question("Do you enjoy trying new things?", listOf(
            MoodOption("Yes, I'm always up for something new", 3),
            MoodOption("Sometimes, but not too often", 2),
            MoodOption("Not really, I prefer sticking to what I know", 1)
        )),
        Question("How do you prefer to work?", listOf(
            MoodOption("In a team with lots of collaboration", 3),
            MoodOption("Individually, focusing on my tasks", 1),
            MoodOption("I can do both depending on the task", 2)
        )),
        Question("How do you deal with criticism?", listOf(
            MoodOption("I take it as an opportunity to improve", 3),
            MoodOption("I get defensive, but try to learn", 2),
            MoodOption("I take it personally and get upset", 1)
        )),
        Question("Do you consider yourself more creative or logical?", listOf(
            MoodOption("Creative, I love thinking outside the box", 3),
            MoodOption("Logical, I prefer structured thinking", 1),
            MoodOption("I think I balance both", 2)
        )),
        Question("How often do you make decisions based on your gut feeling?", listOf(
            MoodOption("I trust my gut instinct a lot", 3),
            MoodOption("Only when I don't have all the facts", 2),
            MoodOption("I prefer to analyze everything thoroughly", 1)
        )),
        Question("How important is routine to you?", listOf(
            MoodOption("I love routines, they help me feel secure", 1),
            MoodOption("I have a routine, but I'm flexible", 2),
            MoodOption("I dislike routines, I prefer variety", 3)
        )),
        Question("How do you usually spend your evenings?", listOf(
            MoodOption("Relaxing at home, maybe watching TV", 1),
            MoodOption("Going out with friends", 3),
            MoodOption("Engaging in a hobby or learning something new", 2)
        )),
        Question("Do you find it easy to make new friends?", listOf(
            MoodOption("Yes, I can talk to anyone", 3),
            MoodOption("It takes time, but I manage", 2),
            MoodOption("No, I prefer a small circle", 1)
        )),
        Question("How do you approach challenges?", listOf(
            MoodOption("I dive in headfirst, no hesitation", 3),
            MoodOption("I plan and strategize before acting", 2),
            MoodOption("I avoid challenges whenever I can", 1)
        )),
        Question("How much do you value independence?", listOf(
            MoodOption("It's essential to me", 3),
            MoodOption("I value it, but I'm okay with depending on others", 2),
            MoodOption("I prefer to rely on others for support", 1)
        )),
        Question("How would you describe your sense of humor?", listOf(
            MoodOption("Dry and sarcastic", 1),
            MoodOption("Funny and lighthearted", 3),
            MoodOption("I enjoy humor, but I'm not always joking", 2)
        )),
        Question("How do you feel about change?", listOf(
            MoodOption("I embrace it and look forward to new things", 3),
            MoodOption("I can adapt, but it takes time", 2),
            MoodOption("I struggle with change and prefer stability", 1)
        )),
        Question("How do you react when you don’t understand something?", listOf(
            MoodOption("I ask questions and try to figure it out", 3),
            MoodOption("I wait for someone to explain it to me", 2),
            MoodOption("I get frustrated and try to ignore it", 1)
        )),
        Question("Which of these best describes you?", listOf(
            MoodOption("Introverted and reserved", 1),
            MoodOption("Balanced, I enjoy both alone time and socializing", 2),
            MoodOption("Extroverted and outgoing", 3)
        )),
        Question("What is your relationship with risk?", listOf(
            MoodOption("I take risks when necessary", 3),
            MoodOption("I avoid risks when possible", 1),
            MoodOption("I take calculated risks", 2)
        )),
        Question("How would you describe your work style?", listOf(
            MoodOption("Fast-paced, I like to stay busy", 3),
            MoodOption("Steady, I like a balance of work and rest", 2),
            MoodOption("Slow and methodical, I prefer taking my time", 1)
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
        val (emoji, message) = when {
            totalScore in 20..24 -> "😐" to "You're a calm and reserved individual. You appreciate stability and peace."
            totalScore in 25..29 -> "😊" to "You have a balanced personality. You know when to work hard and when to relax."
            totalScore in 30..34 -> "😁" to "You're an optimistic person who loves to engage in social activities and new challenges."
            totalScore in 35..39 -> "🔥" to "You're full of energy and creativity. You embrace change and lead others with passion."
            totalScore in 40..44 -> "🎯" to "You're a focused achiever, always striving for success with a clear plan."
            totalScore in 45..49 -> "💡" to "You are highly intellectual and creative, constantly thinking of new ideas."
            totalScore in 50..54 -> "🌟" to "You're a natural leader, charismatic and motivated by personal growth and the success of others."
            totalScore in 55..59 -> "🌞" to "You're an extremely positive individual, always looking at the brighter side of life."
            totalScore in 60..64 -> "💪" to "You are resilient and determined, taking on challenges with confidence and strength."
            totalScore in 65..69 -> "🚀" to "You're adventurous and daring, always looking to expand your horizons."
            totalScore in 70..74 -> "✨" to "You're imaginative and inspiring, often bringing out the best in others with your creative vision."
            totalScore in 75..79 -> "🌈" to "You're a free spirit, full of joy and excitement. You live life to the fullest!"
            totalScore in 80..84 -> "🎉" to "You're a party person, always the center of attention, enjoying every moment of life."
            totalScore in 85..89 -> "👑" to "You're a leader in the truest sense, commanding respect and admiration from others."
            totalScore in 90..94 -> "💖" to "You're incredibly empathetic and care deeply about the people in your life."
            totalScore in 95..99 -> "🦸‍♂️" to "You're heroic and selfless, always willing to help others and put them first."
            totalScore in 100..104 -> "🔥✨" to "You're a dynamic force, blending ambition, creativity, and determination in everything you do."
            totalScore in 105..109 -> "⚡" to "You're an electrifying personality, always exciting and full of new ideas."
            totalScore in 110..114 -> "🎭" to "You have a deep, complex personality, full of layers that reveal themselves over time."
            totalScore in 115..120 -> "🌟🌟" to "You're an exceptional individual, balancing every part of your life with grace and wisdom."
            else -> "⚡" to "You're a powerhouse, unstoppable and full of energy, always aiming higher."
        }

        val resultMessage = "$emoji\n\n$message"

        val dialog = AlertDialog.Builder(this)
            .setTitle("Your Personality Result")
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

    private fun showWelcomeDialog() {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Welcome to Personality Calculator 😊")
            .setMessage("Answer a few quick questions to discover your personality. Ready?")
            .setCancelable(false)
            .setPositiveButton("Let's Go!") { _, _ ->
                showQuestion()
            }
            .setNegativeButton("Maybe Later") { _, _ ->
                finish() // Close the app if user taps 'Maybe Later'
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

        // Show welcome pop-up on launch
        showWelcomeDialog()
    }
}
