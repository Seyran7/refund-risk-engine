package com.seyran.refundriskengine.service.risk;

import com.seyran.refundriskengine.domain.model.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HighAmountRuleTest {
    private static final Logger log = LoggerFactory.getLogger(HighAmountRuleTest.class);
    private final HighAmountRule rule= new HighAmountRule();

    @Test
    void shouldReturn20_whenAmountGreaterThanThreshold(){
        Order order = new Order();
        order.setTotalAmount(BigDecimal.valueOf(1500));

        int result = rule.calculateRisk(order);

        assertEquals(20,result);
    }
    @Test
    void shouldReturn0_whenAmountLessThanThreshold(){
        Order order = new Order();
        order.setTotalAmount(BigDecimal.valueOf(500));
        int result = rule.calculateRisk(order);
        assertEquals(0,result);
    }
}
