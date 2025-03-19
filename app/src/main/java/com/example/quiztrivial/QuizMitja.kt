package com.example.quiztrivial

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuizMitja : AppCompatActivity() {

    companion object {
        // Declaración de las preguntas, opciones y respuestas correctas
        val questions = arrayOf(
            "Quin és el component principal del sol que el fa brillar?",
            "Què és la teoria de la relativitat general d'Albert Einstein?",
            "En quin any es va signar la Declaració d'Independència dels Estats Units?",
            "Què és el principi d'incertesa de Heisenberg?"
        )

        val choices = arrayOf(
            arrayOf("Hel·li", "Oxigen", "Hidrogen","Carboni"),
            arrayOf("La teoria que explica les lleis de la física en espais buits", "La teoria que afirma que la gravetat és una força invisible", "La teoria que afirma que la gravetat és el resultat de la curvatura de l'espai-temps","La teoria que determina que l'espai-temps és finit"),
            arrayOf("1774", "1789", "1776","1790"),
            arrayOf("És un principi que explica com les partícules subatòmiques poden estar en dos llocs alhora", "És un principi que estableix que no es poden mesurar amb precisió la posició i la velocitat d'una partícula simultàniament", "És un principi que defineix la curvatura de l'espai-temps","És un principi que afirma que les partícules sempre tenen un moviment definit")
        )

        val correctAnswers = arrayOf(
            "Hidrogen",
            "La teoria que afirma que la gravetat és el resultat de la curvatura de l'espai-temps",
            "1776",
            "És un principi que estableix que no es poden mesurar amb precisió la posició i la velocitat d'una partícula simultàniament"
        )
    }

    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_facil)

        val questionTextView: TextView = findViewById(R.id.question)
        val totalQuestionsTextView: TextView = findViewById(R.id.total_question)
        val ansA: Button = findViewById(R.id.ans_A)
        val ansB: Button = findViewById(R.id.ans_B)
        val ansC: Button = findViewById(R.id.ans_C)
        val ansD: Button = findViewById(R.id.ans_D)
        val submitButton: Button = findViewById(R.id.submit_btn)

        // Mostrar el total de preguntas
        totalQuestionsTextView.text = "Total questions: ${questions.size}"

        // Función para actualizar la pregunta y las opciones
        fun updateQuestion() {
            if (currentQuestionIndex < questions.size) {
                questionTextView.text = questions[currentQuestionIndex]
                ansA.text = choices[currentQuestionIndex][0]
                ansB.text = choices[currentQuestionIndex][1]
                ansC.text = choices[currentQuestionIndex][2]
                ansD.text = choices[currentQuestionIndex].getOrElse(3) { "Opció D" }  // Para evitar posibles nulls
            }
        }

        // Función para verificar si la respuesta seleccionada es correcta
        fun checkAnswer(selectedAnswer: String) {
            val correctAnswer = correctAnswers[currentQuestionIndex]
            if (selectedAnswer == correctAnswer) {
                score++
            }
        }

        // Configurar los botones de respuesta
        ansA.setOnClickListener { checkAnswer(ansA.text.toString()) }
        ansB.setOnClickListener { checkAnswer(ansB.text.toString()) }
        ansC.setOnClickListener { checkAnswer(ansC.text.toString()) }
        ansD.setOnClickListener { checkAnswer(ansD.text.toString()) }

        // Acción para el botón "Submit"
        submitButton.setOnClickListener {
            currentQuestionIndex++
            if (currentQuestionIndex < questions.size) {
                updateQuestion()  // Mostrar la siguiente pregunta
            } else {
                // Si no hay más preguntas, mostrar el resultado final
                questionTextView.text = "Quiz Finished! Your score is $score out of ${questions.size}"
                submitButton.visibility = View.INVISIBLE  // Ocultar el botón de submit
            }
        }

        // Inicializa la primera pregunta
        updateQuestion()
    }
}