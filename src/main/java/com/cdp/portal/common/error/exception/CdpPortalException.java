package com.cdp.portal.common.error.exception;

import com.cdp.portal.common.enumeration.CdpPortalError;

import lombok.Getter;

@Getter
public class CdpPortalException extends RuntimeException {

    private static final long serialVersionUID = -6960786931953013380L;
    
    private final CdpPortalError error;
    private final String message;
    
    public CdpPortalException(CdpPortalError error, String... args) {
        super(error.getMessage());
        this.error = error;
        this.message = error.format(args);
    }

}
