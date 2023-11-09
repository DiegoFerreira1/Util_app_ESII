package com.example.utilapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainCursos extends AppCompatActivity {
    private ImageView ic_home,ic_perfil, ic_reels, ic_sair, ic_search;
    private TextView text_listarCursos;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cursos);

        iniciarComponentes();

        // Navegação
        ic_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirecione para a atividade apropriada (por exemplo, MainActivityPrincipal)
                Intent intent = new Intent(MainCursos.this, MainActivityPrincipal.class);
                startActivity(intent);
                finish();
            }
        });
        ic_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirecione para a atividade apropriada (por exemplo, MainActivityPrincipal)
                Intent intent = new Intent(MainCursos.this, MainProfile.class);
                startActivity(intent);
                finish();
            }
        });
        ic_reels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(MainCursos.this, MainReels.class);
                startActivity(intent);
                finish();
            }
        });

        ic_sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(MainCursos.this, MainLogin.class);
                startActivity(intent);
                finish();
            }
        });
        text_listarCursos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(MainCursos.this, MainScroolCursos.class);
                startActivity(intent);
                finish();
            }
        });


    }

    public void iniciarComponentes(){
        ic_home = findViewById(R.id.id_button_home_course);
        ic_perfil = findViewById(R.id.id_button_profile_course);
        ic_reels = findViewById(R.id.id_button_reels_course);
        ic_sair= findViewById(R.id.id_button_logout_course);
        ic_search = findViewById(R.id.ic_button_search_course);
        text_listarCursos = findViewById(R.id.text_listar_cursos);

        mAuth = FirebaseAuth.getInstance();
    }
}
