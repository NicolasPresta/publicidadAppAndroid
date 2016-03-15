package com.example.presta.publicidadexample.rest;

import com.example.presta.publicidadexample.rest.model.PublicidadResponse;
import com.example.presta.publicidadexample.rest.model.PublicidadesResponse;

import java.lang.reflect.Type;

import retrofit.RestAdapter;
import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;
import rx.Observable;

/**
 * Created by Presta on 10/03/2016.
 */
public class PublicidadesAdapter {

    private static IPublicidadesService API_SERVICE;

    public static IPublicidadesService getApiService(){
        if(API_SERVICE == null) {

            RestAdapter adapter = new RestAdapter.Builder()
                    .setEndpoint(ApiConstants.URL_BASE)
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build();

            API_SERVICE = adapter.create(IPublicidadesService.class);
        }

        return API_SERVICE;
    }

    public static Observable<PublicidadesResponse> getAll() {
        return getApiService().getAll();
    }

    public static Observable<PublicidadResponse> getById(Integer id) {
        return getApiService().getById(id);
    }
}
