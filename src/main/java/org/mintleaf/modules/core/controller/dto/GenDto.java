package org.mintleaf.modules.core.controller.dto;

/**
 * @Author: MengchuZhang
 * @Date: 2018/9/5 8:06
 * @Version 1.0
 */
public class GenDto {
    /*
    数据源名称
    */
    private String dataSource;
    /*
    表名
    */
    private String tableName;
    /*
    sql文件存放路径
    */
    private String sqlPath;
    /*
    实体存放路径
    */
    private String entityPath;
    /*
   数据库类型
   */
    private String dbType;



    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getSqlPath() {
        return sqlPath;
    }

    public void setSqlPath(String sqlPath) {
        this.sqlPath = sqlPath;
    }

    public String getEntityPath() {
        return entityPath;
    }

    public void setEntityPath(String entityPath) {
        this.entityPath = entityPath;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }
}
