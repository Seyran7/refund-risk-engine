package com.seyran.refundriskengine.service.analysis;

import com.seyran.refundriskengine.domain.model.Order;
import com.seyran.refundriskengine.service.risk.RiskRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RiskEngine {
    private final List<RiskRule> riskRules;

    public int calculateTotalRisk(Order order) {
        return riskRules.stream()
                .mapToInt(rule->rule.calculateRisk(order)).sum();

    }
}
