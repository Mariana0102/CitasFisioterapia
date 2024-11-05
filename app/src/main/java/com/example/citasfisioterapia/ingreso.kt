package com.example.citasfisioterapia
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Ingreso : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ingreso)  // Asegúrate de que este sea el nombre correcto de tu archivo XML

        // Inicializa Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Referencia a los componentes de la interfaz
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
        registerButton = findViewById(R.id.registerButton)

        // Configura el listener para el botón de ingreso
        loginButton.setOnClickListener {
            loginUser()
        }

        // Configura el listener para el botón de registro
        registerButton.setOnClickListener {
            // Navega a la actividad de registro
            startActivity(Intent(this, Registro::class.java))
        }
    }

    private fun loginUser() {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        // Validación de campos
        if (email.isEmpty()) {
            showToast("Por favor, ingresa tu correo.")
            return
        }

        if (password.isEmpty()) {
            showToast("Por favor, ingresa tu contraseña.")
            return
        }

        // Iniciar sesión con Firebase Authentication
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Inicio de sesión exitoso
                    showToast("Inicio de sesión exitoso.")
                    // Aquí puedes redirigir a otra actividad (por ejemplo, la pantalla principal)
                    // startActivity(Intent(this, MainActivity::class.java))
                    // finish() // Cierra la actividad actual
                } else {
                    // Si el inicio de sesión falla, muestra el mensaje de error
                    showToast("Error en el inicio de sesión: ${task.exception?.message}")
                }
            }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

