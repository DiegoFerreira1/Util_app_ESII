package com.example.utilapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivityPrincipal extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ImageView ic_sair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_principal);

        mAuth = FirebaseAuth.getInstance();

        ic_sair= findViewById(R.id.id_button_logout);

        ic_sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(MainActivityPrincipal.this, MainLogin.class);
                startActivity(intent);
                finish();
            }
        });


    }
}