package com.example.presta.publicidadexample.common.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Presta on 05/04/2016.
 */
public class Promocion {

    private Integer id;
    private String titulo;
    private String descripcion;
    private String vigenciaDesde;
    private String vigenciaHasta;
    private String condiciones;
    private String imagen;
    private String urlCompartir;
    private String codigo;
    private Integer altoImagen;
    private Float imgProporcion;

    public Promocion(Integer id, String descripcion, String vigenciaHasta, String urlCompartir, String codigo, String imagen, String condiciones, String vigenciaDesde, String titulo, Float imgProporcion) {
        this.descripcion = descripcion;
        this.vigenciaHasta = vigenciaHasta;
        this.urlCompartir = urlCompartir;
        this.codigo = codigo;
        this.imagen = imagen;
        this.condiciones = condiciones;
        this.vigenciaDesde = vigenciaDesde;
        this.titulo = titulo;
        this.id = id;
        this.imgProporcion = imgProporcion;
        // this.altoImagen = altoImagen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCondiciones() {
        return condiciones;
    }

    public void setCondiciones(String condiciones) {
        this.condiciones = condiciones;
    }

    public String getVigenciaDesde() {
        return vigenciaDesde;
    }

    public void setVigenciaDesde(String vigenciaDesde) {
        this.vigenciaDesde = vigenciaDesde;
    }

    public String getVigenciaHasta() {
        return vigenciaHasta;
    }

    public void setVigenciaHasta(String vigenciaHasta) {
        this.vigenciaHasta = vigenciaHasta;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getUrlCompartir() {
        return urlCompartir;
    }

    public void setUrlCompartir(String urlCompartir) {
        this.urlCompartir = urlCompartir;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getAltoImagen() {
        return altoImagen;
    }

    public void setAltoImagen(Integer altoImagen) {
        this.altoImagen = altoImagen;
    }

    public Float getImgProporcion() {
        return imgProporcion;
    }

    public void setImgProporcion(Float imgProporcion) {
        this.imgProporcion = imgProporcion;
    }
}
