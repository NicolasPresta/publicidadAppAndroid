package com.example.presta.publicidadexample.rest.get.jsonModel;

import com.example.presta.publicidadexample.common.entities.Publicidad;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Presta on 10/03/2016.
 */
// Objeto que contiene la respuesta de la API
public class PublicidadResponse {

    @SerializedName("publicidad")
    private Publicidad publicidad;

    public Publicidad getPublicidad() {
        return publicidad;
    }

    public void setPublicidad(Publicidad publicidad) {
        this.publicidad = publicidad;
    }
}