package com.seyran.refundriskengine.integration;

import com.seyran.refundriskengine.domain.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HighRefundRuleIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testHighRefundBuyerRisk() {
        Buyer buyer=Buyer.builder()
                .id(2L)
                .email("refund@gmail.com")
                .totalOrders(5)
                .totalRefunds(4)
                .blocked(false)
                .createdAt(LocalDateTime.now().minusDays(30))
                .build();

        Order order= Order.builder()
                .id(101L)
                .buyer(buyer)
                .totalAmount(BigDecimal.valueOf(2000))
                .status(OrderStatus.COMPLETED)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Order> request = new HttpEntity<>(order, headers);

        ResponseEntity<RiskResult> response = restTemplate.postForEntity(
                "/api/risk/calculate", request, RiskResult.class);

        assertEquals(HttpStatus.OK,response.getStatusCode());
        RiskResult riskResult=response.getBody();
        assertEquals(30, riskResult.getScore());
        assertEquals(RiskLevel.MEDIUM,riskResult.getLevel());

    }
}
