package com.example.presta.publicidadexample.rest.get;

import com.example.presta.publicidadexample.rest.ApiConstants;
import com.example.presta.publicidadexample.rest.get.jsonModel.PublicidadResponse;
import com.example.presta.publicidadexample.rest.get.jsonModel.PublicidadesResponse;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by Presta on 10/03/2016.
 */
// Esta inferfaz define todos los puntos de entrada a la api que sean GET
public interface IApiGetService {

    // GetAll publicidades
    @GET(ApiConstants.GET_METHOD_PUBLICIDADES)
    Observable<PublicidadesResponse> getAll(@Path("uuid") String uuid);

    // GetById publicidad
    @GET(ApiConstants.GET_METHOD_PUBLICIDAD)
    Observable<PublicidadResponse> getById(@Path("uuid") String uuid, @Path("id") Integer id);

}
