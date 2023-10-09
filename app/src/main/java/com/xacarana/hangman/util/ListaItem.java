package com.xacarana.hangman.util;

public class ListaItem {
    private String nombre;
    private int imagen_id;

    public ListaItem(){}

    public ListaItem(String nombre,int imagen_id){
        this.nombre = nombre;
        this.imagen_id = imagen_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getImagen_id() {
        return imagen_id;
    }

    public void setImagen_id(int imagen_id) {
        this.imagen_id = imagen_id;
    }
}
