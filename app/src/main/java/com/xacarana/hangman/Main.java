package com.xacarana.hangman;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.xacarana.hangman.logica.Puntaje;
import com.xacarana.hangman.util.Datos;

import java.util.Timer;
import java.util.TimerTask;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ((Datos)this.getApplication()).setPuntaje(new Puntaje());
        Timer t = new Timer();
        t.schedule(new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Main.this, SeleccionCategoria.class);
                startActivity(intent);
                Main.this.finish();
            }
        }, 3000);

    }
}