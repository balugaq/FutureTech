package net.bxx2004.pandalib.java.sql.mysql;

import net.bxx2004.pandalib.java.sql.SQLData;

/**
 * MySQL数据库的数据类型
 */
public class MySQLData implements SQLData {
    public MySQLDataType type;
    public Object value;
    public long addData;
    public String name;
    public String row;
    public MySQLDataTerm[] terms;

    /**
     * 新建一个MySQL数据类
     *
     * @param type  数据类型
     * @param value 值
     */
    public MySQLData(MySQLDataType type, Object value) {
        this.type = type;
        this.value = value;
    }

    public MySQLData() {
    }

    ;

    /**
     * 新建一个无值的MySQL数据类(用于建表)
     *
     * @param type    类型
     * @param addData 附加值
     * @param name    命名
     */
    public MySQLData(MySQLDataType type, String name, long addData) {
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
    public MySQLData(MySQLDataType type, String name) {
        this.type = type;
        this.name = name;
    }

    /**
     * 新建一个无值的MySQL数据类(用于插入)
     *
     * @param rowName 列名
     * @param value   值
     */
    public MySQLData(String rowName, Object value) {
        this.value = value;
        this.row = rowName;
    }

    /**
     * 新建一个无值的MySQL数据类(用于删除,查询)
     *
     * @param term 条件
     */
    public MySQLData(MySQLDataTerm... term) {
        this.terms = term;
    }

    /**
     * 新建一个无值的MySQL数据类(用于修改)
     *
     * @param rowName 列名
     * @param value   值
     * @param term    条件
     */
    public MySQLData(String rowName, Object value, MySQLDataTerm... term) {
        this.row = rowName;
        this.value = value;
        this.terms = term;
    }

    /**
     * 构造一个MySQLData用于插入
     *
     * @param rowName 列名
     * @param value   值
     * @return 构造一个MySQLData用于插入
     */
    public static MySQLData getInsert(String rowName, Object value) {
        return new MySQLData(rowName, value);
    }

    /**
     * 构造一个MySQLData用于删除
     *
     * @param terms 条件
     * @return 构造一个MySQLData用于删除
     */
    public static MySQLData getDelete(MySQLDataTerm... terms) {
        return new MySQLData(terms);
    }

    /**
     * 构造一个MySQLData用于查询
     *
     * @param terms 条件
     * @return 构造一个MySQLData用于查询
     */
    public static MySQLData getSelect(MySQLDataTerm... terms) {
        return new MySQLData(terms);
    }

    /**
     * 构造一个MySQLData用于修改
     *
     * @param rowName 列名
     * @param value   值
     * @param terms   条件
     * @return 构造一个MySQLData用于修改
     */
    public static MySQLData getUpdate(String rowName, Object value, MySQLDataTerm... terms) {
        return new MySQLData(rowName, value, terms);
    }

    /**
     * 构造一个MySQLData用于建表
     *
     * @param type    数据类型
     * @param name    列名
     * @param addData 附加值
     * @return 构造一个MySQLData用于建表
     */
    public static MySQLData getTable(MySQLDataType type, String name, long addData) {
        return new MySQLData(type, name, addData);
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
    public enum MySQLDataType {
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
