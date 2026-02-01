package com.seyran.refundriskengine.service;

import com.seyran.refundriskengine.domain.model.Order;
import com.seyran.refundriskengine.service.risk.RiskRule;

import java.util.List;

public class RiskService {
    private final List<RiskRule>  rules;

    public RiskService(List<RiskRule> rules) {
        this.rules = rules;
    }
    public int calculateRisk(Order order ) {
        int totalRisk = 0;

        for (RiskRule rule : rules) {
            totalRisk += rule.calculateRisk(order);
        }
        return totalRisk;
    }
}
