package com.cdp.portal.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.json.JSONObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.model.AWSSecretsManagerException;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.cdp.portal.config.datasource.RoutingDataSource;
import com.zaxxer.hikari.HikariDataSource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DataSourceConfig {

	private final AWSSecretsManager secretsManagerClient;
	private String dbPassword;
	private String dbPasswordOneid;

	@Value("${spring.config.activate.on-profile}")
    private String profile;

	@Value("${local.database.password.master-password}")
    private String localDbMasterPassword;

	@Value("${local.database.password.slave-password}")
	private String localDbSlavePassword;

	@Value("${local.database.password.oneid-password}")
	private String localDbOneidPassword;

    @Value("${cloud.aws.secrets-manager.db-password-arn}")
    String dbPasswordSecretManagerArn;

    @Value("${cloud.aws.secrets-manager.db-password-arn-oneid}")
    String dbPasswordSecretManagerArnOneid;

    private static final String MASTER_DATASOURCE = "masterDataSource";
    private static final String SLAVE_DATASOURCE = "slaveDataSource";
    private static final String ROUTING_DATASOURCE = "routingDataSource";

    @Bean(MASTER_DATASOURCE)
    @ConfigurationProperties(prefix = "spring.datasource.master")
    DataSource masterDataSource() {
    	var cdpMasterDataSource = DataSourceBuilder.create().type(HikariDataSource.class).build();
    	if ("local".equals(profile)) {
            cdpMasterDataSource.setPassword(localDbMasterPassword);
        } else {
            if (dbPassword == null) {
                getSecretsManagerDbPassword();
            }
            cdpMasterDataSource.setPassword(dbPassword);
        }

        return cdpMasterDataSource;
    }

    @Bean(SLAVE_DATASOURCE)
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    DataSource slaveDataSource() {
    	var cdpSlaveDataSource = DataSourceBuilder.create().type(HikariDataSource.class).build();
    	if ("local".equals(profile)) {
    		cdpSlaveDataSource.setPassword(localDbSlavePassword);
        } else {
            if (dbPassword == null) {
                getSecretsManagerDbPassword();
            }
            cdpSlaveDataSource.setPassword(dbPassword);
        }

        return cdpSlaveDataSource;
    }

    @Bean
    @DependsOn({MASTER_DATASOURCE, SLAVE_DATASOURCE})
    DataSource routingDataSource(@Qualifier(MASTER_DATASOURCE) DataSource masterDataSource, @Qualifier(SLAVE_DATASOURCE) DataSource slaveDataSource) {
        RoutingDataSource routingDataSource = new RoutingDataSource();

        Map<Object, Object> datasourceMap = new HashMap<>();
        datasourceMap.put("master", masterDataSource);
        datasourceMap.put("slave", slaveDataSource);

        routingDataSource.setTargetDataSources(datasourceMap);
        routingDataSource.setDefaultTargetDataSource(masterDataSource);

        return routingDataSource;
    }

    @Bean
    @Primary
    @DependsOn("routingDataSource")
    @Qualifier(ROUTING_DATASOURCE)
    DataSource dataSource(DataSource routingDataSource) {
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.oneid")
    DataSource oneidDataSource() {
    	var oneidDataSource = DataSourceBuilder.create().type(HikariDataSource.class).build();
    	if ("local".equals(profile)) {
    		oneidDataSource.setPassword(localDbOneidPassword);
        } else {
            if (dbPasswordOneid == null) {
            	getSecretsManagerDbPasswordOneid();
            }
            oneidDataSource.setPassword(dbPasswordOneid);
        }

        return oneidDataSource;
    }

    public void getSecretsManagerDbPassword() {
        try {
            var getSecretValueRequest = new GetSecretValueRequest()
                    .withSecretId(dbPasswordSecretManagerArn);
            GetSecretValueResult getSecretValueResult = null;
            getSecretValueResult = secretsManagerClient.getSecretValue(getSecretValueRequest);

            if (getSecretValueResult.getSecretString() != null) {
                var secret = getSecretValueResult.getSecretString();

                var jObject = new JSONObject(secret);
                this.dbPassword = jObject.getString("password");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new AWSSecretsManagerException("SecretManager Exception Occured : " + e.getMessage());
        }
    }

    public void getSecretsManagerDbPasswordOneid() {
    	try {
    		var getSecretValueRequest = new GetSecretValueRequest()
    				.withSecretId(dbPasswordSecretManagerArnOneid);
    		GetSecretValueResult getSecretValueResult = null;
    		getSecretValueResult = secretsManagerClient.getSecretValue(getSecretValueRequest);

    		if (getSecretValueResult.getSecretString() != null) {
    			var secret = getSecretValueResult.getSecretString();

    			var jObject = new JSONObject(secret);
    			this.dbPasswordOneid = jObject.getString("Password");
    		}
    	} catch (Exception e) {
    		log.error(e.getMessage());
    		throw new AWSSecretsManagerException("SecretManager Exception Occured : " + e.getMessage());
    	}
    }

}
