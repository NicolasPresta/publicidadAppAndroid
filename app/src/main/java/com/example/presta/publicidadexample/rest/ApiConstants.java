package com.example.presta.publicidadexample.rest;

/**
 * Created by Presta on 10/03/2016.
 */
public class ApiConstants {

    //public static final String IP = "104.131.38.51";
    public static final String IP = "192.168.0.102";

    // Si pongo la URL no resuelve el host, aunque seguramente funciona mal el DNS pq a veces desde chrome pasa igual...
    //http://prueba1.nicolaspresta.com.ar/

   // public static final String PORT = "80";
    public static final String PORT = "3002";

    public static final String URL_BASE = "http://" + IP + ":" + PORT;


    // http://192.168.0.103:3002/publicidades
    public static final String URL_PUBLICIDADES = "/publicidades/{uuid}";
    public static final String URL_PUBLICIDAD = "/publicidad/{uuid}/{id}";

}

