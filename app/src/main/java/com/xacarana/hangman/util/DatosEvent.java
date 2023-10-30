package com.xacarana.hangman.util;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;

public interface DatosEvent {
    public void onDatosLeidos(ArrayList<String> datos, String error);
}
