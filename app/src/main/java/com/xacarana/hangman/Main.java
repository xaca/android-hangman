package com.xacarana.hangman;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.xacarana.hangman.logica.Puntaje;
import com.xacarana.hangman.modelo.Datos;

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