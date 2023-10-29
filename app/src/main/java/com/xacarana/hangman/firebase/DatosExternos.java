package com.xacarana.hangman.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.xacarana.hangman.errores.LecturaDatosException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DatosExternos {

    public static ArrayList<String> getPalabrasPorCategoria(String categoria) throws LecturaDatosException {

        ArrayList<String> palabras = new ArrayList<String>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("categorias").document(categoria);
        final String[] tipo_error = {null};

            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>(){
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            //Log.d("XACA", "DocumentSnapshot data: " + document.getData());
                            Map<String,Object> temp = document.getData();

                            for ( Map.Entry<String,Object> i : temp.entrySet())
                            {
                                //Log.d("XACA",i.getKey() + ":" + i.getValue());
                                palabras.add(i.getValue().toString());
                            }

                        } else {
                            tipo_error[0] = LecturaDatosException.DOCUMENTO_NO_ENCONTRADO;
                        }
                    } else {
                        tipo_error[0] = LecturaDatosException.ERROR_DE_FIREBASE + " " +task.getException();
                    }
                }
            });

        if(tipo_error[0] != null)
        {
            throw new LecturaDatosException(tipo_error[0]);
        }

        return palabras;
    }

}
