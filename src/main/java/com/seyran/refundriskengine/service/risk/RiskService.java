package com.seyran.refundriskengine.service.risk;

import com.seyran.refundriskengine.domain.model.Order;
import com.seyran.refundriskengine.domain.model.RiskLevel;
import com.seyran.refundriskengine.domain.model.RiskResult;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class RiskService {
    private final List<RiskRule>  rules;

    public RiskService(List<RiskRule> rules) {
        this.rules = rules;
    }
    public RiskResult calculateRisk(Order order ) {
        int score = rules.stream()
                .sorted(Comparator.comparingInt(RiskRule::priority))
                .mapToInt(rule->rule.calculateRisk(order))
                .sum();

        return new RiskResult(score, RiskLevel.fromScore(score));
    }
}
