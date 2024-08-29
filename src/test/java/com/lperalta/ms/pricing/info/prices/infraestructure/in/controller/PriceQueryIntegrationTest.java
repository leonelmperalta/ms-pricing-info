package com.lperalta.ms.pricing.info.prices.infraestructure.in.controller;

import com.lperalta.ms.pricing.info.prices.infraestructure.in.controller.advice.ExceptionController;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@EnableWebMvc
public class PriceQueryIntegrationTest {


    @Autowired
    private PriceController priceController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(priceController)
                .setControllerAdvice(new ExceptionController())
                .build();
    }


    /**
     * Test case 1: This case has only one matching application dates in db prices. So the one returned will be the result.
     * <p>
     *  Request:
     *  application_date: 2020-06-14 10:00:00,
     *  brand_id = 1 (ZARA),
     *  product_id = 35455
     * <p>
     * Should return:
     *  brand_id = 1 (ZARA),
     *  product_id = 35455,
     *  fee_id = 1,
     *  final_price = 35.50
     *
     */
    @Test
    public void whenPriceQuery_ThenReturnFee1Price() throws Exception {

        mockMvc.perform(
                        get("/pricing-info/v1/price")
                                .queryParam("brand_id", "1")
                                .queryParam("product_id", "35455")
                                .queryParam("application_date", "2020-06-14 10:00:00")
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("fee_id", CoreMatchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("final_amount", CoreMatchers.is(35.5)))
                .andExpect(MockMvcResultMatchers.jsonPath("brand_id", CoreMatchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("product_id", CoreMatchers.is(35455)));
    }

    /**
     * Test case 2: This case has two matching application dates in db prices. So the one whit highest priority
     * will be the result.
     * <p>
     *  Request:
     *  application_date: 2020-06-14 16:00:00,
     *  brand_id = 1 (ZARA),
     *  product_id = 35455
     * <p>
     * Should return:
     *  brand_id = 1 (ZARA),
     *  product_id = 35455,
     *  fee_id = 2,
     *  final_price = 25.45
     *
     */
    @Test
    public void whenPriceQuery_ThenReturnFee1PriceHighestPriority() throws Exception {

        mockMvc.perform(
                        get("/pricing-info/v1/price")
                                .queryParam("brand_id", "1")
                                .queryParam("product_id", "35455")
                                .queryParam("application_date", "2020-06-14 16:00:00")
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("fee_id", CoreMatchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("final_amount", CoreMatchers.is(25.45)))
                .andExpect(MockMvcResultMatchers.jsonPath("brand_id", CoreMatchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("product_id", CoreMatchers.is(35455)));
    }

    /**
     * Test case 3: This case has only one matching application dates in db prices. So the one returned will be the result.
     * <p>
     *  Request:
     *  application_date: 2020-06-14 21:00:00,
     *  brand_id = 1 (ZARA),
     *  product_id = 35455
     * <p>
     * Should return:
     *  brand_id = 1 (ZARA),
     *  product_id = 35455,
     *  fee_id = 1,
     *  final_price = 35.50
     *
     */
    @Test
    public void whenPriceQueryAt21Hours_ThenReturnFee1Price() throws Exception {

        mockMvc.perform(
                        get("/pricing-info/v1/price")
                                .queryParam("brand_id", "1")
                                .queryParam("product_id", "35455")
                                .queryParam("application_date", "2020-06-14 21:00:00")
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("fee_id", CoreMatchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("final_amount", CoreMatchers.is(35.5)))
                .andExpect(MockMvcResultMatchers.jsonPath("brand_id", CoreMatchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("product_id", CoreMatchers.is(35455)));
    }

    /**
     * Test case 4: This case has two matching application dates in db prices. So the one whit highest priority
     * will be the result.
     * <p>
     *  Request:
     *  application_date: 2020-06-15 10:00:00,
     *  brand_id = 1 (ZARA),
     *  product_id = 35455
     * <p>
     * Should return:
     *  brand_id = 1 (ZARA),
     *  product_id = 35455,
     *  fee_id = 3,
     *  final_price = 35.50
     *
     */
    @Test
    public void whenPriceQuery_ThenReturnFee3PriceHighestPriority() throws Exception {

        mockMvc.perform(
                        get("/pricing-info/v1/price")
                                .queryParam("brand_id", "1")
                                .queryParam("product_id", "35455")
                                .queryParam("application_date", "2020-06-15 10:00:00")
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("fee_id", CoreMatchers.is(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("final_amount", CoreMatchers.is(30.5)))
                .andExpect(MockMvcResultMatchers.jsonPath("brand_id", CoreMatchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("product_id", CoreMatchers.is(35455)));
    }

    /**
     * Test case 4: This case has two matching application dates in db prices. So the one whit highest priority
     * will be the result.
     * <p>
     *  Request:
     *  application_date: 2020-06-16 21:00:00,
     *  brand_id = 1 (ZARA),
     *  product_id = 35455
     * <p>
     * Should return:
     *  brand_id = 1 (ZARA),
     *  product_id = 35455,
     *  fee_id = 4,
     *  final_price = 38.95
     *
     */
    @Test
    public void whenPriceQuery_ThenReturnFee4PriceHighestPriority() throws Exception {

        mockMvc.perform(
                        get("/pricing-info/v1/price")
                                .queryParam("brand_id", "1")
                                .queryParam("product_id", "35455")
                                .queryParam("application_date", "2020-06-16 21:00:00")
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("fee_id", CoreMatchers.is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("final_amount", CoreMatchers.is(38.95)))
                .andExpect(MockMvcResultMatchers.jsonPath("brand_id", CoreMatchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("product_id", CoreMatchers.is(35455)));
    }


}
