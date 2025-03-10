package com.example.quiztrivial

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class Menu : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    var user: FirebaseUser? = null
    lateinit var tancarSessio: Button
    lateinit var miPuntuaciotxt: TextView
    lateinit var puntuacio: TextView
    lateinit var uid: TextView
    lateinit var correo: TextView
    lateinit var nom: TextView
    lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // Vinculamos las vistas
        tancarSessio = findViewById(R.id.tancarSessio)
        miPuntuaciotxt = findViewById(R.id.miPuntuaciotxt)
        puntuacio = findViewById(R.id.puntuacio)
        uid = findViewById(R.id.uid)
        correo = findViewById(R.id.correo)
        nom = findViewById(R.id.nom)

        // Inicializamos FirebaseAuth y obtenemos el usuario actual
        auth = FirebaseAuth.getInstance()
        user = auth.currentUser

        // Configuramos el botón para cerrar sesión
        tancarSessio.setOnClickListener {
            tancalaSessio()
        }

        // Llamamos a consulta para cargar los datos del jugador
        consulta()
    }

    private fun tancalaSessio() {
        auth.signOut() // Cierra la sesión
        val intent = Intent(this, LoginRegister::class.java)
        startActivity(intent)
        finish()
    }

    // Método para consultar datos de Firebase
    private fun consulta() {
        val database: FirebaseDatabase =
            FirebaseDatabase.getInstance("https://quiztrivial-8b71c-default-rtdb.europe-west1.firebasedatabase.app/")
        val bdreference: DatabaseReference = database.getReference("DATA BASE JUGADORS")

        bdreference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.i("DEBUG", "Arrel value: ${snapshot.getValue()}")
                Log.i("DEBUG", "Arrel key: ${snapshot.key}")

                var trobat: Boolean = false
                for (ds in snapshot.children) {
                    Log.i("DEBUG", "DS key: ${ds.child("Uid").key}")
                    Log.i("DEBUG", "DS value: ${ds.child("Uid").getValue()}")
                    Log.i("DEBUG", "DS data: ${ds.child("Data").getValue()}")
                    Log.i("DEBUG", "DS mail: ${ds.child("Email").getValue()}")

                    // Comparamos el email almacenado en la base de datos con el email del usuario actual
                    if (ds.child("Email").getValue().toString() == user?.email) {
                        trobat = true
                        puntuacio.text = ds.child("Puntuacio").getValue().toString()
                        uid.text = ds.child("Uid").getValue().toString()
                        correo.text = ds.child("Email").getValue().toString()
                        nom.text = ds.child("Nom").getValue().toString()
                    }
                }

                if (!trobat) {
                    Log.e("ERROR", "ERROR NO TROBAT MAIL")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("ERROR", "ERROR DATABASE CANCEL")
            }
        })
    }

    // Verificamos si el usuario está logueado
    private fun usuariLogejat() {
        if (user != null) {
            Toast.makeText(this, "Jugador logejat", Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    // Llamamos a usuariLogejat en onStart
    override fun onStart() {
        usuariLogejat()
        super.onStart()
    }
}
