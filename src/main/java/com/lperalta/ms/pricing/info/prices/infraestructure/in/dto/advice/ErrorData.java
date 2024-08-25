package com.lperalta.ms.pricing.info.prices.infraestructure.in.dto.advice;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ErrorData {

    private String code;
    private String description;

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final ErrorData errorData = new ErrorData();

        private Builder() {
            // private constructor
        }

        public Builder code(String code) {
            errorData.code = code;
            return this;
        }

        public Builder description(String description) {
            errorData.description = description;
            return this;
        }

        public ErrorData build() {
            // You can add validation logic here if needed
            return errorData;
        }
    }
}
