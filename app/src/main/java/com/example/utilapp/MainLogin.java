package com.example.utilapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainLogin extends AppCompatActivity {

    String[] mensagens = {"Preencha todos os campos!", "Usuário não cadastrado!"};

    private Button bt_login;
    private Button bt_cadastro;
    private EditText edt_mail, edt_senha;
    private ImageView ver_senha;
    private CheckBox check_login;
    private ProgressBar LoginProgressBar;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        IniciarComponentes();

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginmail = edt_mail.getText().toString();
                String loginsenha = edt_senha.getText().toString();

                if (loginmail.isEmpty() || loginsenha.isEmpty()){
                    Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                }else {
                    AutenticarUsuario(v, loginmail, loginsenha);

                }

            }
        });
        bt_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainLogin.this, MainCadastro.class);
                startActivity(intent);
            }
        });
    }

    private void AutenticarUsuario(View v, String loginmail, String loginsenha) {
        if(!TextUtils.isEmpty(loginmail) || !TextUtils.isEmpty(loginsenha)){

            LoginProgressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(loginmail, loginsenha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){
                        LoginProgressBar.setVisibility(View.VISIBLE);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                abrirTelaPrincipal();
                            }
                        }, 3000);

                    }else {
                        Snackbar snackbar = Snackbar.make(v, mensagens[1], Snackbar.LENGTH_SHORT);
                        snackbar.setBackgroundTint(Color.WHITE);
                        snackbar.setTextColor(Color.BLACK);
                        snackbar.show();
                        LoginProgressBar.setVisibility(View.INVISIBLE);

                    }

                }
            });

        }
    }

    private void abrirTelaPrincipal() {
        Intent intent = new Intent(MainLogin.this, MainActivityPrincipal.class);
        startActivity(intent);
        finish();
    }
    private void IniciarComponentes(){
        mAuth = FirebaseAuth.getInstance();
        edt_mail = findViewById(R.id.edit_mail);
        edt_senha = findViewById(R.id.edit_pass);
        bt_login = findViewById(R.id.id_button_login);
        bt_cadastro = findViewById(R.id.id_button_cad);
        ver_senha = findViewById(R.id.id_ver_senha_login);
        check_login = findViewById(R.id.id_checkbox);
        LoginProgressBar = findViewById(R.id.id_bar);

    }
}