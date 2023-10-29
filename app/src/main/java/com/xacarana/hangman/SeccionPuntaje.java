package com.xacarana.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xacarana.hangman.logica.Puntaje;
import com.xacarana.hangman.modelo.Datos;

public class SeccionPuntaje extends AppCompatActivity {

    private Puntaje puntaje;
    private TextView humano, pc;
    private ImageButton ib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntaje);

        puntaje = ((Datos)this.getApplication()).getPuntaje();
        humano = (TextView) findViewById(R.id.txt_humano);
        pc = (TextView) findViewById(R.id.txt_pc);
        humano.setText(String.format("%d", puntaje.getPuntajeHumano()));
        pc.setText(String.format("%d", puntaje.getPuntajePC()));
        ib = (ImageButton) findViewById(R.id.imageButton2);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}