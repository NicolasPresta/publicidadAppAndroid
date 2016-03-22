package com.example;

import de.greenrobot.daogenerator.Schema;

/**
 * A Version of the Schema.
 *
 * @author Jeremy
 */
public abstract class SchemaVersion {

    // TODO #1 Update this to a package definition which makes sense for your project
    public static final String CURRENT_SCHEMA_PACKAGE = "com.example.presta.publicidadexample.dataAccess.model";

    private final Schema schema;

    private final boolean current;

    /**
     * Constructor
     *
     * @param current indicating if this is the current schema.
     */
    public SchemaVersion(boolean current) {
        int version = getVersionNumber();
        String packageName = CURRENT_SCHEMA_PACKAGE;
        if (!current) {
            packageName += ".v" + version;
        }
        this.schema = new Schema(version, packageName);
        this.schema.enableKeepSectionsByDefault();
        this.current = current;
    }

    /**
     * @return the GreenDAO schema.
     */
    protected Schema getSchema() {
        return schema;
    }

    /**
     * @return boolean indicating if this is the highest or current schema version.
     */
    public boolean isCurrent() {
        return current;
    }

    /**
     * @return unique integer schema version identifier.
     */
    public abstract int getVersionNumber();
}