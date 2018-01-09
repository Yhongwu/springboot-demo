package com.howard.datasource.common;

public class DatabaseContextHolder {
    //保存一个线程安全的数据源标识 防止互相干扰
    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();

    public static void setDatabaseType(DatabaseType type){
        contextHolder.set(type);
    }

    public static DatabaseType getDatabaseType() {
        return contextHolder.get();
    }
}
