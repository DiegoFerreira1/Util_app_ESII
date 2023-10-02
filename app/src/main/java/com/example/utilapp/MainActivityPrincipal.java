package com.example.utilapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivityPrincipal extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_principal);

        mAuth = FirebaseAuth.getInstance();

        btn_logout = findViewById(R.id.id_button_logout);

        btn_logout.setOnClickListener(new View.OnClickListener() {
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