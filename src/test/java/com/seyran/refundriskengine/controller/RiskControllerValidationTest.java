package com.seyran.refundriskengine.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.seyran.refundriskengine.dto.BuyerRequestDto;
import com.seyran.refundriskengine.dto.OrderRequestDto;
import com.seyran.refundriskengine.service.risk.RiskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
public class RiskControllerValidationTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RiskService riskService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnBadRequestWhenEmailIsEmpty() throws Exception {
        BuyerRequestDto buyer = new BuyerRequestDto();
        buyer.setEmail("");
        buyer.setTotalOrders(5);
        buyer.setTotalRefunds(2);

        OrderRequestDto order = new OrderRequestDto();
        order.setBuyer(buyer);
        order.setTotalAmount(BigDecimal.valueOf(1000));
        order.setOrderStatus("COMPLETED");

        mockMvc.perform(post("/api/risk/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(order)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Email is required."))
                .andExpect(jsonPath("$.status").value(400));
    }
}
