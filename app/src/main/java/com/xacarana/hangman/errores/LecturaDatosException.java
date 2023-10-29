package com.xacarana.hangman.errores;

public class LecturaDatosException extends Exception{
    public static final String DOCUMENTO_NO_ENCONTRADO  = "Documento no encontrado";
    public static final String ERROR_DE_FIREBASE  = "Error de firebase";
    public LecturaDatosException(String error)
    {
        super(error);
    }
}
