package com.seyran.refundriskengine.service;

import com.seyran.refundriskengine.domain.model.Order;
import com.seyran.refundriskengine.domain.model.RiskLevel;
import com.seyran.refundriskengine.service.risk.RiskRule;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class RiskService {
    private final List<RiskRule>  rules;

    public RiskService(List<RiskRule> rules) {
        this.rules = rules;
    }
    public int calculateRisk(Order order ) {
        int score = rules.stream()
                .sorted(Comparator.comparingInt(RiskRule::priority))
                .mapToInt(rule->rule.calculateRisk(order))
                .sum();

        RiskLevel level;
        if(score >= 60) level=RiskLevel.HIGH;
        else if(score>=30) level=RiskLevel.MEDIUM;
        else level = RiskLevel.LOW;

        return new RiskResult(score,level);
    }
}
