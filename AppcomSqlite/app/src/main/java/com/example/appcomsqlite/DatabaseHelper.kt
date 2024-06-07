package com.example.appcomsqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "app.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "pessoa"
        private const val COLUMN_NAME = "nome"
        private const val COLUMN_AGE = "idade"
        private const val COLUMN_HEIGHT = "altura"
        private const val COLUMN_WEIGHT = "peso"
    }
    override fun onCreate(db: SQLiteDatabase) {
        val createtablequeryPessoa = "CREATE TABLE IF NOT EXISTS $TABLE_NAME ( $COLUMN_NAME VARCHAR, $COLUMN_AGE INT(3), $COLUMN_HEIGHT REAL, $COLUMN_WEIGHT REAL)"
        db.execSQL(createtablequeryPessoa)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Implementar caso haja atualização da estrutura do banco de dados,
    }
    fun insertPessoa(nome: String, idade: Int, altura: Double, peso: Double) {
        val db = writableDatabase
        val insertqueryPessoa = "INSERT INTO $TABLE_NAME($COLUMN_NAME,$COLUMN_AGE,$COLUMN_HEIGHT,$COLUMN_WEIGHT) VALUES ('$nome',$idade, $altura, $peso)"
        db.execSQL(insertqueryPessoa)
        db.close()
    }
    fun getAllPessoas(): ArrayList<Pessoa> {
        val pessoasList = ArrayList<Pessoa>()
        val db = readableDatabase
        val selectqueryPessoa = "SELECT $COLUMN_NAME, $COLUMN_AGE, $COLUMN_HEIGHT, $COLUMN_WEIGHT FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectqueryPessoa, null)
        if (cursor.moveToFirst()) {
            do {
                val nome = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
                val idade = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AGE))
                val altura = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_HEIGHT))
                val peso = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_WEIGHT))
                val pessoa = Pessoa(nome, idade, altura, peso)
                pessoasList.add(pessoa)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return pessoasList
    }
}
