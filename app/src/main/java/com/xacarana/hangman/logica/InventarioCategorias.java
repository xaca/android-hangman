package com.xacarana.hangman.logica;

import com.xacarana.hangman.errores.LecturaDatosException;
import com.xacarana.hangman.firebase.DatosExternos;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by User on 25/09/2016.
 */
public class InventarioCategorias {

    private String categorias[];
    private ArrayList<ArrayList<String>> palabras;
    private int categoria_actual;

    public InventarioCategorias()
    {
        ArrayList<String> temp;
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

        temp = new ArrayList<String>();
        temp.add("noruega");
        temp.add("grecia");
        temp.add("holanda");
        temp.add("españa");
        temp.add("nicaragua");
        temp.add("honduras");
        temp.add("rumania");
        temp.add("kuwait");
        temp.add("puerto rico");
        temp.add("polonia");

        palabras.add(temp);
        //TODO: Realizar la logica para leer desde Firestore los datos de todas las categorias
        //TODO: Marcar las palabras que han salido, para que no se repitan
        try{
            //Aca se debe pasar el id de la categoria como una constante
            temp.addAll(DatosExternos.getPalabrasPorCategoria("paises"));
        }
        catch(LecturaDatosException e){

        }
    }

    public String palabraAleatoria(int categoria){
        Random rnd = new Random();
        int min = 0, max = palabras.size()-1;
        int aleatorio = rnd.nextInt((max - min) + 1) + min;
        setCategoria_actual(categoria);
        return (palabras.get(getCategoria_actual())).get(aleatorio);
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
}
