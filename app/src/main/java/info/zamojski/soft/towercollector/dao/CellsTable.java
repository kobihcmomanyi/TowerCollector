/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package info.zamojski.soft.towercollector.dao;

final class CellsTable implements ITable {

    static final String TABLE_NAME = "cells";
    static final String COLUMN_ROW_ID = "row_id";
    static final String COLUMN_MCC = "mcc";
    static final String COLUMN_MNC = "mnc";
    static final String COLUMN_LAC = "lac";
    static final String COLUMN_CID = "cid";
    static final String COLUMN_NET_TYPE = "net_type";
    static final String COLUMN_DISCOVERED_AT = "discovered_at";

    private static final String QUERY_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + COLUMN_ROW_ID + " INTEGER PRIMARY KEY NOT NULL, "
            + COLUMN_MCC + " INTEGER NOT NULL, "
            + COLUMN_MNC + " INTEGER NOT NULL, "
            + COLUMN_LAC + " INTEGER NOT NULL, "
            + COLUMN_CID + " INTEGER NOT NULL, "
            + COLUMN_NET_TYPE + " INTEGER NOT NULL, "
            + COLUMN_DISCOVERED_AT + " INTEGER NOT NULL, "
            + "UNIQUE (" + COLUMN_CID + ", " + COLUMN_LAC + ", " + COLUMN_MNC + ", " + COLUMN_MCC + ", " + COLUMN_NET_TYPE + ") ON CONFLICT IGNORE)";

    private static final String QUERY_CREATE_TRIGGER_ON_DELETE = "CREATE TRIGGER 'archive_cell' BEFORE DELETE ON " + TABLE_NAME + " BEGIN INSERT INTO "
            + CellsArchiveTable.TABLE_NAME + " (" + CellsArchiveTable.COLUMN_MCC + ", " + CellsArchiveTable.COLUMN_MNC + ", "
            + CellsArchiveTable.COLUMN_LAC + ", " + CellsArchiveTable.COLUMN_CID + ", " + CellsArchiveTable.COLUMN_NET_TYPE + ", "
            + CellsArchiveTable.COLUMN_DISCOVERED_AT + ") VALUES (old." + COLUMN_MCC + ", old." + COLUMN_MNC
            + ", old." + COLUMN_LAC + ", old." + COLUMN_CID + ", old." + COLUMN_NET_TYPE + ", old." + COLUMN_DISCOVERED_AT + "); END";

    @Override
    public String[] getCreateQueries() {
        return new String[]{
                QUERY_CREATE_TABLE,
                QUERY_CREATE_TRIGGER_ON_DELETE
        };
    }

}
