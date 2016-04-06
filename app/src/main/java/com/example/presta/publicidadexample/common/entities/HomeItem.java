package com.example.presta.publicidadexample.common.entities;

/**
 * Created by Presta on 05/04/2016.
 */
public class HomeItem {

    private Object entidad;
    private int clase;

    public HomeItem(int clase, Object entidad) {
        this.clase = clase;
        this.entidad = entidad;
    }

    public Object getEntidad() {
        return entidad;
    }

    public void setEntidad(Object entidad) {
        this.entidad = entidad;
    }

    public int getClase() {
        return clase;
    }

    public void setClase(int clase) {
        this.clase = clase;
    }
}
