package com.example.quiztrivial

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuizFacil : AppCompatActivity() {

    companion object {
        // Declaración de las preguntas, opciones y respuestas correctas
        val questions = arrayOf(
            "Quin conflicte va succeir entre 1939 i 1945?",
            "En quin pais va neixer Rasputin?",
            "Que es mes gran, un atom o una molecula?",
            "Qui va ser el primer cosmonauta?"
        )

        val choices = arrayOf(
            arrayOf("Segona guerra mundial", "Primera guerra mundial", "Guerra de vietnam"),
            arrayOf("Ucraina", "Russia", "Lituania"),
            arrayOf("Atom", "Molecula", "Els dos igual"),
            arrayOf("Yuri Gagarin", "Carrero Blanco", "Tom Hank")
        )

        val correctAnswers = arrayOf(
            "Segona guerra mundial",
            "Russia",
            "Molecula",
            "Yuri Gagarin"
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
