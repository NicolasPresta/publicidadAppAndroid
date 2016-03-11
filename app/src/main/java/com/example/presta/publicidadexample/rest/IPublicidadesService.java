package com.example.presta.publicidadexample.rest;

import com.example.presta.publicidadexample.rest.model.PublicidadesResponse;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by Presta on 10/03/2016.
 */
public interface IPublicidadesService {

    // GetAll publicidades
    @GET(ApiConstants.URL_PUBLICIDADES)
    Observable<PublicidadesResponse> getAll();

}
