package com.example.presta.publicidadexample.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.example.presta.publicidadexample.model.UserData;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER_DATA".
*/
public class UserDataDao extends AbstractDao<UserData, Long> {

    public static final String TABLENAME = "USER_DATA";

    /**
     * Properties of entity UserData.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property FechaNacimiento = new Property(1, java.util.Date.class, "fechaNacimiento", false, "FECHA_NACIMIENTO");
        public final static Property Sexo = new Property(2, String.class, "sexo", false, "SEXO");
        public final static Property DatosSincronizados = new Property(3, Boolean.class, "datosSincronizados", false, "DATOS_SINCRONIZADOS");
    };


    public UserDataDao(DaoConfig config) {
        super(config);
    }
    
    public UserDataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_DATA\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"FECHA_NACIMIENTO\" INTEGER," + // 1: fechaNacimiento
                "\"SEXO\" TEXT," + // 2: sexo
                "\"DATOS_SINCRONIZADOS\" INTEGER);"); // 3: datosSincronizados
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_DATA\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, UserData entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        java.util.Date fechaNacimiento = entity.getFechaNacimiento();
        if (fechaNacimiento != null) {
            stmt.bindLong(2, fechaNacimiento.getTime());
        }
 
        String sexo = entity.getSexo();
        if (sexo != null) {
            stmt.bindString(3, sexo);
        }
 
        Boolean datosSincronizados = entity.getDatosSincronizados();
        if (datosSincronizados != null) {
            stmt.bindLong(4, datosSincronizados ? 1L: 0L);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public UserData readEntity(Cursor cursor, int offset) {
        UserData entity = new UserData( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : new java.util.Date(cursor.getLong(offset + 1)), // fechaNacimiento
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // sexo
            cursor.isNull(offset + 3) ? null : cursor.getShort(offset + 3) != 0 // datosSincronizados
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, UserData entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setFechaNacimiento(cursor.isNull(offset + 1) ? null : new java.util.Date(cursor.getLong(offset + 1)));
        entity.setSexo(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setDatosSincronizados(cursor.isNull(offset + 3) ? null : cursor.getShort(offset + 3) != 0);
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(UserData entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(UserData entity) {
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
