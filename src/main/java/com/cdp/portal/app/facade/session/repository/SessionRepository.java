package com.cdp.portal.app.facade.session.repository;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import com.cdp.portal.app.facade.session.dto.SessionDto;

@Repository
public class SessionRepository {
    @Value(value = "${spring.redis.session-ttl}")
    private int redisSessionTtl;

    @Autowired
    private RedisTemplate<String, SessionDto> redisSessionTemplate;

    @Resource(name = "redisSessionTemplate")
    ValueOperations<String, SessionDto> valueOperations;

    public void createSession(String key, SessionDto session) {
        valueOperations.set(key, session);
        redisSessionTemplate.expire(key, redisSessionTtl, TimeUnit.SECONDS);
    }

    public SessionDto getSession(String key) {
        redisSessionTemplate.expire(key, redisSessionTtl, TimeUnit.SECONDS);
        return valueOperations.get(key);
    }

    public Boolean deleteSession(String key) {
        return redisSessionTemplate.delete(key);
    }
}
