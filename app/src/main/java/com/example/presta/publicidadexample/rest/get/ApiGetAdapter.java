package com.example.presta.publicidadexample.rest.get;

import com.example.presta.publicidadexample.common.CommonVariables;
import com.example.presta.publicidadexample.rest.ApiConstants;
import com.example.presta.publicidadexample.rest.get.jsonModel.PublicidadResponse;
import com.example.presta.publicidadexample.rest.get.jsonModel.PublicidadesResponse;

import retrofit.RestAdapter;
import rx.Observable;

/**
 * Created by Presta on 10/03/2016.
 */
public class ApiGetAdapter {

    private static IApiGetService API_SERVICE;

    // Retorna el servicio base para llamar al servidor, es un singleton, solo se intancia la primera vez que se llama.
    public static IApiGetService getApiService() {
        if (API_SERVICE == null) {

            RestAdapter adapter = new RestAdapter.Builder()
                    .setEndpoint(ApiConstants.URL_BASE)
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build();

            API_SERVICE = adapter.create(IApiGetService.class);
        }

        return API_SERVICE;
    }

    //
    public static Observable<PublicidadesResponse> getAll() {
        return getApiService().getAll(CommonVariables.GetUuid());
    }

    public static Observable<PublicidadResponse> getById(Integer id) {
        return getApiService().getById(CommonVariables.GetUuid(), id);
    }
}
