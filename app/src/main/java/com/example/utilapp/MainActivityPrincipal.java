package com.example.utilapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;

public class MainActivityPrincipal extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ImageView ic_sair;
    private ImageView ic_courses;
    private ImageView ic_home;
    private FirebaseFirestore db;
    TextView textUserDados;
    TextView textUserEndereco;
    TextView textUserEmail;
    TextView text_user;
    public void lerDados(){
        textUserDados = findViewById(R.id.text_user_dados);
        textUserEndereco = findViewById(R.id.text_email_dados);
        textUserEmail = findViewById(R.id.text_endereco_dados);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_principal);
        text_user = findViewById(R.id.text_user);
        ic_sair= findViewById(R.id.id_button_logout);
        ic_courses= findViewById(R.id.id_button_courses);
        ic_home = findViewById(R.id.id_button_home);
        mAuth = FirebaseAuth.getInstance();
        String uidDoUsuario = mAuth.getUid();
        lerDados();


        db = FirebaseFirestore.getInstance();

        DocumentReference documentRef = db.collection("Usuarios").document(uidDoUsuario);
        ListenerRegistration listenerRegistration = documentRef.addSnapshotListener(
                (documentSnapshot, e) -> {
                    if (e != null) {
                        return;
                    }

                    if (documentSnapshot != null && documentSnapshot.exists()) {
                        String nome = documentSnapshot.getString("nome");
                        textUserDados.setText(nome);
                        text_user.setText(nome);
                        String email = documentSnapshot.getString("email");
                        textUserEndereco.setText(email);
                        String endereco = documentSnapshot.getString("endereco");
                        textUserEmail.setText(endereco);

                    } else {
                    }
                }
        );


        ic_sair.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(MainActivityPrincipal.this, MainLogin.class);
                startActivity(intent);
                finish();
                }
        });
        ic_courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityPrincipal.this, MainCursos.class);
                startActivity(intent);
                finish();
            }
        });
        ic_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityPrincipal.this, MainActivityPrincipal.class);
                startActivity(intent);
                finish();
            }
        });

    }
}