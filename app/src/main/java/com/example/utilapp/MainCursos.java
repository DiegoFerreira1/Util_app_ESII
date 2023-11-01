package com.example.utilapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class MainCursos extends AppCompatActivity {
    private ImageView ic_sair;
    private ImageView ic_home;
    private ImageView ic_courses;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cursos);
        mAuth = FirebaseAuth.getInstance();

        ic_sair = findViewById(R.id.id_button_logout);
        ic_courses = findViewById(R.id.id_button_courses);
        ic_home = findViewById(R.id.id_button_home);

        ic_sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(MainCursos.this, MainLogin.class);
                startActivity(intent);
                finish();
            }
        });

        ic_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirecione para a atividade apropriada (por exemplo, MainActivityPrincipal)
                Intent intent = new Intent(MainCursos.this, MainActivityPrincipal.class);
                startActivity(intent);
                finish();
            }
        });

        ic_courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainCursos.this, MainCursos.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
