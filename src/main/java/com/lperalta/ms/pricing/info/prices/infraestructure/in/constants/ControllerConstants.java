package com.lperalta.ms.pricing.info.prices.infraestructure.in.constants;


public class ControllerConstants {

    private ControllerConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String PRICE_CONTROLLER_BASE_PATH = "/pricing-info/v1/price";
    public static final String BRAND_ID_QUERY_PARAM = "brand_id";
    public static final String PRODUCT_ID_QUERY_PARAM = "product_id";
    public static final String APPLICATION_DATE_QUERY_PARAM = "application_date";
}
