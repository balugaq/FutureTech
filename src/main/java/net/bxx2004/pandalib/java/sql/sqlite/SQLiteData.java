package net.bxx2004.pandalib.java.sql.sqlite;

import net.bxx2004.pandalib.java.sql.SQLData;

public class SQLiteData implements SQLData {
    public SQLiteData.SQLiteDataType type;
    public Object value;
    public long addData;
    public String name;
    public String row;
    public SQLiteDataTerm[] terms;

    /**
     * 新建一个MySQL数据类
     *
     * @param type  数据类型
     * @param value 值
     */
    public SQLiteData(SQLiteData.SQLiteDataType type, Object value) {
        this.type = type;
        this.value = value;
    }

    public SQLiteData() {
    }

    /**
     * 新建一个无值的MySQL数据类(用于建表)
     *
     * @param type    类型
     * @param addData 附加值
     * @param name    命名
     */
    public SQLiteData(SQLiteData.SQLiteDataType type, String name, long addData) {
        this.type = type;
        this.name = name;
        this.addData = addData;
    }

    /**
     * 新建一个无值的MySQL数据类(用于建表)
     *
     * @param type 类型
     * @param name 命名
     */
    public SQLiteData(SQLiteData.SQLiteDataType type, String name) {
        this.type = type;
        this.name = name;
    }

    /**
     * 新建一个无值的MySQL数据类(用于插入)
     *
     * @param rowName 列名
     * @param value   值
     */
    public SQLiteData(String rowName, Object value) {
        this.value = value;
        this.row = rowName;
    }

    /**
     * 新建一个无值的MySQL数据类(用于删除,查询)
     *
     * @param term 条件
     */
    public SQLiteData(SQLiteDataTerm... term) {
        this.terms = term;
    }

    /**
     * 新建一个无值的MySQL数据类(用于修改)
     *
     * @param rowName 列名
     * @param value   值
     * @param term    条件
     */
    public SQLiteData(String rowName, Object value, SQLiteDataTerm... term) {
        this.row = rowName;
        this.value = value;
        this.terms = term;
    }

    /**
     * 构造一个SQLiteData用于插入
     *
     * @param rowName 列名
     * @param value   值
     * @return 构造一个SQLiteData用于插入
     */
    public static SQLiteData getInsert(String rowName, Object value) {
        return new SQLiteData(rowName, value);
    }

    /**
     * 构造一个SQLiteData用于删除
     *
     * @param terms 条件
     * @return 构造一个SQLiteData用于删除
     */
    public static SQLiteData getDelete(SQLiteDataTerm... terms) {
        return new SQLiteData(terms);
    }

    /**
     * 构造一个SQLiteData用于查询
     *
     * @param terms 条件
     * @return 构造一个SQLiteData用于查询
     */
    public static SQLiteData getSelect(SQLiteDataTerm... terms) {
        return new SQLiteData(terms);
    }

    /**
     * 构造一个SQLiteData用于修改
     *
     * @param rowName 列名
     * @param value   值
     * @param terms   条件
     * @return 构造一个SQLiteData用于修改
     */
    public static SQLiteData getUpdate(String rowName, Object value, SQLiteDataTerm... terms) {
        return new SQLiteData(rowName, value, terms);
    }

    /**
     * 构造一个SQLiteData用于建表
     *
     * @param type    数据类型
     * @param name    列名
     * @param addData 附加值
     * @return 构造一个SQLiteData用于建表
     */
    public static SQLiteData getTable(SQLiteData.SQLiteDataType type, String name, long addData) {
        return new SQLiteData(type, name, addData);
    }

    /**
     * 修改该数据值
     *
     * @param value 值
     */
    public void alter(Object value) {
        this.value = value;
    }

    /**
     * 获取值
     *
     * @return 值
     */
    public String valueAsString() {
        return value.toString();
    }

    /**
     * 获取MySQL的写法
     *
     * @return MySQL的写法
     */
    public String getMySQLLanguage() {
        if (this.addData != 0) {
            if ((name != null) && (!name.isEmpty())) {
                return this.type.name() + "(" + addData + ") " + name;
            } else {
                return this.type.name() + "(" + addData + ")";
            }
        } else {
            if ((name != null) && (!name.isEmpty())) {
                return this.type.name() + " " + name;
            } else {
                return this.type.name();
            }
        }
    }

    /**
     * MySQL数据类型枚举
     */
    public enum SQLiteDataType {
        TINYINT,
        SMALLINT,
        MEDIUMINT,
        INT,
        INTEGER,
        BIGINT,
        FLOAT,
        DOUBLE,
        DECIMAL,
        DATE,
        TIME,
        YEAR,
        DATETIME,
        TIMESTAMP,
        CHAR,
        VARCHAR,
        TINYBLOB,
        TINYTEXT,
        BLOB,
        TEXT,
        MEDIUMBLOB,
        MEDIUMTEXT,
        LONGBLOB,
        LONGTEXT
    }
}
