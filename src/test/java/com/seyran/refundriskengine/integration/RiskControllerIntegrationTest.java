package com.seyran.refundriskengine.integration;

import com.seyran.refundriskengine.domain.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RiskControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testCalculateRiskEndPoint(){
        Buyer buyer =Buyer.builder()
                .id(1L)
                .email("test@gmail.com")
                .totalOrders(5)
                .totalRefunds(2)
                .blocked(false)
                .build();

        Order order = Order.builder()
                .id(100L)
                .buyer(buyer)
                .totalAmount(BigDecimal.valueOf(1500))
                .status(OrderStatus.COMPLETED)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Order> request = new HttpEntity<>(order, headers);

        ResponseEntity<RiskResult> response=restTemplate.postForEntity("/api/risk/calculate",request,RiskResult.class);

        assertEquals(HttpStatus.OK,response.getStatusCode());
        RiskResult result = response.getBody();
        assertEquals(35,result.getScore());
        assertEquals(RiskLevel.MEDIUM,result.getLevel());
    }
}
