package com.xacarana.hangman.util;

public class StringUtil {
    public static String getLetra(int i)
    {
        if(i>=27)
        {
            return "";
        }
        return "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ".substring(i, i+1);
    }
}
