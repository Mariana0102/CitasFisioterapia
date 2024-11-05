package com.example.citasfisioterapia

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Registro : AppCompatActivity() {

    private lateinit var editTextNombre: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextConfirmPassword: EditText
    private lateinit var buttonRegister: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro)

        // Inicializa Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Referencia a los componentes de la interfaz
        editTextNombre = findViewById(R.id.editTextNombre)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword)
        buttonRegister = findViewById(R.id.buttonRegister)

        // Configura el listener para el botón de registro
        buttonRegister.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val nombre = editTextNombre.text.toString().trim()
        val email = editTextEmail.text.toString().trim()
        val password = editTextPassword.text.toString().trim()
        val confirmPassword = editTextConfirmPassword.text.toString().trim()

        // Validación de campos
        if (nombre.isEmpty()) {
            showToast("Por favor, ingresa tu nombre completo.")
            return
        }

        if (email.isEmpty()) {
            showToast("Por favor, ingresa tu correo.")
            return
        }

        if (password.isEmpty()) {
            showToast("Por favor, ingresa una contraseña.")
            return
        }

        if (confirmPassword.isEmpty()) {
            showToast("Por favor, confirma tu contraseña.")
            return
        }

        if (password != confirmPassword) {
            showToast("Las contraseñas no coinciden.")
            return
        }

        // Registro del usuario en Firebase
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Registro exitoso
                    showToast("Registro exitoso.")
                    // Aquí puedes redirigir a otra actividad (por ejemplo, la pantalla principal)
                    // startActivity(Intent(this, MainActivity::class.java))
                    // finish() // Cierra la actividad actual
                } else {
                    // Si el registro falla, muestra el mensaje de error
                    showToast("Error en el registro: ${task.exception?.message}")
                }
            }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
