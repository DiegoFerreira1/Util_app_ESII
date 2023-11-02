package com.example.utilapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class MainReels extends AppCompatActivity {
    private ImageView  ic_home, ic_perfil, ic_courses, ic_sair;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_reels);
        iniciarComponentes();


        //Navegação
        ic_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainReels.this, MainActivityPrincipal.class);
                startActivity(intent);
                finish();
            }
        });
        ic_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainReels.this, MainProfile.class);
                startActivity(intent);
                finish();
            }
        });
        ic_courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainReels.this, MainCursos.class);
                startActivity(intent);
                finish();
            }
        });
        ic_sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(MainReels.this, MainLogin.class);
                startActivity(intent);
                finish();
            }
        });


    }

    //Metodos
    public void iniciarComponentes(){
        ic_home = findViewById(R.id.id_button_home_reels);
        ic_perfil = findViewById(R.id.id_button_profile_reels);
        ic_courses = findViewById(R.id.id_button_courses_reels);
        ic_sair= findViewById(R.id.id_button_logout_reels);
        mAuth = FirebaseAuth.getInstance();
    }
}