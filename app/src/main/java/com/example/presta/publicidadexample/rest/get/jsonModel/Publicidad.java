package com.example.presta.publicidadexample.rest.get.jsonModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Presta on 10/03/2016.
 */
public class Publicidad {

    /*
        titulo:"Una super oferta",
        descripcion:"TV 42 pulgadas a solo $10000",
        img: "http://www.algo.com/imagen.jpg",
        vigenciaDesde: "09/03/2016",
        vigenciaHasta: "30/03/2016",
        condiciones: "solo si tenes familia en la antartida"
    */

    //region "-- CONSTRUCTORS --"

    public Publicidad(String titulo, String descripcion, String vigenciaDesde, String vigenciaHasta, String condiciones, String img, String urlCompartir) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.vigenciaDesde = vigenciaDesde;
        this.vigenciaHasta = vigenciaHasta;
        this.condiciones = condiciones;
        this.img = img;
        this.urlCompartir = urlCompartir;
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

    //endregion

    //region "-- PUBLIC METHODS --"

    //endregion
}