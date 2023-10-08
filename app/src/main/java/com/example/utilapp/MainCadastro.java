package com.example.utilapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.utilapp.tools.FieldValidator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainCadastro extends AppCompatActivity {

    private EditText edit_nome, edit_mail, edit_senha, edit_senha2, edit_endereco;
    private Button bt_concluir;

    private ImageView ver_senha_cad;

    String[] mensagens = {"O botão foi clicado", "Preencha todos os campos", "Cadastro realizado com Sucesso"};
    String usuarioId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cadastro);
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        IniciarConponentes();

        bt_concluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = edit_nome.getText().toString();
                String email = edit_mail.getText().toString();
                String senha = edit_senha.getText().toString();
                String senha2 = edit_senha2.getText().toString();
                String endereco = edit_endereco.getText().toString();


                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || senha2.isEmpty() || endereco.isEmpty()) {

                    Snackbar snackbar = Snackbar.make(v, mensagens[1], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                } else {
                    CadastrarUsuario(v);

                }

            }
        });

        ver_senha_cad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_senha.getTransformationMethod() instanceof PasswordTransformationMethod) {
                    edit_senha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    edit_senha2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    edit_senha.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    edit_senha2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

    }

    private void CadastrarUsuario(View v) {

        String nome = edit_nome.getText().toString();
        String email = edit_mail.getText().toString();
        String senha = edit_senha.getText().toString();
        String senha2 = edit_senha2.getText().toString();
        String endereco = edit_endereco.getText().toString();

        if (senha.equals(senha2)) {

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {

                        SalvarDadosUser(nome, email, senha, endereco);

                        Snackbar snackbar = Snackbar.make(v, mensagens[2], Snackbar.LENGTH_SHORT);
                        snackbar.setBackgroundTint(Color.WHITE);
                        snackbar.setTextColor(Color.BLACK);
                        snackbar.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                snackbar.dismiss();
                                Intent intent = new Intent(MainCadastro.this, MainLogin.class);
                                startActivity(intent);
                                finish();
                            }
                        }, 1500); // Atraso de 1500 milissegundos (2 segundos)
                    } else {

                        if (!FieldValidator.validate(senha, FieldValidator.TYPE_PASSWORD)) {
                            String erro = "A senha deve conter pelo menos um caractere minúsculo, um maiúsculo, um número e um caractere especial.";
                            Snackbar snackbar = Snackbar.make(v, erro, Snackbar.LENGTH_SHORT);
                            snackbar.setBackgroundTint(Color.WHITE);
                            snackbar.setTextColor(Color.BLACK);
                            snackbar.show();
                        } else {

                            String erro;
                            try {

                                throw task.getException();


                            }catch (FirebaseAuthWeakPasswordException e) {
                                erro = "A senha deve conter no mínimo 6 caracteres";

                            }catch (FirebaseAuthUserCollisionException e) {
                                erro = "Email ja cadastrado.";

                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                erro = "Email inválido.";

                            } catch (Exception e) {
                                erro = "Erro ao cadastrar usuário";

                            }
                            Snackbar snackbar = Snackbar.make(v, erro, Snackbar.LENGTH_SHORT);
                            snackbar.setBackgroundTint(Color.WHITE);
                            snackbar.setTextColor(Color.BLACK);
                            snackbar.show();
                        }
                    }
                }
            }); // Esta é a chave de fechamento correta para o método addOnCompleteListener


        } else {
            String erro = "As senhas não conferem!";
            Snackbar snackbar = Snackbar.make(v, erro, Snackbar.LENGTH_SHORT);
            snackbar.setBackgroundTint(Color.WHITE);
            snackbar.setTextColor(Color.BLACK);
            snackbar.show();
        }

    } // Esta é a chave de fechamento correta para o método CadastrarUsuarios


    private void IniciarConponentes() {
        edit_nome = findViewById(R.id.edit_nome_cad);
        edit_mail = findViewById(R.id.edit_mail_cad);
        edit_senha = findViewById(R.id.edit_pass_cad);
        edit_senha2 = findViewById(R.id.edit_pass_cad2);
        edit_endereco = findViewById(R.id.edit_address_cad);
        bt_concluir = findViewById(R.id.id_button_cad);
        ver_senha_cad = findViewById(R.id.id_ver_senha_cad);
    }
    private void SalvarDadosUser(String nome_usuario, String email, String senha, String endereco) {
        try {
            String jdbcUrl = "jdbc:postgresql://motty.db.elephantsql.com:5432/dskxvjps";
            String username = "dskxvjps";
            String password = "kw6tTe1l0LPWj0L0p71qOtk14selY4uF";

            // Estabelecer a conexão com o banco de dados
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Preparar uma declaração SQL para a inserção
            String sql = "INSERT INTO usuarios (nome_usuario, email, senha, endereco) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nome_usuario);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, senha);
            preparedStatement.setString(4, endereco);

            // Executar a inserção
            int rowsAffected = preparedStatement.executeUpdate();

            // Verificar se a inserção foi bem-sucedida
            if (rowsAffected > 0) {
                Toast.makeText(MainCadastro.this, "Dados inseridos com sucesso.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainCadastro.this, "Falha ao inserir dados.", Toast.LENGTH_SHORT).show();
            }

            // Fechar a conexão com o banco de dados
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }


}