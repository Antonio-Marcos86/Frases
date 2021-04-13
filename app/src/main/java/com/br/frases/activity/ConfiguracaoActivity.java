package com.br.frases.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.br.frases.R;
import com.br.frases.helpers.ConfiguracaoFirebase;
import com.br.frases.helpers.UsuarioFirebase;
import com.br.frases.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class ConfiguracaoActivity extends AppCompatActivity {

    private DatabaseReference firebaseReference;
    private EditText editNome,editEmail,edittelefone;
    private TextView nomePerfil;
    private static  final int SELECAO_GALERIA = 200;
    private CircleImageView imagemUsuarioPerfil;
    private StorageReference storageReference;
    private  String idUsuarioLogado;
    private String urlImagemSelecionada ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao);
        inicializarComponentes();
        storageReference = ConfiguracaoFirebase.getFirebaseStorage();

        storageReference = ConfiguracaoFirebase.getFirebaseStorage();
        firebaseReference = ConfiguracaoFirebase.getReferenciaFirebase();
        idUsuarioLogado = UsuarioFirebase.getIdUsuario();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Configurações");
        setSupportActionBar(toolbar);
        // Para mostrar a seta de voltar para home
        // Necessário configurar no AndroidManifests
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imagemUsuarioPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if(i.resolveActivity(getPackageManager())!= null){
                    startActivityForResult(i,SELECAO_GALERIA);
                }
            }

        });
        
        // recuperando dados do usuário

        recuperarDados();



    }

    private void recuperarDados() {
        DatabaseReference usuarioRef = firebaseReference
                .child("usuarios").child(idUsuarioLogado);
        usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if(datasnapshot.getValue() != null){
                    Usuario usuario = datasnapshot.getValue(Usuario.class);
                    nomePerfil.setText(usuario.getNome());
                    editNome.setText(usuario.getNome());
                    editEmail.setText(usuario.getEmail());
                    edittelefone.setText(usuario.getTelefone());
                    urlImagemSelecionada = usuario.getUrlImagem();
                    if(urlImagemSelecionada != ""){
                        Picasso.get().load(urlImagemSelecionada).into(imagemUsuarioPerfil);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void validarDados(View View) {
        // Valida se os campos foram preenchidos

        String nome = editNome.getText().toString();
        String email = editEmail.getText().toString();
        String telefone = edittelefone.getText().toString();


            if (!nome.isEmpty()) {
                if (!email.isEmpty()) {
                    if (!telefone.isEmpty()) {
                        Usuario usuario = new Usuario();
                        usuario.setIdUsuario(idUsuarioLogado);
                        usuario.setNome(nome);
                        usuario.setEmail(email);
                        usuario.setTelefone(telefone);
                        usuario.setUrlImagem(urlImagemSelecionada);
                        usuario.salvar();
                        exibirMensagem("Dados salvos com sucesso!");
                        finish();
                        startActivity(new Intent(this, MainActivity.class));
                    } else {
                        exibirMensagem("Digite seu telefone!");
                    }

                } else {
                    exibirMensagem("Digite seu email!");
                }
            } else {
                exibirMensagem("Digite seu nome completo!");
            }

    }


    private void exibirMensagem(String texto) {
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Bitmap imagem = null;

            try {
                switch (requestCode) {
                    case SELECAO_GALERIA:
                        Uri localImagem = data.getData();
                        imagem = MediaStore.Images.Media.getBitmap(getContentResolver(), localImagem);
                        break;
                }
                if (imagem != null) {
                    imagemUsuarioPerfil.setImageBitmap(imagem);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    imagem.compress(Bitmap.CompressFormat.JPEG, 70, baos);
                    byte[] dadosImagem = baos.toByteArray();

                    // Configurando o Storage
                    final StorageReference imagemRef = storageReference
                            .child("imagens")
                            .child("empresas")
                            .child(idUsuarioLogado + "jpeg");

                    // Tarefa de Upload
                    UploadTask uploadTask = imagemRef.putBytes(dadosImagem);

                    // Em caso de falha no upload
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ConfiguracaoActivity.this, "Erro ao fazer o upload da imagem", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            imagemRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    Uri url = task.getResult();
                                }
                            });
                            Toast.makeText(ConfiguracaoActivity.this, "Sucesso ao fazer upload da imagem", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void inicializarComponentes(){
        editNome = findViewById(R.id.editNomeConfig);
        editEmail = findViewById(R.id.editEmailConfig);
        edittelefone = findViewById(R.id.editTelefone);
        imagemUsuarioPerfil = findViewById(R.id.imagemUsuario);
        nomePerfil = findViewById(R.id.NomeUsuario);


    }
}