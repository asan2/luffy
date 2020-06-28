package com.jcokee.exception;

import java.io.Serializable;

public class BaseException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -8644605627640136494L;

    protected String errorCode;
    protected String errorMsg;
    protected Throwable caused;

    public BaseException(String errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }

    public BaseException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BaseException(String errorCode, Throwable caused) {
        super(caused);
        this.errorCode = errorCode;
        this.caused = caused;
    }

    public BaseException(String errorCode, String errorMsg, Throwable caused) {
        super(errorMsg, caused);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.caused = caused;
    }

    public String getErrorCode() {
        if (this.errorCode != null && !"".equals(this.errorCode.trim())) {
            return this.errorCode;
        } else if (this.caused != null) {
            if (this.caused instanceof BaseException) {
                BaseException causedException = (BaseException)this.caused;
                return causedException.getErrorCode();
            } else {
                return this.errorCode;
            }
        } else {
            return this.errorCode;
        }
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        if (this.errorMsg != null && !"".equals(this.errorMsg)) {
            return this.errorMsg;
        } else if (this.caused != null) {
            if (this.caused instanceof BaseException) {
                BaseException causedException = (BaseException)this.caused;
                return causedException.getErrorMsg();
            } else {
                return this.errorMsg;
            }
        } else {
            return this.errorMsg;
        }
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Throwable getCaused() {
        return this.caused;
    }

    public void setCaused(Throwable caused) {
        this.caused = caused;
    }
}
