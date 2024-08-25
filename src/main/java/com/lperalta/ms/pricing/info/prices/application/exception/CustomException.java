package com.lperalta.ms.pricing.info.prices.application.exception;

public abstract class CustomException extends Exception {

    public CustomException() {}
    public CustomException(String message) {
        super(message);
    }

    protected CustomException(Throwable e) { super(e); }
    protected CustomException(String message, Throwable e) { super(message, e); }

    public abstract String getCode();

    public abstract String getDescription();

}
