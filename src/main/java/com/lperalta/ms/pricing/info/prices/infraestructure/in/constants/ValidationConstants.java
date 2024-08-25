package com.lperalta.ms.pricing.info.prices.infraestructure.in.constants;

public class ValidationConstants {

    private ValidationConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String BRAND_ID_NOT_NULL = "<brand_id> parameter cannot be null.";
    public static final String BRAND_ID_POSITIVE = "<brand_id> should be a positive integer.";
    public static final String PRODUCT_ID_NOT_NULL = "<product_id> parameter cannot be null.";
    public static final String PRODUCT_ID_POSITIVE = "<product_id> should be a positive integer.";
    public static final String APPLICATION_DATE_NOT_BLANK = "<application_date> parameter cannot be null or empty.";
    public static final String ISO_8601_FORMAT_REGEXP = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$";
    public static final String APPLICATION_DATE_ISO_8601 = "<application_date> must have ISO8601 format.";
}
