package com.lperalta.ms.pricing.info.prices.application.exception;

import com.lperalta.ms.pricing.info.prices.application.constants.ExceptionConstants;
import org.springframework.http.HttpStatus;

public class NotDataFoundException extends CustomStatusException {

    @Override
    public String getCode() {
        return ExceptionConstants.NOT_DATA_FOUND_CODE;
    }

    @Override
    public String getDescription() {
        return ExceptionConstants.NOT_DATA_FOUND_MESSAGE;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
