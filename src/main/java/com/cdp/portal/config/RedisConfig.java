package com.cdp.portal.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.cdp.portal.app.facade.session.dto.SessionDto;

@Configuration
@EnableRedisRepositories
public class RedisConfig {
	@Value("${spring.redis.host}")
	private String redisHost;

	@Value("${spring.redis.port}")
	private int redisPort;

	@Value("${spring.profiles.active}")
	private String activeProfile;

	@Bean
	public LettuceConnectionFactory connectionFactory() {

		if ("default".equals(activeProfile)) {
			return new LettuceConnectionFactory(new RedisStandaloneConfiguration(redisHost, redisPort));
		} else {
			List<String> clusterNodes = new ArrayList<>();
			clusterNodes.add(redisHost + ":" + redisPort);
			RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(clusterNodes);
			return new LettuceConnectionFactory(redisClusterConfiguration);
		}

	}

	@Bean
	public RedisTemplate<String, SessionDto> redisSessionTemplate() {
		RedisTemplate<String, SessionDto> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(this.connectionFactory());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(SessionDto.class));
		return redisTemplate;
	}
}
