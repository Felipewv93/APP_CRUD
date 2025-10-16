package com.example.crudtutorial


import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class BancoHelper(context: android.content.Context):
    SQLiteOpenHelper(context, "crudApp", null, 3) {


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS Alunos (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome TEXT, " +
                    "ra INTEGER, " +
                    "nota1 FLOAT, " +
                    "nota2 FLOAT, " +
                    "media FLOAT)"
        )
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Alunos")
        onCreate(db)
    }
}


class MainActivity : AppCompatActivity() {


    private lateinit var bancoHelper: BancoHelper
    private lateinit var banco: SQLiteDatabase


    private lateinit var editNome: EditText
    private lateinit var editRA: EditText
    private lateinit var editNota1: EditText
    private lateinit var editNota2: EditText
    private lateinit var btnInserir: Button
    private lateinit var btnAtualizar: Button
    private lateinit var btnDeletar: Button
    private lateinit var btnListar: Button
    private lateinit var btnLimpar: Button
    private lateinit var listView: ListView


    private var idSelecionado: Int? = null
    private lateinit var adapter: ArrayAdapter<String>
    private val listaItens = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bancoHelper = BancoHelper(this)
        banco = bancoHelper.writableDatabase


        editNome = findViewById(R.id.editNome)
        editRA = findViewById(R.id.editRA)
        editNota1 = findViewById(R.id.editNota1)
        editNota2 = findViewById(R.id.editNota2)
        btnInserir = findViewById(R.id.btnInserir)
        btnAtualizar = findViewById(R.id.btnAtualizar)
        btnDeletar = findViewById(R.id.btnDeletar)
        btnListar = findViewById(R.id.btnListar)
        listView = findViewById(R.id.listView)
        btnLimpar = findViewById(R.id.btnLimpar)


        adapter = ArrayAdapter(this, R.layout.list_item, R.id.textItem, listaItens)
        listView.adapter = adapter


        btnInserir.setOnClickListener { inserir() }
        btnAtualizar.setOnClickListener { atualizar() }
        btnDeletar.setOnClickListener { deletar() }
        btnListar.setOnClickListener { listar() }
        btnLimpar.setOnClickListener { limparLista()}


        listView.setOnItemClickListener { _, _, position, _ ->
            val cursor = banco.rawQuery("SELECT * FROM Alunos", null)
            if (cursor.moveToPosition(position)) {
                idSelecionado = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                editNome.setText(cursor.getString(cursor.getColumnIndexOrThrow("nome")))
                editRA.setText(cursor.getInt(cursor.getColumnIndexOrThrow("ra")).toString())
                editNota1.setText(cursor.getFloat(cursor.getColumnIndexOrThrow("nota1")).toString())
                editNota2.setText(cursor.getFloat(cursor.getColumnIndexOrThrow("nota2")).toString())
            }
            cursor.close()
            Toast.makeText(this, "Selecionado ID $idSelecionado", Toast.LENGTH_SHORT).show()
        }
    }


    private fun inserir() {
        val nome = editNome.text.toString()
        val ra = editRA.text.toString().toIntOrNull()
        val nota1 = editNota1.text.toString().toFloatOrNull()
        val nota2 = editNota2.text.toString().toFloatOrNull()

        if (nome.isNotEmpty() && ra != null && nota1 != null && nota2 != null) {
            val media = (nota1 + nota2) / 2

            val valores = ContentValues()
            valores.put("nome", nome)
            valores.put("ra", ra)
            valores.put("nota1", nota1)
            valores.put("nota2", nota2)
            valores.put("media", media)

            banco.insert("Alunos", null, valores)
            Toast.makeText(this, "Aluno cadastrado com sucesso!", Toast.LENGTH_SHORT).show()

            limparCampos()
            listar()

        } else {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun listar() {
        listaItens.clear()
        val cursor: Cursor = banco.rawQuery("SELECT * FROM Alunos", null)


        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                val nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"))
                val ra = cursor.getInt(cursor.getColumnIndexOrThrow("ra"))
                val nota1 = cursor.getFloat(cursor.getColumnIndexOrThrow("nota1"))
                val nota2 = cursor.getFloat(cursor.getColumnIndexOrThrow("nota2"))
                val media = cursor.getFloat(cursor.getColumnIndexOrThrow("media"))

                listaItens.add("$id - $nome - RA: $ra\nAP1: $nota1 | AP2: $nota2 | Média: %.2f".format(media))
            } while (cursor.moveToNext())

        } else {
            Toast.makeText(this, "Nenhum aluno encontrado", Toast.LENGTH_SHORT).show()
        }

        cursor.close()

        adapter.notifyDataSetChanged()
    }


    private fun atualizar() {
        if (idSelecionado != null) {
            val nome = editNome.text.toString()
            val ra = editRA.text.toString().toIntOrNull()
            val nota1 = editNota1.text.toString().toFloatOrNull()
            val nota2 = editNota2.text.toString().toFloatOrNull()

            if (nome.isNotEmpty() && ra != null && nota1 != null && nota2 != null) {
                val media = (nota1 + nota2) / 2

                val valores = ContentValues()
                valores.put("nome", nome)
                valores.put("ra", ra)
                valores.put("nota1", nota1)
                valores.put("nota2", nota2)
                valores.put("media", media)

                banco.update("Alunos", valores, "id=?", arrayOf(idSelecionado.toString()))
                Toast.makeText(this, "Aluno atualizado com sucesso!", Toast.LENGTH_SHORT).show()

                limparCampos()
                listar()
            } else {
                Toast.makeText(this, "Selecione um aluno para atualizar", Toast.LENGTH_SHORT).show()
            }
        }
    }
        private fun deletar() {
            if (idSelecionado != null) {
                banco.delete("Alunos", "id=?", arrayOf(idSelecionado.toString()))
                Toast.makeText(this, "Aluno deletado com sucesso!", Toast.LENGTH_SHORT).show()

                limparCampos()
                listar()
            } else {
                Toast.makeText(this, "Selecione um item para deletar!", Toast.LENGTH_SHORT).show()
            }
        }

    private fun limparLista() {
        if (adapter.count > 0) {
            adapter.clear()
            adapter.notifyDataSetChanged()
            limparCampos()
        }
        limparCampos()
    }


    private fun limparCampos() {
            editNome.text.clear()
            editRA.text.clear()
            editNota1.text.clear()
            editNota2.text.clear()
            idSelecionado = null
        }
    }