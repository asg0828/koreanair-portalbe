package com.bdp.ap.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.jdbc.config.annotation.SpringSessionDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.bdp.ap.common.annotation.ConnMapperSecond;
import com.bdp.ap.common.aws.AwsSecretsUtil;
import com.bdp.ap.config.props.AwsProps;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Second MyBatis 설정
 */
@Configuration
@MapperScan(value = "com.bdp.ap", annotationClass = ConnMapperSecond.class, sqlSessionFactoryRef = "secondSqlSessionFactory")
@EnableTransactionManagement
public class MybatisConfigSecond {

	@Resource(name="awsProps")
	private AwsProps awsProps;
	
//	@Resource(name="awsSecretsUtil")
//	private AwsSecretsUtil awsSecretsUtil;
	
    @Bean(name = "secondDataSource", destroyMethod = "close")
    @ConfigurationProperties(prefix = "spring.second.datasource")
    @SpringSessionDataSource
    public HikariDataSource secondDataSource() {
    	HikariDataSource hikari = DataSourceBuilder.create().type(HikariDataSource.class).build();
    	// AWS Secret 에서 DB password 가져올 경우
    	//hikari.setPassword(awsSecretsUtil.getValue(awsProps.getDbKeySecond()));
        return hikari;
    }

    @Bean(name = "secondSqlSessionFactory")
    public SqlSessionFactory secondSqlSessionFactory(@Qualifier("secondDataSource") DataSource secondDataSource, ApplicationContext applicationContext) throws Exception {

        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(secondDataSource);
        //sqlSessionFactoryBean.setConfiguration(configuration);
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath*:mybatis-mapper/second/*.xml"));
        //sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-mapper/config_second.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "secondSqlSessionTemplate")
    public SqlSessionTemplate secondSqlSessionTemplate(SqlSessionFactory secondSqlSessionFactory) {
        return new SqlSessionTemplate(secondSqlSessionFactory);
    }
}
