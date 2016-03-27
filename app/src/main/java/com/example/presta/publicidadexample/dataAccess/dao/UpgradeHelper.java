package com.example.presta.publicidadexample.dataAccess.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.presta.publicidadexample.dataAccess.dao.Migrations.MigrateV1ToV2;
import com.example.presta.publicidadexample.dataAccess.dao.Migrations.MigrateV2ToV3;
import com.example.presta.publicidadexample.dataAccess.model.DaoMaster;

/**
 * A simple helper which determines which migration (if any) is required to be
 * applied when a database is opened.
 *
 * @author Jeremy
 */
public class UpgradeHelper extends DaoMaster.OpenHelper {

    public UpgradeHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    /**
     * Apply the appropriate migrations to update the database.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (newVersion) {
            case 2:
                new MigrateV1ToV2().applyMigration(db, oldVersion);
                break;
            case 3:
                new MigrateV2ToV3().applyMigration(db, oldVersion);
                break;
            /*
             case 4:
                new MigrateV3ToV4().applyMigration(db, oldVersion);
                break;
             */
            default:
                return;
        }
    }
}