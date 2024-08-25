package com.lperalta.ms.pricing.info.prices.infraestructure.in.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PriceQueryResponseDTO {

    private String applicationEndDate;
    private String applicationStartDate;
    private Long brandId;
    private String currency;
    private Long feeId;
    private BigDecimal finalPrice;
    private Long productId;

    public String getApplicationEndDate() {
        return applicationEndDate;
    }

    public void setApplicationEndDate(String applicationEndDate) {
        this.applicationEndDate = applicationEndDate;
    }

    public String getApplicationStartDate() {
        return applicationStartDate;
    }

    public void setApplicationStartDate(String applicationStartDate) {
        this.applicationStartDate = applicationStartDate;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getFeeId() {
        return feeId;
    }

    public void setFeeId(Long feeId) {
        this.feeId = feeId;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final PriceQueryResponseDTO instance = new PriceQueryResponseDTO();

        private Builder() {}

        public Builder applicationEndDate(String applicationEndDate) {
            instance.applicationEndDate = applicationEndDate;
            return this;
        }

        public Builder applicationStartDate(String applicationStartDate) {
            instance.applicationStartDate = applicationStartDate;
            return this;
        }

        public Builder brandId(Long brandId) {
            instance.brandId = brandId;
            return this;
        }

        public Builder currency(String currency) {
            instance.currency = currency;
            return this;
        }

        public Builder feeId(Long feeId) {
            instance.feeId = feeId;
            return this;
        }

        public Builder finalPrice(BigDecimal finalPrice) {
            instance.finalPrice = finalPrice;
            return this;
        }

        public Builder productId(Long productId) {
            instance.productId = productId;
            return this;
        }

        public PriceQueryResponseDTO build() {
            return instance;
        }
    }
}
