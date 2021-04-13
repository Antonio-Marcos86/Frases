package com.br.frases.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.io.ByteArrayOutputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth autenticacao;
    private MaterialSearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Frases Nerd");
        setSupportActionBar(toolbar);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        searchView = findViewById(R.id.materialsearchview);
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        // Configurando a pesquisa
        MenuItem item = menu.findItem(R.id.menuPesquisa);
        searchView.setMenuItem(item);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuSair:
                deslogarUsuario();
                break;
            case R.id.menuConfiguracoes:
                abrirConfiguracoes();
                break;
        }

        return super.onOptionsItemSelected(item);
    }



    // Armazenando as frases que podem ser geradas
    public void gerarNovaFrase(View view){
        String[] frases = {//vai gerar frases aleatoriamente
                "Vida longa e próspera (Sr. Spock - StarTrek)",                     //0
                "Eu tenho a força! (He-man)",                                       //1
                "Que a força esteja com você! (StarWars)",                          //2
                "Você não passará! (Gandalf)",                                      //3
                "Com grandes poderes vem grandes responsabilidades (Uncle Ben)",    //4
                "Ao infinito, e além (BuzzLightyear)" ,                             //5
                "Bazinga! (Shedon Cooper)" ,                                        //6
                "Que é que há, velhinho (Pernalonga)"                               //7
        };
           // VAR responsável por gerar os valores aleatórios
           // que serão utilizados para indicar a posião no vetor frases
               int numero = new Random().nextInt(8);

          // Mostrando a frase com base na posição sorteada
           TextView frase = findViewById(R.id.txtFraseGerada);
           frase.setText(frases[numero]);
    }// fechamento da função gerarNovaFrase
    private void abrirConfiguracoes() {
        startActivity(new Intent(MainActivity.this, ConfiguracaoActivity.class));
    }


    private void deslogarUsuario() {
        try {
            autenticacao.signOut();
            finish();
            startActivity(new Intent(this,InicioActivity.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}