package com.xacarana.hangman;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.xacarana.hangman.ui.ListaCategoriasAdapter;
import com.xacarana.hangman.util.ListaItem;

public class SeleccionCategoria extends Activity {

    private RecyclerView rv;
    protected ListaCategoriasAdapter lAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_categoria);
        rv = findViewById(R.id.lista);
        ListaItem[] temp = crearModeloItems(getResources().getStringArray(R.array.lista_categorias));
        lAdapter = new ListaCategoriasAdapter(temp);
        lAdapter.setListener(new ListaCategoriasAdapter.OnClickListener(){
            @Override
            public void onClick(int position) {
                CargarJuego(position);
            }
        });
        rv.setAdapter(lAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);
    }

    public ListaItem[] crearModeloItems(String[]categorias){
        int[]imagenes = {R.drawable.categoria_peliculas,R.drawable.categoria_cine,
        R.drawable.categoria_deportes,R.drawable.categoria_frutas,
                R.drawable.categoria_musica,R.drawable.categoria_paises};
        ListaItem[] ans = new ListaItem[imagenes.length];
        for (int i = 0; i < imagenes.length; i++) {
            ans[i] = new ListaItem(categorias[i],imagenes[i]);
        }
        return ans;
    }

    public void CargarJuego(int posicion){
        Intent intent = new Intent(SeleccionCategoria.this, Juego.class);
        intent.putExtra("categoria",""+posicion);
        startActivity(intent);

    }

}