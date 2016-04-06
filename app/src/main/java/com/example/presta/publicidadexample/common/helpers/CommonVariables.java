package com.example.presta.publicidadexample.common.helpers;

/**
 * Created by Presta on 17/03/2016.
 */

// Aca se definen variables que se necesitan acceder desde cualquier lugar de la aplicación.
// Usar con responsabilidad.

public class CommonVariables {

    //region -- UUID --

    private static String uuid;

    public static void SetUuid(String uuidParam){
        uuid = uuidParam;
    }

    public static String GetUuid(){
        return uuid;
    }

    //endregion

}
