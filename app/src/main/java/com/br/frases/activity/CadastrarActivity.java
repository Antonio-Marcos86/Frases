package com.br.frases.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.br.frases.R;
import com.br.frases.helpers.ConfiguracaoFirebase;
import com.br.frases.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastrarActivity extends AppCompatActivity {
    private EditText campoEmail,campoSenha;
    private FirebaseAuth autenticacao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);


        campoEmail = findViewById(R.id.editEmailCadastro);
        campoSenha = findViewById(R.id.editSenhaCadastro);

    }
    public void salvarUsuario(Usuario usuario){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(usuario.getEmail(),usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    exibirMensagem("Cadastro feito com sucesso!");
                    finish();
                    startActivity(new Intent(CadastrarActivity.this,MainActivity.class));
                }else{
                    String erroExcecao = "";

                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        erroExcecao = "Digite uma senha mais forte!";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        erroExcecao = "Por favor, digite um e-mail válido!";
                    } catch (FirebaseAuthUserCollisionException e) {
                        erroExcecao = "E-mail já cadastrado!";
                    } catch (Exception e) {
                        erroExcecao = "ao cadastrar usuário: " + e.getMessage();
                    }
                        exibirMensagem("Erro: " + erroExcecao);

                }
            }
        });
    }
    public void cadastroUsuario(View View){
            // recuperando textos dos campos

        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();


            if (!email.isEmpty()){
                if (!senha.isEmpty()){
                        Usuario usuario =new Usuario();
                        usuario.setEmail(email);
                        usuario.setSenha(senha);
                        salvarUsuario(usuario);
                }else{
                    exibirMensagem("Por favor digite uma senha!");
                }
            }else{
                exibirMensagem("Por favor digite se email!");
            }
        }

    private void exibirMensagem(String texto) {
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }

}