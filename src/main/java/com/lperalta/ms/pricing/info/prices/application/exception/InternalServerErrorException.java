package com.lperalta.ms.pricing.info.prices.application.exception;

import com.lperalta.ms.pricing.info.prices.application.constants.ExceptionConstants;
import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends CustomStatusException {

    @Override
    public String getCode() {
        return ExceptionConstants.INTERNAL_SERVER_ERROR_CODE;
    }

    @Override
    public String getDescription() {
        return ExceptionConstants.INTERNAL_SERVER_ERROR_MESSAGE;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
