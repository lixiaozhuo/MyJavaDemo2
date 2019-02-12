package com.lixiaozhuo.house.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * JPA配置
 */
//@Configuration
@EnableJpaRepositories(basePackages = "com.lixiaozhuo.house.repository")
@EnableTransactionManagement//开启事务管理
public class JPAConfig {
    //数据源配置
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    //实体类管理工厂
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        //是否生产SQL
        jpaVendorAdapter.setGenerateDdl(false);
        //实体类管理工厂
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new
                LocalContainerEntityManagerFactoryBean();
        //数据源
        entityManagerFactoryBean.setDataSource(dataSource());
        //jpa适配器
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        //实体类包名
        entityManagerFactoryBean.setPackagesToScan("com.lixiaozhuo.house.entity");
        return entityManagerFactoryBean;
    }

    //事务配置
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        //事务管理器
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        //设置实体类
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
