package com.jcokee.pojo.vo;

import java.io.Serializable;

public class ResultVO<T> implements Serializable {
    private static final long serialVersionUID = -6041301804479388563L;

    private ResultVO.Status status;
    private String message;
    private T result;
    private Integer errorCode;

    public ResultVO(ResultVO.Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResultVO(ResultVO.Status status, T result) {
        this.status = status;
        this.result = result;
    }

    public ResultVO(ResultVO.Status status, String message, Integer errorCode) {
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
    }

    public boolean isOk() {
        return this.status == ResultVO.Status.OK;
    }

    public ResultVO.Status getStatus() {
        return this.status;
    }

    public void setStatus(ResultVO.Status status) {
        this.status = status;
    }

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public static enum Status {
        OK,
        ERROR;
        private Status() {}
    }
}
