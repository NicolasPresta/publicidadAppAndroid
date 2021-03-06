package com.example.presta.publicidadexample.common.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Presta on 10/03/2016.
 */
public class Publicidad {

    //region "-- CONSTRUCTORS --"

    public Publicidad(Integer id, String titulo, String descripcion, String vigenciaDesde, String vigenciaHasta, String condiciones, String img, String urlCompartir, Float imgProporcion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.vigenciaDesde = vigenciaDesde;
        this.vigenciaHasta = vigenciaHasta;
        this.condiciones = condiciones;
        this.img = img;
        this.urlCompartir = urlCompartir;
        this.imgProporcion = imgProporcion;
    }

    //endregion

    //region "-- ATRIBUTES --"

    @SerializedName("id")
    private Integer id;

    @SerializedName("titulo")
    private String titulo;

    @SerializedName("descripcion")
    private String descripcion;

    @SerializedName("vigenciaDesde")
    private String vigenciaDesde;

    @SerializedName("vigenciaHasta")
    private String vigenciaHasta;

    @SerializedName("condiciones")
    private String condiciones;

    @SerializedName("img")
    private String img;

    @SerializedName("urlCompartir")
    private String urlCompartir;

    // alto  / ancho
    @SerializedName("imgProporcion")
    private Float imgProporcion;


    //endregion

    //region "-- SETTERS --"

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setVigenciaDesde(String vigenciaDesde) {
        this.vigenciaDesde = vigenciaDesde;
    }

    public void setVigenciaHasta(String vigenciaHasta) {
        this.vigenciaHasta = vigenciaHasta;
    }

    public void setCondiciones(String condiciones) {
        this.condiciones = condiciones;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setUrlCompartir(String URLCompartir) {
        this.urlCompartir = URLCompartir;
    }

    public void setImgProporcion(Float imgProporcion) {
        this.imgProporcion = imgProporcion;
    }

    //endregion

    //region "-- GETTERS --"

    public Integer getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getVigenciaDesde() {
        return vigenciaDesde;
    }

    public String getVigenciaHasta() {
        return vigenciaHasta;
    }

    public String getImg() {
        return img;
    }

    public String getCondiciones() {
        return condiciones;
    }

    public String getUrlCompartir() {
        return urlCompartir;
    }

    public Float getImgProporcion() {
        return imgProporcion;
    }

    //endregion

    //region "-- PUBLIC METHODS --"

    //endregion
}