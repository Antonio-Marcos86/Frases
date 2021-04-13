package com.br.frases.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.br.frases.R;
import com.br.frases.helpers.ConfiguracaoFirebase;
import com.br.frases.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class InicioActivity extends AppCompatActivity {
    private EditText campoEmail,campoSenha;
    private FirebaseAuth autenticacao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        verificarUsuarioLogado();

        campoEmail = findViewById(R.id.editEmailLogin);
        campoSenha = findViewById(R.id.editSenhaLogin);


    }
    public void logar(Usuario usuario){
        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(),usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                      abrirMain();

                }else{
                    exibirMensagem("Erro ao autenticar usuário");
                }
            }
        });
    }
    public void logarUsuario(View View){
        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();


            if (!email.isEmpty()){
                if (!senha.isEmpty()){

                    Usuario usuario =new Usuario();
                    usuario.setEmail(email);
                    usuario.setSenha(senha);
                    logar(usuario);
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

    public void abrirMain(){
        startActivity(new Intent(this,MainActivity.class));
    }
    public void cadastrar(View View){
        startActivity(new Intent(this,CadastrarActivity.class));
    }
    private void verificarUsuarioLogado(){
        FirebaseUser usuarioLogado = autenticacao.getCurrentUser();
        if( usuarioLogado != null){
            String tipoUsuario = usuarioLogado.getDisplayName();
            abrirMain();
        }
    }
}