package com.cdp.portal.config;

import java.io.FileNotFoundException;

import javax.sql.DataSource;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
@MapperScan(basePackages="com.cdp.**.mapper", annotationClass = Mapper.class, sqlSessionFactoryRef = "cdpSqlSessionFactory")
public class CdpMybatisConfig {

	private final ApplicationContext applicationContext;

	@Bean
	SqlSessionFactory cdpSqlSessionFactory(@Qualifier("routingDataSource") DataSource dataSource) throws Exception {
		Resource[] resources = null;

		try {
			resources = new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*.xml");
		} catch(FileNotFoundException e) {
			log.debug(">> resources(*Maper.xml) does not exist.");
			return null;
		}

		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setVfs(SpringBootVFS.class);
		sessionFactory.setConfigLocation(applicationContext.getResource("classpath:mybatis/mybatis-config.xml"));
		sessionFactory.setMapperLocations(resources);

		return sessionFactory.getObject();
	}

	@Bean
	SqlSessionTemplate cdpSqlSessionTemplate(@Qualifier("cdpSqlSessionFactory") SqlSessionFactory cdpSqlSessionFactory) {
		if(cdpSqlSessionFactory == null) {
			log.debug(">> cdpSqlSessionFactory is null");
			return null;
		}

		return new SqlSessionTemplate(cdpSqlSessionFactory);
	}


}