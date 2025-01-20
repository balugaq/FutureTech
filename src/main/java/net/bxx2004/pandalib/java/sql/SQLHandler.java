package net.bxx2004.pandalib.java.sql;

/**
 * SQL操作实现
 */
public interface SQLHandler {
    /**
     * 建立SQL连接
     */
    public void set(SQLConnection connection);

    /**
     * 建表
     */
    public void createTable(String name, boolean primary_key_ID, SQLData... data);

    /**
     * 删表
     */
    public void deleteTable(String name);

    /**
     * 表是否存在
     *
     * @param tableName 表名
     * @return 是否存在
     */
    public boolean exist(String tableName);
}
