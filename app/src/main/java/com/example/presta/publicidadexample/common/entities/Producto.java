package com.example.presta.publicidadexample.common.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Presta on 05/04/2016.
 */
public class Producto {

    private Integer id;
    private String nombre;
    private String descripcion;
    private String imagen;
    private String urlCompartir;
    private Double precio;

    public Producto(Integer id, String imagen, String descripcion, String nombre, String urlCompartir, Double precio) {
        this.id = id;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.urlCompartir = urlCompartir;
        this.precio = precio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrlCompartir() {
        return urlCompartir;
    }

    public void setUrlCompartir(String urlCompartir) {
        this.urlCompartir = urlCompartir;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
