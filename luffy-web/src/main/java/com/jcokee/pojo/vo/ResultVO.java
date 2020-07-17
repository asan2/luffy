package com.jcokee.pojo.vo;

import java.io.Serializable;

public class ResultVO<T> implements Serializable {
    private static final long serialVersionUID = -6041301804479388563L;

    private static final String RESULT_SUCCESS = "success";

    private ResultVO.Status status;
    private String message;
    private T result;
    private Integer code;

    public ResultVO (T result) {
        this.status = Status.OK;
        this.result = result;
    }

    public ResultVO(ResultVO.Status status, T result) {
        this.status = status;
        this.result = result;
    }

    public ResultVO(ResultVO.Status status, String message, Integer code) {
        this.status = status;
        this.message = message;
        this.code = code;
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

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public static enum Status {
        OK,
        ERROR;
        private Status() {}
    }
}
