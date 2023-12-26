package com.cdp.portal.common.error.exception;

public class OneidSysException extends RuntimeException{

    private static final long serialVersionUID = -8986252979297708482L;

    protected String msgCode;

    protected Object[] args;

    public OneidSysException(Throwable cause){super(cause);}

    public OneidSysException(String message){super(message);}

    public OneidSysException(String message, Throwable cause){super(message,cause);}

    public OneidSysException(){super();}

    public OneidSysException(String msgCode, String message) {
        super(message);
        this.msgCode = msgCode;
    }

    public OneidSysException(String msgCode, String message, Throwable cause){
        super(message,cause);
        this.msgCode = msgCode;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    protected OneidSysException(String message, Throwable cause,
                                boolean enableSuppression,
                                boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}