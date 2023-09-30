package com.utec.tutorialfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var editTextNombre: EditText
    private lateinit var editTextApellido: EditText
    private lateinit var editTextCorreo: EditText
    private lateinit var editTextTelefono: EditText
    private lateinit var buttonRegistrar: Button
    private val database = FirebaseDatabase.getInstance()
    private val databaseReference = database.reference.child("Contactos")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextNombre = findViewById(R.id.editTextNombre)
        editTextApellido = findViewById(R.id.editTextApellido)
        editTextCorreo = findViewById(R.id.editTextCorreo)
        editTextTelefono = findViewById(R.id.editTextTelefono)
        buttonRegistrar = findViewById(R.id.buttonRegistrar)
        buttonRegistrar.setOnClickListener {
            registrar()
        }
    }

    private fun registrar(){
        val nombre = editTextNombre.text.toString()
        val apellido = editTextApellido.text.toString()
        val correo = editTextCorreo.text.toString()
        val telefono = editTextTelefono.text.toString()

        val contacto = Contacto(nombre, apellido, correo, telefono)
        val contactoId = databaseReference.push().key
        contactoId?.let {
            databaseReference.child(it).setValue(contacto)
            // Limpiar los campos después de registrar
            editTextNombre.text.clear()
            editTextApellido.text.clear()
            editTextCorreo.text.clear()
            editTextTelefono.text.clear()
        }
    }
}

data class Contacto(var name:String, var lastName:String, var mail:String, var phone:String)