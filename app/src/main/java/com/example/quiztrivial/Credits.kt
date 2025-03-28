package com.example.quiztrivial

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import java.util.Timer
import java.util.TimerTask

class Credits : AppCompatActivity() {
    private var timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_credits)

        // Inicia el temporitzador
        timer.scheduleAtFixedRate(TimeTask(), 0L, 3000L)



        val buttonEnrere: Button = findViewById(R.id.buttonEnrere)


        buttonEnrere.setOnClickListener {

            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
            finish()
        }



    }







    // Classe interna TimeTask per canviar els fragments
    private inner class TimeTask : TimerTask() {
        private var numeroFragment: Int = 1

        override fun run() {
            numeroFragment++
            if (numeroFragment > 2) numeroFragment = 1

            runOnUiThread {  // Hem d'executar operacions en el fil principal
                if (numeroFragment == 1) {
                    // Canviar al fragment_primer
                    supportFragmentManager.commit {
                        replace<fragment_primer>(R.id.frameContainer)
                        setReorderingAllowed(true)
                        addToBackStack("replacement")
                    }
                } else {
                    // Canviar al fragment_segon
                    supportFragmentManager.commit {
                        replace<fragment_segon>(R.id.frameContainer)
                        setReorderingAllowed(true)
                        addToBackStack("replacement")
                    }
                }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }
}
