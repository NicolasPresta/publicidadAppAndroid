package com.example.presta.publicidadexample.dataAccess.dao.Migrations;

import android.database.sqlite.SQLiteDatabase;

import com.example.presta.publicidadexample.dataAccess.dao.Migrations.MigrateV1ToV2;
import com.example.presta.publicidadexample.dataAccess.dao.Migrations.Migration;
import com.example.presta.publicidadexample.dataAccess.dao.Migrations.MigrationImpl;
import com.example.presta.publicidadexample.dataAccess.model.GpsDataDao;

/**
 * Migration from Version2 to Version3
 *
 * @author Jeremy
 */
public class MigrateV2ToV3 extends MigrationImpl {

    /**
     * {@inheritDoc}
     */
    @Override
    public int applyMigration(SQLiteDatabase db,
                              int currentVersion) {
        super.prepareMigration(db, currentVersion);

        // Creamos la tabla de GPS
        GpsDataDao.createTable(db, false);

        return getMigratedVersion();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTargetVersion() {
        return 2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMigratedVersion() {
        return 3;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Migration getPreviousMigration() {
        return new MigrateV1ToV2();
    }
}
