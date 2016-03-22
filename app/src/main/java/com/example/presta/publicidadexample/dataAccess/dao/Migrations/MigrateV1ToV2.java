package com.example.presta.publicidadexample.dataAccess.dao.Migrations;

import android.database.sqlite.SQLiteDatabase;

import com.example.presta.publicidadexample.dataAccess.dao.Migrations.Migration;
import com.example.presta.publicidadexample.dataAccess.dao.Migrations.MigrationImpl;

/**
 * Migration from Version1 to Version2
 *
 * @author Jeremy
 */
public class MigrateV1ToV2 extends MigrationImpl {

    /**
     * {@inheritDoc}
     */
    @Override
    public int applyMigration(SQLiteDatabase db,
                              int currentVersion) {
        prepareMigration(db, currentVersion);

       // db.execSQL("ALTER TABLE USER_DATA ADD COLUMN CAMPO_DE_PRUEBA TEXT NULL ");

        return getMigratedVersion();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTargetVersion() {
        return 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMigratedVersion() {
        return 2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Migration getPreviousMigration() {
        return null;
    }
}
