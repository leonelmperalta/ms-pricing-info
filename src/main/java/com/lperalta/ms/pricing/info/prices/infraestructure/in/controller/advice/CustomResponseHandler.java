package com.lperalta.ms.pricing.info.prices.infraestructure.in.controller.advice;

import com.lperalta.ms.pricing.info.prices.application.exception.CustomStatusException;
import com.lperalta.ms.pricing.info.prices.infraestructure.in.dto.advice.ErrorData;
import com.lperalta.ms.pricing.info.prices.infraestructure.in.dto.advice.MetaData;
import com.lperalta.ms.pricing.info.prices.infraestructure.in.dto.advice.ResponseBody;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@ControllerAdvice(basePackages = "com.lperalta.ms.pricing.info")
public class CustomResponseHandler implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        HttpStatus responseStatus = HttpStatus.valueOf(((ServletServerHttpResponse) response).getServletResponse().getStatus());

        if (responseStatus.is2xxSuccessful() && responseStatus != HttpStatus.PARTIAL_CONTENT) {
            ResponseBody customResponse = this.createResponseBody(request);
            customResponse.setData(body instanceof Collection ? body : Collections.singleton(body));
            return customResponse;
        } else if (body instanceof ResponseBody responseBody) {
            ResponseBody customResponse = this.createResponseBody(request);
            customResponse.setData(responseBody.getData() != null ? responseBody.getData() : new ArrayList<>());
            customResponse.setErrors(responseBody.getErrors());
            return customResponse;
        }
        return body instanceof Collection ? body : Collections.singletonList(body);
    }

    private ResponseBody createResponseBody(ServerHttpRequest request) {
        MetaData metaData = MetaData.builder()
                .method(request.getMethod().name())
                .operation(request.getURI().getPath())
                .build();
        return ResponseBody.builder()
                .metaData(metaData)
                .errors(new ArrayList<>())
                .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseBody> handleValidationException(ConstraintViolationException ex, HttpServletRequest request) {
        List<ErrorData> errors = ex.getConstraintViolations().stream().map((err) ->
                ErrorData.builder().code("ERROR_400").description(err.getMessage()).build()
        ).toList();
        return ResponseEntity.badRequest().body(ResponseBody.builder().errors(errors).build());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseBody> handleTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        List<ErrorData> errors = Collections.singletonList(ErrorData.builder()
                .code("ERROR_400")
                .description("<".concat(ex.getName().concat("> has an invalid type."))).build());

        return ResponseEntity.badRequest().body(ResponseBody.builder().errors(errors).build());
    }

    @ExceptionHandler(CustomStatusException.class)
    public ResponseEntity<ResponseBody> handleCustomStatusException(CustomStatusException ex, HttpServletRequest request) {
        List<ErrorData> errors = Collections.singletonList(ErrorData.builder()
                .code(ex.getCode())
                .description(ex.getDescription()).build());

        return ResponseEntity.status(ex.getStatus()).body(ResponseBody.builder().errors(errors).build());
    }
}
