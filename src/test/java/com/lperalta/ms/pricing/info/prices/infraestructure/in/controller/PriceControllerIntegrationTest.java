package com.lperalta.ms.pricing.info.prices.infraestructure.in.controller;

import com.lperalta.ms.pricing.info.prices.application.exception.NotDataFoundException;
import com.lperalta.ms.pricing.info.prices.domain.port.in.PriceQueryUseCase;
import com.lperalta.ms.pricing.info.prices.domain.model.PriceQuery;
import com.lperalta.ms.pricing.info.prices.infraestructure.in.controller.advice.ExceptionController;
import com.lperalta.ms.pricing.info.prices.infraestructure.in.mapper.PriceQueryMapper;
import com.lperalta.ms.pricing.info.util.TestUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;



@SpringBootTest(classes = {PriceController.class})
@AutoConfigureMockMvc
@EnableWebMvc
class PriceControllerIntegrationTest {

    private MockMvc mockMvc;

    @MockBean
    private PriceQueryUseCase priceQueryUseCase;

    @MockBean
    private PriceQueryMapper priceQueryMapper;

    @Autowired
    @InjectMocks
    private PriceController priceController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(priceController)
                .setControllerAdvice(new ExceptionController())
                .build();
    }

    @Test
    public void givenValidRequest_whenQuery_thenReturn200() throws Exception {
        PriceQuery priceQuery = TestUtils.singlePriceQuery().get(0);

        Mockito.when(this.priceQueryUseCase.priceQuery(eq(1L), eq(35455L), eq("2020-06-14 16:00:00")))
                .thenReturn(priceQuery);
        Mockito.when(this.priceQueryMapper.toDto(eq(priceQuery)))
                .thenReturn(TestUtils.getPriceQueryResponse());


        mockMvc.perform(
                get("/pricing-info/v1/price")
                        .queryParam("brand_id", "1")
                        .queryParam("product_id", "35455")
                        .queryParam("application_date", "2020-06-14 16:00:00")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("fee_id", CoreMatchers.is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("final_amount", CoreMatchers.is(38.95)));
    }

    @Test
    public void givenInvalidBrand_whenQuery_thenReturn400() throws Exception {
        mockMvc.perform(
                        get("/pricing-info/v1/price")
                                .queryParam("brand_id", "ABC")
                                .queryParam("product_id", "35455")
                                .queryParam("application_date", "2020-06-14 16:00:00")
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].code", CoreMatchers.is("ERROR_400")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].description", CoreMatchers.is("<brand_id> has an invalid type.")));
    }

    @Test
    public void givenInvalidRequest_whenQuery_thenReturn404() throws Exception {
        Mockito.when(this.priceQueryUseCase.priceQuery(any(), any(), any())).thenThrow(NotDataFoundException.class);

        mockMvc.perform(
                        get("/pricing-info/v1/price")
                                .queryParam("brand_id", "1250")
                                .queryParam("product_id", "35455")
                                .queryParam("application_date", "2020-06-14 16:00:00")
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].code", CoreMatchers.is("ERROR_404")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].description", CoreMatchers.is("Not data found for given parameters")));
    }

}