package com.example.presta.publicidadexample.rest;

import com.example.presta.publicidadexample.rest.model.PublicidadResponse;
import com.example.presta.publicidadexample.rest.model.PublicidadesResponse;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Presta on 10/03/2016.
 */
public interface IPublicidadesService {

    // GetAll publicidades
    @GET(ApiConstants.URL_PUBLICIDADES)
    Observable<PublicidadesResponse> getAll();

    // GetById publicidad
    @GET(ApiConstants.URL_PUBLICIDAD)
    Observable<PublicidadResponse> getById(@Path("id") Integer id);

}
