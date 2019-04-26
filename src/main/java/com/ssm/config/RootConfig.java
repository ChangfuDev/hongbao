package com.ssm.config;

import org.apache.tomcat.dbcp.dbcp.BasicDataSourceFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;


import javax.sql.DataSource;
import java.util.Properties;

/**
 * 实现注解式事务
 * <p>
 * Configuration 配置类
 * ComponentScan 定义Spring 扫描的包
 * 实现 TransactionManagementConfigurer 接口,这样可以配置注解驱动事务
 *
 * @author one
 * @date 2019/4/26
 */
@Configuration
@ComponentScan(value = "com.*",
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Service.class})})
@EnableTransactionManagement
public class RootConfig implements TransactionManagementConfigurer {

    private DataSource dataSource = null;

    /**
     * 配置数据库
     *
     * @return 数据库连接池
     */
    @Bean(name = "dataSource")
    public DataSource initDataSource() {
        if (dataSource != null) {
            return dataSource;
        }

        Properties properties = new Properties();
        properties.setProperty("driverClassName", "com.mysql.jdbc.Driver");
        properties.setProperty("url", "jdbc:mysql://localhost:3306/hongbao");
        properties.setProperty("username", "root");
        properties.setProperty("password", "123456");
        properties.setProperty("maxIdle", "20");
        properties.setProperty("maxWait", "30000");

        try {
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    /**
     * 配置 SqlSessionFactoryBean
     *
     * @return SqlSessionFactoryBean
     */
    @Bean(name = "SqlSessionFactory")
    public SqlSessionFactoryBean initSqlSessionFactory() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(initDataSource());

        //配置MyBatis配置文件
        Resource resource = new ClassPathResource("com/ssm/mybatis-config.xml");
        sqlSessionFactoryBean.setConfigLocation(resource);

        return sqlSessionFactoryBean;
    }

    /**
     * 通过自动扫描，发现Mybatis Mapper 接口
     *
     * @return Mapper扫描器
     */
    @Bean
    public MapperScannerConfigurer initMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();

        mapperScannerConfigurer.setBasePackage("com.ssm.dao");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("SqlSessionFactory");
        mapperScannerConfigurer.setAnnotationClass(Repository.class);
        return mapperScannerConfigurer;
    }

    /**
     * 实现接口方法，注册注解事务，当 @Transaction 使用的时候产生数据库事务
     */
    @Override
    @Bean(name = "annotationDrivenTransactionManager")
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();

        transactionManager.setDataSource(initDataSource());
        return transactionManager;
    }
}
