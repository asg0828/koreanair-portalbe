package com.cdp.portal.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import com.cdp.portal.config.datasource.RoutingDataSource;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {
    
    private static final String MASTER_DATASOURCE = "masterDataSource";
    private static final String SLAVE_DATASOURCE = "slaveDataSource";
    
    @Bean(MASTER_DATASOURCE)
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create() 
                .type(HikariDataSource.class) 
                .build();
    }
    
    @Bean(SLAVE_DATASOURCE)
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create() 
                .type(HikariDataSource.class) 
                .build();
    }
    
    @Bean
    @DependsOn({MASTER_DATASOURCE, SLAVE_DATASOURCE})
    public DataSource routingDataSource(@Qualifier(MASTER_DATASOURCE) DataSource masterDataSource, @Qualifier(SLAVE_DATASOURCE) DataSource slaveDataSource) {
        RoutingDataSource routingDataSource = new RoutingDataSource();
        
        Map<Object, Object> datasourceMap = new HashMap<>();
        datasourceMap.put("master", masterDataSource);
        datasourceMap.put("slave", slaveDataSource);
        
        routingDataSource.setTargetDataSources(datasourceMap);
        routingDataSource.setDefaultTargetDataSource(slaveDataSource);
        
        return routingDataSource;
    }
    
    @Bean
    @Primary
    @DependsOn("routingDataSource")
    public DataSource dataSource(DataSource routingDataSource) {
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }

}
