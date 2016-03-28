package com.example.presta.publicidadexample.rest.post;

/**
 * Created by Presta on 20/03/2016.
 */
// Interfaz que deben implementar aquellos objetos que quieran hacer un POST
public interface OnPostCompleted {
    void onPostCompleted(String metod, Integer result);
}
