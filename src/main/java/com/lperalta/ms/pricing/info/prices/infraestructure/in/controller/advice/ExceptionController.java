package com.lperalta.ms.pricing.info.prices.infraestructure.in.controller.advice;

import com.lperalta.ms.pricing.info.prices.application.exception.CustomStatusException;
import com.lperalta.ms.pricing.info.prices.infraestructure.in.dto.ErrorItemDTO;
import com.lperalta.ms.pricing.info.prices.infraestructure.in.dto.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Collections;
import java.util.List;

@ControllerAdvice(basePackages = "com.lperalta.ms.pricing.info")
public class ExceptionController {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationException(ConstraintViolationException ex, HttpServletRequest request) {
        List<ErrorItemDTO> errors = ex.getConstraintViolations().stream().map((err) ->
                ErrorItemDTO.builder().code("ERROR_400").description(err.getMessage()).build()
        ).toList();
        return ResponseEntity.badRequest().body(ErrorResponseDTO.builder().errors(errors).build());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDTO> handleTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        List<ErrorItemDTO> errors = Collections.singletonList(ErrorItemDTO.builder()
                .code("ERROR_400")
                .description("<".concat(ex.getName().concat("> has an invalid type."))).build());

        return ResponseEntity.badRequest().body(ErrorResponseDTO.builder().errors(errors).build());
    }

    @ExceptionHandler(CustomStatusException.class)
    public ResponseEntity<ErrorResponseDTO> handleCustomStatusException(CustomStatusException ex, HttpServletRequest request) {
        List<ErrorItemDTO> errors = Collections.singletonList(ErrorItemDTO.builder()
                .code(ex.getCode())
                .description(ex.getDescription()).build());

        return ResponseEntity.status(ex.getStatus()).body(ErrorResponseDTO.builder().errors(errors).build());
    }
}
