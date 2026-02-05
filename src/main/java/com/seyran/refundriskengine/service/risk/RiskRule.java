package com.seyran.refundriskengine.service.risk;

import com.seyran.refundriskengine.domain.model.Order;
;


public interface RiskRule {
    int calculateRisk(Order order);
    int priority();
}
