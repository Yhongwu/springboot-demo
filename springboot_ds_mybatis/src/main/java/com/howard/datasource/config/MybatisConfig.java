package com.howard.datasource.config;

import com.howard.datasource.common.DatabaseType;
import com.howard.datasource.common.DynamicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import sun.text.resources.cldr.bs.FormatData_bs;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@MapperScan("com.howard.datasource.mapper") //扫描
public class MybatisConfig {

    @Autowired
    private Environment env;

    /**
     * 分别配置两个数据源
     * @bean 默认名称就类名
     * @return
     * @throws Exception
     */
    @Bean("dataSource1")
    public DataSource dataSource1() throws Exception {
        Properties properties = new Properties();
        properties.put("driverClassName", env.getProperty("jdbc1.driverClassName"));
        properties.put("url", env.getProperty("jdbc1.url"));
        properties.put("username", env.getProperty("jdbc1.username"));
        properties.put("password", env.getProperty("jdbc1.password"));
        return DruidDataSourceFactory.createDataSource(properties);
    }

    @Bean("dataSource2")
    public DataSource dataSource2() throws Exception {
        Properties properties = new Properties();
        properties.put("driverClassName", env.getProperty("jdbc2.driverClassName"));
        properties.put("url", env.getProperty("jdbc2.url"));
        properties.put("username", env.getProperty("jdbc2.username"));
        properties.put("password", env.getProperty("jdbc2.password"));
        return DruidDataSourceFactory.createDataSource(properties);
    }

    /**
     * 配置数据源
     * 设置默认数据源
     * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个
     * @return
     */
    @Bean
    @Primary
    public DynamicDataSource dynamicDataSource() throws Exception {
        Map<Object,Object> targetDatasources = new HashMap<>();
        targetDatasources.put(DatabaseType.datasource1,dataSource1());
        targetDatasources.put(DatabaseType.datasource2,dataSource2());

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        //AbstractRoutingDataSource的方法 还类最终判断使用哪个数据源标识从而去使用具体哪个数据源(determineCurrentLookupKey)
        dynamicDataSource.setTargetDataSources(targetDatasources);
        //设置默认数据源
        dynamicDataSource.setDefaultTargetDataSource(dataSource1());

        return dynamicDataSource;
    }

    /**
     * 配置SqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();
        sfb.setDataSource(dynamicDataSource());

        //xml的路径
        sfb.setTypeAliasesPackage(env.getProperty("mybatis.type-aliases-package"));
        //配置mapper.xml路径
        sfb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapper-locations")));

        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        return sfb.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DynamicDataSource dynamicDataSource) {
        return new DataSourceTransactionManager(dynamicDataSource);
    }
}
