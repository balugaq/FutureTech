package net.bxx2004.pandalib.java.sql.mysql;

import net.bxx2004.pandalib.java.sql.SQLDataTerm;

/**
 * MySQL所需条件类
 */
public class MySQLDataTerm implements SQLDataTerm {
    public String rowName;
    public Object value;

    public MySQLDataTerm(String rowName, Object value) {
        this.rowName = rowName;
        this.value = value;
    }
}