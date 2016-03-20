package com.example;

import java.io.Console;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;


public class EntitiesGenerator {

    private static final String PROJECT_DIR = System.getProperty("user.dir").replace("\\", "/");

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "com.example.presta.publicidadexample.dataAccess.model");

        Entity appConfig = schema.addEntity("AppConfig");
        appConfig.addIdProperty();
        appConfig.addBooleanProperty("esPrimerUso");

        Entity userData = schema.addEntity("UserData");
        userData.addIdProperty();
        userData.addDateProperty("fechaNacimiento");
        userData.addStringProperty("sexo");
        userData.addStringProperty("cuentas");
        userData.addBooleanProperty("datosSincronizados");

        Entity phoneData = schema.addEntity("PhoneData");
        phoneData.addIdProperty();
        phoneData.addStringProperty("deviceId");
        phoneData.addStringProperty("subscriberId");
        phoneData.addStringProperty("simSerialNumber");
        phoneData.addStringProperty("line1Number");
        phoneData.addStringProperty("networkOperatorName");
        phoneData.addStringProperty("networkCountryIso");
        phoneData.addIntProperty("SDK_INT");
        phoneData.addStringProperty("MANUFACTURER");
        phoneData.addStringProperty("MODEL");
        phoneData.addBooleanProperty("datosSincronizados");


        new DaoGenerator().generateAll(schema, PROJECT_DIR  + "/app/src/main/java");

    }

}
