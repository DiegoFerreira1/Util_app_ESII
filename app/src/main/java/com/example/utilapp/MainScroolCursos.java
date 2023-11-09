package com.example.utilapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.utilapp.tools.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainScroolCursos extends AppCompatActivity {

    private Button bt_voltar, bt_novoCurso;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_scrool_cursos);

        RecyclerView recyclerView = findViewById(R.id.id_rv_cursos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<String> data = new ArrayList<>();
        // Adicione dados de exemplo
        data.add("Item 1");
        data.add("Item 2");
        data.add("Item 3");

        MyAdapter adapter = new MyAdapter(this, data);
        recyclerView.setAdapter(adapter);

        //Botão Novo curso
        bt_novoCurso = findViewById(R.id.id_bt_inserirCurso);
        bt_novoCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainScroolCursos.this, MainInsertCurso.class);
                startActivity(intent);
                finish();

            }
        });

        //Botão voltar
        bt_voltar = findViewById(R.id.id_bt_voltar);
        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScroolCursos.this, MainCursos.class);
                startActivity(intent);
                finish();
             }
        });










    }







}