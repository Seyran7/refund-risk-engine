package com.seyran.refundriskengine.service;

import com.seyran.refundriskengine.domain.model.Order;
import com.seyran.refundriskengine.service.risk.RiskRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class RiskEvaluationService {
    private final List<RiskRule> riskRules;
    public int  evaluate(Order order) {

        return riskRules.stream()
                .mapToInt(rule->rule.calculateRisk(order)).sum();
    }
}
