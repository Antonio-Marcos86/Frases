package com.br.frases.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.br.frases.R;
import com.br.frases.activity.InicioActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               mostrarInicio();
               finish();
           }
       },2000);
    }
   private void mostrarInicio(){
        startActivity(new Intent(this, InicioActivity.class));
   }
}