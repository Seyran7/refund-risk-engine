package com.seyran.refundriskengine.service;

import com.seyran.refundriskengine.domain.model.Order;
import com.seyran.refundriskengine.domain.model.RiskLevel;
import com.seyran.refundriskengine.domain.model.RiskResult;
import com.seyran.refundriskengine.service.risk.RiskRule;
import com.seyran.refundriskengine.service.risk.RiskService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class RiskServiceTest {

    @Test
    void shouldCalculateTotalRiskAndReturnHighLevel() {
        RiskRule rule1 = new RiskRule() {
            @Override
            public int calculateRisk(Order order) {
                return 20;
            }

            @Override
            public int priority() {
                return 1;
            }
        };

        RiskRule rule2 = new RiskRule() {
            @Override
            public int calculateRisk(Order order) {
                return 15;
            }
            @Override
            public int priority() {
                return 2;
            }
        };
        RiskRule rule3 = new RiskRule() {
            @Override
            public int calculateRisk(Order order) {
                return 30;
            }
            @Override
            public int priority() {
                return 3;
            }
        };

        RiskService service = new RiskService(List.of(rule1, rule2, rule3));

            Order order = new Order();
        order.setTotalAmount(BigDecimal.valueOf(1500));

            RiskResult result = service.calculateRisk(order);

            assertEquals(65,result.getScore());

            assertEquals(RiskLevel.HIGH, result.getLevel());
        }
    }


