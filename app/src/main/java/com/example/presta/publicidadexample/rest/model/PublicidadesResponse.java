package com.example.presta.publicidadexample.rest.model;

import com.example.presta.publicidadexample.entities.Publicidad;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Presta on 10/03/2016.
 */
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