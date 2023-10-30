package com.xacarana.hangman.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.xacarana.hangman.errores.LecturaDatosException;
import com.xacarana.hangman.util.DatosEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DatosExternos {


    public static void getPalabrasPorCategoria(String categoria,final DatosEvent event){

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("categorias").document(categoria);

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>(){
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                String error = null;
                ArrayList<String> palabras = new ArrayList<String>();

                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists())
                    {
                        //Log.d("Prueba", "DocumentSnapshot data: " + document.getData());
                        Map<String,Object> temp = document.getData();

                        for ( Map.Entry<String,Object> i : temp.entrySet())
                        {
                            //Log.d("Prueba",i.getKey() + ":" + i.getValue());
                            palabras.add(i.getValue().toString());
                        }
                    } else {
                        error = LecturaDatosException.DOCUMENTO_NO_ENCONTRADO;
                    }

                } else {
                    error = LecturaDatosException.ERROR_DE_FIREBASE + " " +task.getException();
                }
                event.onDatosLeidos(palabras,error);
            }
        });
    }

}
