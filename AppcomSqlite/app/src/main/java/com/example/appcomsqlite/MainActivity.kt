package com.example.appcomsqlite

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var editTextNome: EditText
    private lateinit var editTextIdade: EditText
    private lateinit var editTextPeso: EditText
    private lateinit var editTextAltura: EditText
    private lateinit var buttonCalcularIMC: Button
    private lateinit var buttonSalvarDados: Button
    private lateinit var textViewResultadoIMC: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databaseHelper = DatabaseHelper(this)
        setContentView(R.layout.activity_main)

        this.editTextNome = findViewById(R.id.editTextNome)
        this.editTextIdade = findViewById(R.id.editTextIdade)
        this.editTextPeso = findViewById(R.id.editTextPeso)
        this.editTextAltura = findViewById(R.id.editTextAltura)
        buttonCalcularIMC = findViewById(R.id.buttonCalcularIMC)
        buttonSalvarDados = findViewById(R.id.btnSalvarDados)
        textViewResultadoIMC = findViewById(R.id.textViewResultadoIMC)

        buttonCalcularIMC.setOnClickListener {
            val pesoText = editTextPeso.text.toString()
            val alturaText = editTextAltura.text.toString()
            val peso = pesoText.toDouble()
            val altura = alturaText.toDouble()
            textViewResultadoIMC.text = calcIMC(peso, altura)
        }
        buttonSalvarDados.setOnClickListener {
            val nome = editTextNome.text.toString()
            val idade = editTextIdade.text.toString().toInt()
            val peso = editTextPeso.text.toString().toDouble()
            val altura = editTextAltura.text.toString().toDouble()

            if (nome.isNotEmpty() && idade != null && altura != null && peso != null) { // instancia o objeto
                val pessoa = Pessoa(nome, idade, altura, peso)
                /*chama método da classe databasehelper para persistir os dados no banco de dados */
                databaseHelper.insertPessoa(pessoa.nome, pessoa.idade, pessoa.altura, pessoa.peso)

                Toast.makeText(this, "Sucesso!", Toast.LENGTH_SHORT).show()
                Log.i("appBD", "${databaseHelper.getAllPessoas()}"
                ) // limpa os campos para novo cadastro
                editTextNome.text.clear()
                editTextIdade.text.clear()
                editTextAltura.text.clear()
                editTextPeso.text.clear()
            } else { //se validação falhou mostra mensagem
                Toast.makeText(this, "Há erro no preenchimento de um ou mais dados", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun calcIMC(Peso: Double, Altura: Double): String {
        val imc = Peso / (Altura * Altura)
        val imcFormatted = String.format("%.2f", imc)
        val resultado = "Seu IMC é: $imcFormatted"
        return resultado
    }
}