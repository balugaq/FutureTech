package net.bxx2004.pandalib.java.sql.sqlite;

import net.bxx2004.pandalib.java.sql.SQLDataTerm;

public class SQLiteDataTerm implements SQLDataTerm {
    public String rowName;
    public Object value;

    public SQLiteDataTerm(String rowName, Object value) {
        this.rowName = rowName;
        this.value = value;
    }
}
