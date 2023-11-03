package com.example.utilapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivityPrincipal extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ImageView ic_profile, ic_courses, ic_reels, ic_sair;

    String usuarioID;
    private TextView textUserNome,textUserEndereco, textUserEmail, text_user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_principal);

        iniciarComponentes();



        // Navegação
        ic_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityPrincipal.this, MainProfile.class);
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

        ic_reels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityPrincipal.this, MainReels.class);
                startActivity(intent);
                finish();
            }
        });
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

    //Metodos
    public void iniciarComponentes(){
        textUserNome = findViewById(R.id.text_user_dados);
        text_user = findViewById(R.id.text_user);
        textUserEmail = findViewById(R.id.text_email_dados);
        textUserEndereco = findViewById(R.id.text_endereco_dados);

        ic_profile = findViewById(R.id.id_button_profile);
        ic_courses= findViewById(R.id.id_button_courses);
        ic_reels = findViewById(R.id.id_button_reels);
        ic_sair= findViewById(R.id.id_button_logout);

        mAuth = FirebaseAuth.getInstance();

    }

    //Start para recuperar os dados do usuario logado
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        db.collection("Usuarios")
                .whereEqualTo("email", email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String documentoID = document.getId();
                                String nomeUser = (String) document.getData().get("nome");
                                String enderecoUser = (String) document.getData().get("endereco");

                                textUserNome.setText(nomeUser);
                                text_user.setText(nomeUser);
                                textUserEndereco.setText(enderecoUser);
                                textUserEmail.setText(email);

                            }
                        } else {
                            Log.d("Firestore", "Documento não encontrado");
                        }
                    }
                });
    }



}