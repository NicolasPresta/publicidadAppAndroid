package com.example.presta.publicidadexample.dataAccess.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.example.presta.publicidadexample.dataAccess.model.GpsData;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "GPS_DATA".
*/
public class GpsDataDao extends AbstractDao<GpsData, Long> {

    public static final String TABLENAME = "GPS_DATA";

    /**
     * Properties of entity GpsData.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Fecha = new Property(1, java.util.Date.class, "fecha", false, "FECHA");
        public final static Property Lat = new Property(2, Double.class, "lat", false, "LAT");
        public final static Property Lon = new Property(3, Double.class, "lon", false, "LON");
        public final static Property Modo = new Property(4, String.class, "modo", false, "MODO");
    };


    public GpsDataDao(DaoConfig config) {
        super(config);
    }
    
    public GpsDataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"GPS_DATA\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"FECHA\" INTEGER," + // 1: fecha
                "\"LAT\" REAL," + // 2: lat
                "\"LON\" REAL," + // 3: lon
                "\"MODO\" TEXT);"); // 4: modo
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"GPS_DATA\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, GpsData entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        java.util.Date fecha = entity.getFecha();
        if (fecha != null) {
            stmt.bindLong(2, fecha.getTime());
        }
 
        Double lat = entity.getLat();
        if (lat != null) {
            stmt.bindDouble(3, lat);
        }
 
        Double lon = entity.getLon();
        if (lon != null) {
            stmt.bindDouble(4, lon);
        }
 
        String modo = entity.getModo();
        if (modo != null) {
            stmt.bindString(5, modo);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public GpsData readEntity(Cursor cursor, int offset) {
        GpsData entity = new GpsData( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : new java.util.Date(cursor.getLong(offset + 1)), // fecha
            cursor.isNull(offset + 2) ? null : cursor.getDouble(offset + 2), // lat
            cursor.isNull(offset + 3) ? null : cursor.getDouble(offset + 3), // lon
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // modo
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, GpsData entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setFecha(cursor.isNull(offset + 1) ? null : new java.util.Date(cursor.getLong(offset + 1)));
        entity.setLat(cursor.isNull(offset + 2) ? null : cursor.getDouble(offset + 2));
        entity.setLon(cursor.isNull(offset + 3) ? null : cursor.getDouble(offset + 3));
        entity.setModo(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(GpsData entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(GpsData entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
