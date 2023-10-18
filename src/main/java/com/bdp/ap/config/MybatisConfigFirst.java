package com.bdp.ap.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.bdp.ap.common.CommonUtil;
import com.bdp.ap.common.Constant;
import com.bdp.ap.common.annotation.ConnMapperFirst;
import com.bdp.ap.common.aws.AwsSecretsUtil;
import com.bdp.ap.config.props.AwsProps;
import com.bdp.ap.config.props.DbmsProps;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

/**
 * First MyBatis 설정
 */
@Configuration
@MapperScan(value = "com.bdp.ap", annotationClass = ConnMapperFirst.class, sqlSessionFactoryRef = "firstSqlSessionFactory")
@EnableTransactionManagement
@Slf4j
public class MybatisConfigFirst {

	@Resource(name="awsProps")
	private AwsProps awsProps;
	
//	@Resource(name="awsSecretsUtil")
//	private AwsSecretsUtil awsSecretsUtil;
	
	@Resource(name="dbmsProps")
	private DbmsProps dbmsProps;

	@Resource(name="commonUtil")
	private CommonUtil commonUtil;
	
    /**
     * 데이터 소스 셋팅 spring.first.datasource 프로퍼티 정보를 이용
     *
     * @return
     */
    @Bean(name = "firstDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.first.datasource")
    public DataSource firstDataSource() {
    	HikariDataSource hikari = DataSourceBuilder.create().type(HikariDataSource.class).build();
    	// AWS Secret 에서 DB password 가져올 경우
    	//hikari.setPassword(awsSecretsUtil.getValue(awsProps.getDbKeyFirst()));
        return hikari;
    }

    @Bean(name = "firstSqlSessionFactory")
    @Primary
    public SqlSessionFactory firstSqlSessionFactory(@Qualifier("firstDataSource") DataSource firstDataSource, ApplicationContext applicationContext) throws Exception {
        //org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        //configuration.setMapUnderscoreToCamelCase(true);
    	
    	log.debug("firstSqlSessionFactory1 : {}", firstDataSource());
    	log.debug("firstSqlSessionFactory2 : {}", firstDataSource);
    	
    	HikariDataSource hikari = (HikariDataSource) firstDataSource;
    	
    	String jndiName = hikari.getDataSourceJNDI() == null ? "" : hikari.getDataSourceJNDI();
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        if("".equals(jndiName)) {
        	sqlSessionFactoryBean.setDataSource(firstDataSource);
        }else {
        	JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
        	sqlSessionFactoryBean.setDataSource(jndiDataSourceLookup.getDataSource(jndiName));
        }
        //sqlSessionFactoryBean.setConfiguration(configuration);
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis-mapper/first/" + dbmsProps.getDbmsKind() + "/**/*.xml"));
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-mapper/first/config_first.xml"));
        return sqlSessionFactoryBean.getObject();

    }

    @Bean(name = "firstSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate firstSqlSessionTemplate(SqlSessionFactory firstSqlSessionFactory) {
        return new SqlSessionTemplate(firstSqlSessionFactory);
    }
}
