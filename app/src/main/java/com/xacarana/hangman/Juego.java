package com.xacarana.hangman;

import static com.xacarana.hangman.util.StringUtil.*;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.xacarana.hangman.logica.LogicaAhorcado;
import com.xacarana.hangman.modelo.Datos;
import com.xacarana.hangman.util.StringUtil;

import java.util.Timer;
import java.util.TimerTask;

public class Juego extends AppCompatActivity implements View.OnClickListener{

    private LogicaAhorcado la;
    private TextView tv, po;
    private ImageView iv;
    private ImageButton ib;
    private int estado_partida;
    private int imagenes_juego[] = {R.drawable.ahorcado_01, R.drawable.ahorcado_02, R.drawable.ahorcado_03, R.drawable.ahorcado_04, R.drawable.ahorcado_05, R.drawable.ahorcado_06, R.drawable.ahorcado_07};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        tv = (TextView) findViewById(R.id.categoria_seleccionada);
        po = (TextView) findViewById(R.id.palabraOculta);
        iv = (ImageView) findViewById(R.id.imageView2);
        ib = (ImageButton) findViewById(R.id.imageButton);
        iv.setImageResource(imagenes_juego[0]);

        Datos datos = ((Datos)this.getApplication());

        if(datos.getLogica() == null)
        {
            la = new LogicaAhorcado();
            la.setCategorias(getResources().getStringArray(R.array.lista_categorias));
            datos.setLogica(la);
        }
        else{
            la = datos.getLogica();
        }
        la.seleccionarCategoria(Integer.parseInt(getIntent().getStringExtra("categoria")));
        tv.setText(la.getNombreCategoria());
        po.setText(la.getProgresoPalabra());
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Juego.this, SeccionPuntaje.class);
                startActivity(intent);
            }
        });
        estado_partida = LogicaAhorcado.CONTINUAR_PARTIDA;
        la.setPuntaje(datos.getPuntaje());
        la.resetJuego();
        crearTeclado();

    }

    public void crearTeclado(){
        GridLayout gl = (GridLayout) findViewById(R.id.teclado);
        GridLayout.LayoutParams lp;
        int contador  = 0, size, fuente;
        int ancho = getBaseContext().getResources().getDisplayMetrics().widthPixels;
        Button btn;

        size = 10;//ancho <= 480? 38 : 50;
        fuente = 14;// ancho <= 480?15:22;

        for(int i= 0;i<gl.getRowCount();i++)
        {
            for(int j=0;j<gl.getColumnCount();j++)
            {
                lp = new GridLayout.LayoutParams(GridLayout.spec(i), GridLayout.spec(j));
                btn = new Button(this);
                btn.setOnClickListener(this);
                btn.setId(contador);
                btn.setText(StringUtil.getLetra(contador++));
                //btn.setTextSize(fuente);
                //btn.setWidth(size);
                //btn.setHeight(size);
                gl.addView(btn,lp);
                if(contador == 28)
                {
                    btn.setVisibility(Button.INVISIBLE);
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        Button temp = (Button)view;
        view.setEnabled(false);
        if(la.validarLetra(getLetra(view.getId()).toLowerCase().charAt(0)))
        {
            //view.setBackgroundColor(Color.GREEN);
            temp.setTextColor(Color.BLACK);
            temp.setBackgroundTintList(getBaseContext().getColorStateList(R.color.Correcto));
        }
        else
        {
            //view.setBackgroundColor(Color.RED);
            temp.setTextColor(Color.WHITE);
            temp.setBackgroundTintList(getBaseContext().getColorStateList(R.color.Incorrecto));
        }

        po.setText(la.getProgresoPalabra());
        estado_partida = la.ganoPartida();
        switch (estado_partida)
        {
            case LogicaAhorcado.GANO_PARTIDA:
                mostrarMensaje(getString(R.string.gano_partida));
                break;
            case LogicaAhorcado.PERDIO_PARTIDA:
                iv.setImageResource(imagenes_juego[la.getErrores()]);
                mostrarMensaje(getString(R.string.perdio_partida));
                break;
            case LogicaAhorcado.CONTINUAR_PARTIDA:
                iv.setImageResource(imagenes_juego[la.getErrores()]);
                break;
        }
        ((Datos)this.getApplication()).setPuntaje(la.getPuntaje());
    }

    public void mostrarMensaje(String mensaje){
        new AlertDialog.Builder(this)
                .setTitle("")
                .setMessage(mensaje)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Juego.this, SeleccionCategoria.class);
                startActivity(intent);
            }
        }, 2000);
        /*.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // do nothing
            }
        })*/
    }
}