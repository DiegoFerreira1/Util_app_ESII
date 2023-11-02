package com.example.utilapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainProfile extends AppCompatActivity {

    private ImageView  ic_home, ic_courses, ic_reels, ic_sair;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profile);

        iniciarComponentes();

        //Navegação
        ic_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainProfile.this, MainActivityPrincipal.class);
                startActivity(intent);
                finish();
            }
        });
        ic_courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainProfile.this, MainCursos.class);
                startActivity(intent);
                finish();
            }
        });
        ic_reels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainProfile.this, MainReels.class);
                startActivity(intent);
                finish();
            }
        });
        ic_sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(MainProfile.this, MainLogin.class);
                startActivity(intent);
                finish();
            }
        });

    }

    //Metodos
    private void iniciarComponentes(){

        ic_home = findViewById(R.id.id_button_home_perfil);
        ic_courses = findViewById(R.id.id_button_courses_perfil);
        ic_reels = findViewById(R.id.id_button_reels_perfil);
        ic_sair= findViewById(R.id.id_button_logout_perfil);
        mAuth = FirebaseAuth.getInstance();
    }


}