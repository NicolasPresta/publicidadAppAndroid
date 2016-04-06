package com.example.presta.publicidadexample.rest.get.jsonModel;

import com.example.presta.publicidadexample.common.entities.Publicidad;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Presta on 10/03/2016.
 */
// Objeto que contiene la respuesta de la API
public class PublicidadesResponse {

    @SerializedName("publicidades")
    private  ArrayList<Publicidad> publicidades;

    public ArrayList<Publicidad> getPublicidades() {
        return publicidades;
    }

    public void setPublicidades(ArrayList<Publicidad> publicidades) {
        this.publicidades = publicidades;
    }
}