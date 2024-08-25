package com.lperalta.ms.pricing.info.prices.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PriceQuery {

    private PriceQuery() {}

    private LocalDateTime applicationEndDate;
    private LocalDateTime applicationStartDate;
    private Long brandId;
    private String currency;
    private Long feeId;
    private BigDecimal finalPrice;
    private Integer priority;
    private Long productId;

    public LocalDateTime getApplicationEndDate() {
        return applicationEndDate;
    }

    public LocalDateTime getApplicationStartDate() {
        return applicationStartDate;
    }

    public Long getBrandId() {
        return brandId;
    }

    public String getCurrency() {
        return currency;
    }

    public Long getFeeId() {
        return feeId;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public Integer getPriority() {
        return priority;
    }

    public Long getProductId() {
        return productId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final PriceQuery instance = new PriceQuery();

        private Builder() {}

        public Builder applicationEndDate(LocalDateTime applicationEndDate) {
            instance.applicationEndDate = applicationEndDate;
            return this;
        }

        public Builder applicationStartDate(LocalDateTime applicationStartDate) {
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

        public Builder priority(Integer priority) {
            instance.priority = priority;
            return this;
        }

        public Builder productId(Long productId) {
            instance.productId = productId;
            return this;
        }

        public PriceQuery build() {
            return instance;
        }
    }
}
