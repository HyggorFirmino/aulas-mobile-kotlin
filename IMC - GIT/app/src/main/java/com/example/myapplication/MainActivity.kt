package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var editTextPeso: EditText
    private lateinit var editTextAltura: EditText
    private lateinit var buttonCalcular: Button
    private lateinit var textViewReultado: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTextPeso = findViewById(R.id.peso)
        editTextAltura = findViewById(R.id.altura)
        buttonCalcular = findViewById(R.id.calcular)
        textViewReultado = findViewById(R.id.resultado)
        buttonCalcular.setOnClickListener {
            val peso = editTextPeso.text.toString().toDouble()
            val altura = editTextAltura.text.toString().toDouble()
            val IMC = calcularIMC(peso, altura)
            val imcFormatted = String.format("%.2f", IMC)
            textViewReultado.text = "Seu IMC é: $imcFormatted"
        }
    }
    private fun calcularIMC(peso:Double, altura: Double): Double {
        return peso / (altura * altura)
    }
}