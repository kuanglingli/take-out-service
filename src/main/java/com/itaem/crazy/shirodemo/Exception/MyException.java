package com.itaem.crazy.shirodemo.Exception;

import lombok.Data;

public class MyException extends Exception{

    private String errorCode;
    private String provinceCode;

    public MyException(String errorCode) {
        this.errorCode = errorCode;
    }

    public MyException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public MyException(String errorCode, Throwable cause) {
        this(errorCode, errorCode, cause);
    }

    public MyException(String errorCode, String message, String provinceCode) {
        super(message);
        this.errorCode = errorCode;
        this.provinceCode = provinceCode;
    }

    public MyException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public MyException(String errorCode, String message, String provinceCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.provinceCode = provinceCode;
    }

    public String getProvinceCode() {
        return this.provinceCode != null && !"".equalsIgnoreCase(this.provinceCode) ? this.provinceCode : "000";
    }

    public String getErrorCode() {
        return this.errorCode != null && !"".equalsIgnoreCase(this.errorCode) ? this.errorCode : "000000";
    }

    @Override
    public String getMessage() {
        String message = super.getMessage();
        if (message == null || "".equalsIgnoreCase(message)) {
            message = "业务异常未定义！";
        }

        return message;
    }
}
