package com.jcokee.exception;

public class BusinessException extends BaseException {

    /**
     * 异常错误代码，使用4位字符串， 第一位代表产生异常的系统代码 后三位代表具体的错误代码含义 错误代码由具体的常量定义
     */
    protected String errorCode;

    /** 异常错误信息，由实际抛出异常的类定义 */
    protected String errorMsg;

    /** 根异常，保持异常链 */
    protected Throwable caused;

    public BusinessException(String errorCode) {
        super(errorCode);
    }

    public BusinessException(String errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public BusinessException(String errorCode, Throwable caused) {
        super(errorCode, caused);
    }

    public BusinessException(String errorCode, String errorMsg, Throwable caused) {
        super(errorCode, errorMsg, caused);
    }
}
