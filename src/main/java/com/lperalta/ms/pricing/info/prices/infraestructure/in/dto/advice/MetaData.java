package com.lperalta.ms.pricing.info.prices.infraestructure.in.dto.advice;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MetaData {

    private String method;
    private String operation;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final MetaData metaDataDTO = new MetaData();

        private Builder() {
            // private constructor
        }

        public Builder method(String method) {
            metaDataDTO.method = method;
            return this;
        }

        public Builder operation(String operation) {
            metaDataDTO.operation = operation;
            return this;
        }

        public MetaData build() {
            // You can add validation logic here if needed
            return metaDataDTO;
        }
    }
}
