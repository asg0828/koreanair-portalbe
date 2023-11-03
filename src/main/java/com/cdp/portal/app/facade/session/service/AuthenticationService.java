package com.cdp.portal.app.facade.session.service;

import org.springframework.stereotype.Service;

import com.cdp.portal.app.facade.session.dto.SessionDto;
import com.cdp.portal.app.facade.session.repository.SessionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final SessionRepository sessionRepository;

    public SessionDto getSession(String sessionId) {
        return sessionRepository.getSession(sessionId);
    }
}
