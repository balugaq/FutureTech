package net.bxx2004.pandalib.java.sql.sqlite;

import net.bxx2004.pandalib.java.sql.SQL;
import net.bxx2004.pandalib.java.sql.SQLBase;
import net.bxx2004.pandalib.java.sql.SQLData;
import net.bxx2004.pandalib.java.sql.SQLDataTerm;
import net.bxx2004.pandalib.java.sql.SQLPlatForm;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SQLiteBase implements SQLBase {
    private SQLiteConnection connection;
    private String tableName;

    public SQLiteBase(SQLiteConnection connection, String tableName) {
        this.tableName = tableName;
        this.connection = connection;
    }

    public boolean hasData(SQLData data) {
        SQLiteData mySQLData = (SQLiteData) data;
        String sql = "select * from " + tableName + " where " + mySQLData.row + "='" + mySQLData.valueAsString() + "' ";
        ResultSet rs = runQuery(sql);
        try {
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @SQL(platform = SQLPlatForm.SQLITE, type = Type.INSERT)
    public void insert(SQLData... data) {
        String namesql = "";
        String valueSize = "";
        for (SQLData arg : data) {
            SQLiteData mySQLData = (SQLiteData) arg;
            namesql += mySQLData.row + ", ";
            valueSize += "'" + mySQLData.valueAsString() + "',";
        }
        namesql = namesql.substring(0, namesql.length() - 2);
        valueSize = valueSize.substring(0, valueSize.length() - 1);
        String sql = "insert into " + tableName + "(" + namesql + ") values(" + valueSize + ")";
        run(sql);
    }

    @Override
    @SQL(platform = SQLPlatForm.SQLITE, type = Type.DELETE)
    public void delete(SQLDataTerm... data) {
        String terms = "";
        for (SQLDataTerm arg : data) {
            SQLiteDataTerm dataTerm = (SQLiteDataTerm) arg;
            terms += dataTerm.rowName + "='" + dataTerm.value + "' and ";
        }
        terms = terms.substring(0, terms.length() - 4);
        String sql = "delete from " + tableName + " where " + terms;
        run(sql);
    }

    @Override
    @SQL(platform = SQLPlatForm.SQLITE, type = Type.DELETE)
    public void delete(SQLData... data) {
        for (SQLData arg : data) {
            String terms = "";
            SQLiteData mySQLData = (SQLiteData) arg;
            SQLiteDataTerm[] term = mySQLData.terms;
            for (SQLiteDataTerm t : term) {
                terms += t.rowName + "='" + t.value + "' and ";
            }
            terms = terms.substring(0, terms.length() - 4);
            String sql = "delete from " + tableName + " where " + terms;
            run(sql);
        }
    }

    @Override
    @SQL(platform = SQLPlatForm.SQLITE, type = Type.UPDATE)
    public void update(SQLData... data) {
        String terms = "";
        for (SQLData arg : data) {
            SQLiteData data1 = (SQLiteData) arg;
            SQLiteDataTerm[] term = data1.terms;
            for (SQLiteDataTerm t : term) {
                terms += t.rowName + "='" + t.value + "' and ";
            }
            terms = terms.substring(0, terms.length() - 4);
            String sql = "update " + tableName + " set " + data1.row + "='" + data1.value + "' where " + terms;
            run(sql);
        }
    }

    @Override
    @SQL(platform = SQLPlatForm.SQLITE, type = Type.SELECT)
    public List<HashMap> select(SQLDataTerm... data) {
        try {
            String terms = "";
            for (SQLDataTerm ta : data) {
                SQLiteDataTerm t = (SQLiteDataTerm) ta;
                terms += t.rowName + "='" + t.value + "' and ";
            }
            terms = terms.substring(0, terms.length() - 4);
            String sql;
            if (data.length == 0) {
                sql = "select * from " + tableName;
            } else {
                sql = "select * from " + tableName + " where " + terms;
            }
            ResultSet rs = runQuery(sql);
            return asList(rs);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void run(String sql) {
        try {
            Statement statement = this.connection.getConnection().createStatement();
            statement.execute(sql);
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ResultSet runQuery(String sql) {
        try {
            return connection.getConnection().createStatement().executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<HashMap> asList(ResultSet rs) {
        try {
            List<HashMap> list = new ArrayList();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()) {
                HashMap rowData = new HashMap();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                list.add(rowData);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
