package com.example;

import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Version 1 of the Schema definition
 *
 * @author Jeremy
 */
public class Version2 extends SchemaVersion {

    /**
     * Constructor
     *
     * @param current
     */
    public Version2(boolean current) {
        super(current);

        Schema schema = getSchema();

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
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getVersionNumber() {
        return 2;
    }

}
