package com.xacarana.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.xacarana.hangman.firebase.DatosExternos;
import com.xacarana.hangman.util.Categoria;
import com.xacarana.hangman.logica.InventarioCategorias;
import com.xacarana.hangman.logica.Puntaje;
import com.xacarana.hangman.modelo.Datos;
import com.xacarana.hangman.util.DatosEvent;

import java.util.ArrayList;

public class Main extends AppCompatActivity {
    private Datos persistencia_datos;
    private InventarioCategorias inventario_palabras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        persistencia_datos = ((Datos)this.getApplication());
        inventario_palabras = new InventarioCategorias();

        persistencia_datos.setPuntaje(new Puntaje());

        /*Timer t = new Timer();
        t.schedule(new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Main.this, SeleccionCategoria.class);
                startActivity(intent);
                Main.this.finish();
            }
        }, 3000);*/

        //TODO: Realizar la logica para leer desde Firestore los datos de todas las categorias
        //TODO: Marcar las palabras que han salido, para que no se repitan

        //Es necesario hacer esta invocación ya que la petición a Firestore es asincrona
        DatosExternos.getPalabrasPorCategoria("paises", new DatosEvent() {
            @Override
            public void onDatosLeidos(ArrayList<String> datos, String error) {
                if(error == null)
                {
                    //Log.d("Prueba",datos.toString());

                    inventario_palabras.addCategoria(Categoria.PAISES.ordinal(),datos);
                    persistencia_datos.setInventarioPalabras(inventario_palabras);

                    Intent intent = new Intent(Main.this, SeleccionCategoria.class);
                    startActivity(intent);
                    Main.this.finish();
                }
                else{
                    //TODO: Controlar este error
                    Log.d("Prueba","No se leyeron datos");
                }
            }
        });
        //Aca se debe pasar el id de la categoria como una constante



        // Create a new user with a first and last name
        /*Map<String, Object> categoria = new HashMap<>();
        categoria.put("first", "Ada");
        categoria.put("last", "Lovelace");
        categoria.put("born", 1815);*/


        // Add a new document with a generated ID
        /*db.collection("categorias")
            .add(categoria)
            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Log.d("HANGMAN", "DocumentSnapshot added with ID: " + documentReference.getId());
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w("HANGMAN", "Error adding document", e);
                }
            });*/
    }
}