package com.xacarana.hangman.logica;

import android.os.Debug;
import android.util.Log;

import com.xacarana.hangman.errores.LecturaDatosException;
import com.xacarana.hangman.firebase.DatosExternos;
import com.xacarana.hangman.util.DatosEvent;

import java.util.ArrayList;
import java.util.Random;

;

public class InventarioCategorias {


    private String categorias[];
    private ArrayList<ArrayList<String>> palabras;
    private ArrayList<String> palabras_usadas;
    private int categoria_actual;

    public InventarioCategorias()
    {
        ArrayList<String> temp;
        palabras_usadas = new ArrayList<String>();
        palabras = new ArrayList<ArrayList<String>>();
        //categorias = new String[] { "Actores", "Películas", "Deportes", "Frutas", "Grupos de Musica", "Países" };

        temp = new ArrayList<String>();
        temp.add("al pacino");
        temp.add("arnold schwarzenegger");
        temp.add("sylvester stallone");
        temp.add("adam sandler");
        temp.add("uma thurman");
        temp.add("monica bellucci");
        temp.add("jim carrey");
        temp.add("vincent cassel");
        temp.add("brad pitt");
        temp.add("angelina jolie");

        palabras.add(temp);

        temp = new ArrayList<String>();
        temp.add("matrix");
        temp.add("snatch");
        temp.add("rambo");
        temp.add("terminator");
        temp.add("los increibles");
        temp.add("dumbo");
        temp.add("madmax");
        temp.add("cobra");
        temp.add("shrek");
        temp.add("hercules");

        palabras.add(temp);

        temp = new ArrayList<String>();
        temp.add("baloncesto");
        temp.add("natacion");
        temp.add("boxeo");
        temp.add("billar");
        temp.add("tenis de mesa");
        temp.add("ciclismo");
        temp.add("balonmano");
        temp.add("voleibol");
        temp.add("lucha");
        temp.add("futbol");

        palabras.add(temp);

        temp = new ArrayList<String>();
        temp.add("mango");
        temp.add("papaya");
        temp.add("cereza");
        temp.add("fresa");
        temp.add("sandia");
        temp.add("granadilla");
        temp.add("coco");
        temp.add("guayaba");
        temp.add("kiwi");
        temp.add("uchuva");

        palabras.add(temp);

        temp = new ArrayList<String>();
        temp.add("blur");
        temp.add("nirvana");
        temp.add("metallica");
        temp.add("cafe tacuba");
        temp.add("soda estereo");
        temp.add("aterciopelados");
        temp.add("molotov");
        temp.add("dos minutos");
        temp.add("caifanes");
        temp.add("bajo fondo");

        palabras.add(temp);

        final ArrayList<String> datos_servidor;
        datos_servidor = new ArrayList<String>();
        datos_servidor.add("noruega");
        datos_servidor.add("grecia");
        datos_servidor.add("holanda");
        datos_servidor.add("españa");
        datos_servidor.add("nicaragua");
        datos_servidor.add("honduras");
        datos_servidor.add("rumania");
        datos_servidor.add("kuwait");
        datos_servidor.add("puerto rico");
        datos_servidor.add("polonia");

        palabras.add(temp);
    }

    public String palabraAleatoria(int categoria){

        Random rnd = new Random();
        ArrayList <String>temp;
        int min, max, aleatorio;
        String palabra_usada;
        setCategoria_actual(categoria);
        temp = (palabras.get(getCategoria_actual()));

        min = 0;
        max = temp.size()-1;
        aleatorio = rnd.nextInt((max - min) + 1) + min;

        palabra_usada = temp.remove(aleatorio);
        palabras.set(getCategoria_actual(),temp);
        palabras_usadas.add(palabra_usada);

        //TODO:Controlar error cuando no hay mas palabras en la categoria
        return palabra_usada;
    }

    public void setCategorias(String[] categorias)
    {
        this.categorias = categorias;
    }

    public String getNombreCategoria(int id_categoria)
    {
        return categorias[id_categoria];
    }

    public int getCategoria_actual() {
        return categoria_actual;
    }

    public void setCategoria_actual(int categoria_actual) {
        this.categoria_actual = categoria_actual;
    }

    public void addCategoria(int categoria,ArrayList<String> nuevos_datos){
        ArrayList<String> temp = palabras.get(categoria);
        temp.addAll(nuevos_datos);
        palabras.set(categoria,temp);
    }
}
