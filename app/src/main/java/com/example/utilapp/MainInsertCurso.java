package com.example.utilapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.utilapp.tools.Category;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainInsertCurso extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
        private EditText nomeCursoEditText;
        private EditText descricaoCursoEditText;
        private Button bt_concluir, bt_voltar;

        private List<Category> listaDeCategorias;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_insert_curso);

        iniciarComponentes();

        Spinner spinner = findViewById(R.id.id_spinnerCategorias);


        // Realiza a leitura das categorias do Firestore
        buscarCategoria(spinner);

        spinner.setOnItemSelectedListener(this);

        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainInsertCurso.this, MainScroolCursos.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void buscarCategoria(Spinner spinner) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Categoria")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<String> categorias = new ArrayList<>(); // Lista para armazenar os nomes das categorias

                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        String categoriaId = documentSnapshot.getId();
                        String nomeCategoria = documentSnapshot.getString("nome");

                        Log.d("db_cat", categoriaId + nomeCategoria);

                        categorias.add(nomeCategoria); // Adiciona o nome da categoria na lista
                    }

                    // Aqui você pode usar 'categorias' para preencher o Spinner ou realizar outras ações
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categorias);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);
                })
                .addOnFailureListener(e -> {
                    Log.e("db", "Erro ao buscar categorias", e); // Log mais detalhado do erro
                    Toast.makeText(this, "Erro ao buscar categorias", Toast.LENGTH_SHORT).show(); // Mensagem para o usuário
                });
    }


    @SuppressLint("WrongViewCast")
    private void iniciarComponentes(){
        nomeCursoEditText = findViewById(R.id.edit_nome_curso);
        descricaoCursoEditText = findViewById(R.id.edit_descricao);
        bt_concluir = findViewById(R.id.id_bt_inserirCurso);
        bt_voltar = findViewById(R.id.id_bt_voltar);


    }


    // Método para limpar os campos após salvar o curso
    private void limparCampos() {
        nomeCursoEditText.setText("");
        descricaoCursoEditText.setText("");

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
        Log.d("teste", text);
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}