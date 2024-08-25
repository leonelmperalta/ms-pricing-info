package com.lperalta.ms.pricing.info.prices.infraestructure.in.dto.advice;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ResponseBody {

    private MetaData metaData;
    private Object data;
    private List<ErrorData> errors;

    public MetaData getMetaData() {
        return metaData;
    }

    public Object getData() {
        return data;
    }

    public List<ErrorData> getErrors() {
        return errors;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setErrors(List<ErrorData> errors) {
        this.errors = errors;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final ResponseBody responseBody = new ResponseBody();

        private Builder() {}

        public Builder metaData(MetaData metaData) {
            responseBody.metaData = metaData;
            return this;
        }

        public Builder data(Object data) {
            responseBody.data = data;
            return this;
        }

        public Builder errors(List<ErrorData> errors) {
            responseBody.errors = errors;
            return this;
        }

        public ResponseBody build() {
            return responseBody;
        }
    }
}
