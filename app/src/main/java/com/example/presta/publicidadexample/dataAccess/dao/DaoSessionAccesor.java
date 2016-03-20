package com.example.presta.publicidadexample.dataAccess.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.presta.publicidadexample.dataAccess.model.DaoMaster;
import com.example.presta.publicidadexample.dataAccess.model.DaoSession;

/**
 * Created by Presta on 20/03/2016.
 *
 * Retorna un daoSession, siempre el mismo.
 *
 */

public class DaoSessionAccesor {

    private static DaoSession daoSession;

    public static DaoSession GetDaoSession (Context context){
        if(daoSession == null) {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "app-db", null);
            SQLiteDatabase db = helper.getWritableDatabase();
            DaoMaster daoMaster = new DaoMaster(db);
            daoSession = daoMaster.newSession();
        }

        return daoSession;

    }
}

