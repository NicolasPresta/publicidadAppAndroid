package com.example.presta.publicidadexample.rest;

/**
 * Created by Presta on 10/03/2016.
 */
public class ApiConstants {

    //public static final String IP = "104.131.38.51";
    //public static final String IP = "10.1.1.136";
    public static final String IP = "192.168.0.103";

    // Si pongo la URL no resuelve el host, aunque seguramente funciona mal el DNS pq a veces desde chrome pasa igual...
    //http://prueba1.nicolaspresta.com.ar/

    //public static final String PORT = "80";
    public static final String PORT = "3002";

    public static final String URL_BASE = "http://" + IP + ":" + PORT;

    public static final String METHOD_PHONEDATA = "phoneData";
    public static final String METHOD_USERDATA = "userData";
    public static final String METHOD_GPSDATA = "gpsData";


    // http://192.168.0.103:3002/publicidades
    public static final String URL_PUBLICIDADES = "/publicidades/{uuid}";
    public static final String URL_PUBLICIDAD = "/publicidad/{uuid}/{id}";
}

