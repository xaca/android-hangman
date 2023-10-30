package com.xacarana.hangman.modelo;

import android.app.Application;

import com.xacarana.hangman.logica.LogicaAhorcado;
import com.xacarana.hangman.logica.Puntaje;

/**
 * Created by User on 25/09/2016.
 */
public class Datos extends Application {

    private LogicaAhorcado logica;
    private Puntaje puntaje;
    /*Toast.makeText(Juego.this,
    ""+getBaseContext().getResources().getDisplayMetrics().widthPixels, Toast.LENGTH_SHORT).show();*/
    public Puntaje getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Puntaje puntaje) {
        this.puntaje = puntaje;
    }

    public LogicaAhorcado getLogica() {
        return logica;
    }

    public void setLogica(LogicaAhorcado logica) {
        this.logica = logica;
    }
}
