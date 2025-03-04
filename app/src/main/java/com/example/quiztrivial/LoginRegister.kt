package com.example.quiztrivial

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginRegister : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)
        var BTMLOGIN = findViewById<Button>(R.id.BTMLOGIN);
        var BTMREGISTRO = findViewById<Button>(R.id.BTMREGISTRO);
        BTMLOGIN.setOnClickListener(){
            Toast.makeText(this, "click botó login",Toast.LENGTH_LONG).show();
        }
        BTMREGISTRO.setOnClickListener(){
            Toast.makeText(this, "click botó Registre",Toast.LENGTH_LONG).show();
            val intent = Intent(this, MainActivity2::class.java) // Reemplaza con la actividad de destino
            startActivity(intent)
        }
    }
}
