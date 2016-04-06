package com.example.presta.publicidadexample.common.entities;

/**
 * Created by Presta on 05/04/2016.
 */
public class Sucursal {

    private Integer id;
    private String nombre;
    private String direccion;
    private String imagen;
    private int latitud;
    private int longitud;

    public Sucursal(Integer id, String nombre, String direccion, String imagen, int latitud, int longitud) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.imagen = imagen;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getLatitud() {
        return latitud;
    }

    public void setLatitud(int latitud) {
        this.latitud = latitud;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }
}
