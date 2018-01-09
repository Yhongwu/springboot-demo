package com.howard.datasource.common;

/**
 * 列出所有的数据源key（常用数据库名称来命名）
 * 与数据库的一对一
 * 变量名称就是数据库的名称
 */
public enum DatabaseType {
    datasource1("datasource1"),datasource2("datasource2");
    private String name;

    DatabaseType() {
    }

    DatabaseType(String name) {
        this.name = name;
    }
}
